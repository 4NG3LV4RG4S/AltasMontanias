package com.example.altasmontaas;

import com.google.android.gms.maps.model.LatLng;

public class Markers {
    private String title;
    private String description;
    private LatLng position;
    private Class activityClass;

    public Markers(String title, String description, LatLng position, Class activityClass) {
        this.title = title;
        this.description = description;
        this.position = position;
        this.activityClass = activityClass;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LatLng getPosition() {
        return position;
    }

    public Class getActivityClass() {
        return activityClass;
    }


}
