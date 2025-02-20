package com.example.googlemaps;

import android.location.Location;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapsBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FirebaseFirestore db;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private EditText searchBar;
    private Button searchButton, toggleMapButton;
    private boolean isSatelliteView = false;
    private List<Marker> markers = new ArrayList<>();
    private static final String FOURSQUARE_API_KEY = "fsq3krnsJZuNgz0Ax282ItJ5zWngJH6woxxFsCmJ/VzBK2Y=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        requestLocationPermission();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        Log.d("UBICACION", "Ubicación en tiempo real: " + location.getLatitude() + ", " + location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Ubicación actual"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                    }
                }
            }
        };

        searchBar = findViewById(R.id.search_bar);
        searchButton = findViewById(R.id.search_button);
        toggleMapButton = findViewById(R.id.toggle_map_button);

        searchButton.setOnClickListener(view -> searchRestaurantsNearby());
        toggleMapButton.setOnClickListener(view -> toggleMapType());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

            LocationRequest locationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(5000)
                    .setFastestInterval(2000);

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        } else {
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void searchRestaurantsNearby() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String url = "https://api.foursquare.com/v3/places/search?query=restaurant&ll=" + latitude + "," + longitude + "&radius=5000";

                Log.d("API_URL", "URL de la solicitud: " + url);

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        response -> {
                            try {
                                JSONArray results = response.getJSONArray("results");

                                for (Marker marker : markers) {
                                    marker.remove();
                                }
                                markers.clear();

                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject place = results.getJSONObject(i);
                                    String name = place.getString("name");
                                    JSONObject locationObj = place.getJSONObject("geocodes").getJSONObject("main");
                                    double lat = locationObj.getDouble("latitude");
                                    double lon = locationObj.getDouble("longitude");

                                    LatLng placeLocation = new LatLng(lat, lon);
                                    Log.d("ADDING_MARKER", "Agregando marcador: " + name + " en " + lat + ", " + lon);

                                    Marker marker = mMap.addMarker(new MarkerOptions()
                                            .position(placeLocation)
                                            .title(name));

                                    markers.add(marker);
                                }

                                if (!markers.isEmpty()) {
                                    LatLng firstLocation = markers.get(0).getPosition();
                                    Log.d("MOVING_CAMERA", "Centrando la cámara en: " + firstLocation.latitude + ", " + firstLocation.longitude);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 15));
                                } else {
                                    Log.d("NO_MARKERS", "No se encontraron lugares cercanos.");
                                    Toast.makeText(this, "No se encontraron restaurantes", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
                    Log.e("API_ERROR", "Error en la solicitud: " + error.toString());
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", "fsq3 " + FOURSQUARE_API_KEY); // Agregar espacio después de fsq3
                        return headers;
                    }
                };
                requestQueue.add(request);
            } else {
                Toast.makeText(this, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleMapType() {
        if (mMap != null) {
            if (isSatelliteView) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            } else {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
            isSatelliteView = !isSatelliteView;
        }
    }
}
