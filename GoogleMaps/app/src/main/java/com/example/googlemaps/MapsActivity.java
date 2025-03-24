package com.example.googlemaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mapaGoogle;
    private EditText campoBusqueda;
    private Button botonBuscar, botonTipoMapa;
    private FusedLocationProviderClient clienteUbicacion;
    private Marker marcadorUbicacion;
    private static final String CLAVE_API = "AIzaSyBFe3cMarZMD9rtT3OQZHgtY06IoV2ZtNE";
    private List<Marker> listaMarcadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment fragmentoMapa = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (fragmentoMapa != null) {
            fragmentoMapa.getMapAsync(this);
        }

        campoBusqueda = findViewById(R.id.search_bar);
        botonBuscar = findViewById(R.id.search_button);
        botonTipoMapa = findViewById(R.id.toggle_map_button);

        clienteUbicacion = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapaGoogle = googleMap;
        clienteUbicacion = LocationServices.getFusedLocationProviderClient(this);

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
        clienteUbicacion.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location ubicacion) {
                        if (ubicacion != null) {
                            LatLng coordenadas = new LatLng(ubicacion.getLatitude(), ubicacion.getLongitude());
                            if (marcadorUbicacion == null) {
                                marcadorUbicacion = mapaGoogle.addMarker(new MarkerOptions()
                                        .position(coordenadas)
                                        .title("Mi Ubicación"));
                            } else {
                                marcadorUbicacion.setPosition(coordenadas);
                            }
                            mapaGoogle.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 15));
                        }
                    }
                });

        botonBuscar.setOnClickListener(v -> buscarLugares(campoBusqueda.getText().toString()));
        botonTipoMapa.setOnClickListener(v -> alternarTipoMapa());
    }

    private Bitmap convertirVectorABitmap(int recursoVector) {
        VectorDrawableCompat drawableVector = VectorDrawableCompat.create(getResources(), recursoVector, null);
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawableVector.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawableVector.draw(canvas);
        return bitmap;
    }

    private void buscarLugares(String tipoLugar) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        clienteUbicacion.getLastLocation().addOnSuccessListener(this, ubicacion -> {
            if (ubicacion != null) {
                double lat = ubicacion.getLatitude();
                double lng = ubicacion.getLongitude();
                obtenerLugaresCercanos(lat, lng, tipoLugar);
            }
        });
    }

    private void alternarTipoMapa() {
        if (mapaGoogle != null) {
            if (mapaGoogle.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                mapaGoogle.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            } else {
                mapaGoogle.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        }
    }

    private void obtenerLugaresCercanos(double lat, double lng, String tipoLugar) {
        new Thread(() -> {
            try {
                String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                        "location=" + lat + "," + lng +
                        "&radius=5000" +
                        "&type=" + tipoLugar.toLowerCase() +
                        "&key=" + CLAVE_API;

                URL url = new URL(urlString);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestMethod("GET");

                BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }
                lector.close();

                JSONObject jsonObject = new JSONObject(respuesta.toString());
                JSONArray resultados = jsonObject.getJSONArray("results");

                runOnUiThread(() -> {
                    for (Marker marcador : listaMarcadores) {
                        marcador.remove();
                    }
                    listaMarcadores.clear();

                    for (int i = 0; i < resultados.length(); i++) {
                        try {
                            JSONObject lugar = resultados.getJSONObject(i);
                            String nombre = lugar.getString("name");
                            JSONObject ubicacion = lugar.getJSONObject("geometry").getJSONObject("location");
                            double latLugar = ubicacion.getDouble("lat");
                            double lngLugar = ubicacion.getDouble("lng");

                            Marker marcador = mapaGoogle.addMarker(new MarkerOptions()
                                    .position(new LatLng(latLugar, lngLugar))
                                    .title(nombre));
                            listaMarcadores.add(marcador);
                        } catch (Exception e) {
                            Log.e("ErrorMapa", "Error procesando lugar", e);
                        }
                    }
                    mapaGoogle.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 14));
                });

            } catch (Exception e) {
                Log.e("ErrorMapa", "Error obteniendo lugares", e);
            }
        }).start();
    }
}
