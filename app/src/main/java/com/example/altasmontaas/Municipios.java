package com.example.altasmontaas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

public class Municipios extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,GoogleMap.OnInfoWindowClickListener {

    GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    int t;
    Marker marker;
    View v = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipios);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"UseCompatLoadingForDrawables", "PotentialBehaviorOverride"})
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(19.134050071440704, -97.06372878304784)));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        List<Markers> myMarkers = new ArrayList<>();
        myMarkers.add(new Markers("Huatusco", "Ciudad de Conejos", new LatLng(19.14868922736793, -96.96851219981221), Huatusco.class));
        myMarkers.add(new Markers("Coscomatepec de Bravo", "Para Pan el de San Juan", new LatLng(19.068487713962945, -97.04600568064879), Cosco.class));

        myMarkers.add(new Markers("Ixhuatlan del Café", "Café de Altura", new LatLng(19.05106126284839, -96.98377960246161), Ixhua.class));
        myMarkers.add(new Markers("Orizaba", "La Ciudad Más Limpia de México", new LatLng(18.850073596371136, -97.10352116725261), Orizaba.class));

        myMarkers.add(new Markers("Cordoba", "La Ciudad de los 30 Caballeros", new LatLng(18.88414556541565, -96.92489293612635), Cordoba.class));
        myMarkers.add(new Markers("Fortin", "De Flores... Muchos Sabores", new LatLng(18.90253046352665, -96.99887578186762), Fortin.class));

        myMarkers.add(new Markers("Calcahualco", "Proximamente", new LatLng(19.1211536929641, -97.08518277234032), Calca.class));
        myMarkers.add(new Markers("Alpatlahuac", "Proximamente", new LatLng(19.1192040195533, -97.09249609346568), Alpa.class));

        myMarkers.add(new Markers("Zentla", "Proximamente", new LatLng(19.116606361061233, -96.85931934762844), Zentla.class));
        myMarkers.add(new Markers("Paso del Macho", "Proximamente", new LatLng(18.97099119003499, -96.72325179233906), PMacho.class));

        for (Markers myMarker : myMarkers) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(myMarker.getPosition())
                    .title(myMarker.getTitle())
                    .flat(false)
                    .snippet(myMarker.getDescription());

            marker = mMap.addMarker(markerOptions);
            marker.setTag(myMarker.getActivityClass());
        }
        /*
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker1) {
                Class activityClass = (Class) marker1.getTag();
                Intent intent = new Intent(Municipios.this.getApplicationContext(), activityClass);
                Municipios.this.startActivity(intent);
                return false;
            }
        });
         */
        mMap.setOnInfoWindowClickListener(this);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                if(v == null){

                }
                return null;
            }

            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                return null;
            }
        });

        float maxZ = 25.0f;
        float minZ = 10.0f;

        mMap.setMaxZoomPreference(maxZ);
        mMap.setMinZoomPreference(minZ);
    }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case LOCATION_REQUEST:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
                        mMap.setMyLocationEnabled(true);
                    }
                    break;
            }
        }

        @Override
        public boolean onMyLocationButtonClick () {
            Toast.makeText(this, "Mostrando ubicacion Actual", Toast.LENGTH_SHORT)
                    .show();
            // Return false so that we don't consume the event and the default behavior still occurs
            // (the camera animates to the user's current position).
            return false;
        }

        @Override
        public void onMyLocationClick (@NonNull Location location){
            Toast.makeText(this, "Coordenadas:\n" + location, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onInfoWindowClick(@NonNull Marker marker) {
        Class activityClass = (Class)  marker.getTag();
        Intent intent = new Intent(getApplicationContext(), activityClass);
        startActivity(intent);
    }
}
