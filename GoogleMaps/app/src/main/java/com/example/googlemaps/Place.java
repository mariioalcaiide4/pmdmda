package com.example.googlemaps;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    private String name;
    private LatLng location;

    public Place(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return location;
    }
}