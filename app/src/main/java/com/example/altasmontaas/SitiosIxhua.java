package com.example.altasmontaas;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
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
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SitiosIxhua extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    ImageView d, r, h, m;
    Polyline ixp;
    PlacesClient placesClient;
    private List<Marker> restaurantMarkers;
    private List<Marker> hotelMarkers;
    private boolean isRestaurantMarkersVisible;
    private boolean isHotelMarkersVisible;
    Marker marker1, marker2, marker3, marker4, marker5;
    private List<Marker> foodMarkers = new ArrayList<>();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios_ixhua);

        // Inicializar la API Places
        Places.initialize(getApplicationContext(), "AIzaSyCNGDDCBXfXGJV0zWe3LcZN9lTPbX9bCc4");

        // Crear el cliente de Places
        placesClient = Places.createClient(this);

        d = findViewById(R.id.limites);
        r = findViewById(R.id.restaurants);
        h = findViewById(R.id.hotel);
        m = findViewById(R.id.montana);
        restaurantMarkers = new ArrayList<>();
        hotelMarkers = new ArrayList<>();
        foodMarkers = new ArrayList<>();
        hotelMarkers = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapSitiosixhua);
        mapFragment.getMapAsync(this);

        m.setOnClickListener(view -> {
            animation(m);
            if (areMarkersVisible()) {
                deleteMountain();
            } else {
                showMountain();
            }
        });

        r.setOnClickListener(view -> {
            animation(r);
            if (isRestaurantMarkersVisible) {
                // Ocultar marcadores de restaurantes
                for (Marker marker : restaurantMarkers) {
                    marker.remove();
                }
                restaurantMarkers.clear();
                isRestaurantMarkersVisible = false;
            } else {
                // Mostrar marcadores de restaurantes
                showFoodMarkers();
                isRestaurantMarkersVisible = true;
            }
        });

        h.setOnClickListener(view -> {
            animation(h);
            if (isHotelMarkersVisible) {
                // Ocultar marcadores de hoteles
                for (Marker marker : hotelMarkers) {
                    marker.remove();
                }
                hotelMarkers.clear();
                isHotelMarkersVisible = false;
            } else {
                // Mostrar marcadores de hoteles
                showHotelMarkers();
                isHotelMarkersVisible = true;
            }
        });

        d.setOnClickListener(view -> {
            animation(d);
            togglePolylineVisibility();
        });
    }

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        float mx = 65.0f;
        float mn = 8.0f;
        mMap.setMaxZoomPreference(mx);
        mMap.setMinZoomPreference(mn);

        LatLng ix = new LatLng(19.04566277921364, -96.9837412587726);
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(ix, 15.0f);
        mMap.moveCamera(cam);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(19.043686800626595, -96.96523912769419)).title("Mirador de Café").snippet("mirador").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        ixp = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(19.056148895669967, -96.99284326463206),
                        new LatLng(19.055925796035147, -96.99228536516094),
                        new LatLng(19.05771058471152, -96.99084770113924),
                        new LatLng(19.05728467097663, -96.98889505299034),
                        new LatLng(19.05572297792491, -96.98960315616523),
                        new LatLng(19.05513480400287, -96.98621284399462),
                        new LatLng(19.056534248877888, -96.98565494452353),
                        new LatLng(19.056250304495688, -96.98421728050182),
                        new LatLng(19.055317340960478, -96.9844747725654),
                        new LatLng(19.05436409018958, -96.98395978843821),
                        new LatLng(19.054749447543823, -96.98338043129515),
                        new LatLng(19.055357904701562, -96.9837237540466),
                        new LatLng(19.05596635962743, -96.98353063499889),
                        new LatLng(19.05558100510019, -96.98157798685001),
                        new LatLng(19.050003405122425, -96.97065603181942),
                        new LatLng(19.048887862626046, -96.97033416673993),
                        new LatLng(19.04716382765361, -96.97046291277172),
                        new LatLng(19.048421360812835, -96.97503339690044),
                        new LatLng(19.04829966447197, -96.9755054656837),
                        new LatLng(19.048117119793336, -96.97522651594812),
                        new LatLng(19.04756948455261, -96.97574150007532),
                        new LatLng(19.04707497445419, -96.97518511807358),
                        new LatLng(19.047680454305986, -96.97431902146066),
                        new LatLng(19.046554771690474, -96.97043060854227),
                        new LatLng(19.042978259589727, -96.97202289490436),
                        new LatLng(19.044292856584338, -96.97793022304843),
                        new LatLng(19.043906920931576, -96.98100509579727),
                        new LatLng(19.044763214447848, -96.97980576783716),
                        new LatLng(19.049406419260784, -96.97883609842258),
                        new LatLng(19.048899894139804, -96.98173234785823),
                        new LatLng(19.04663257226769, -96.98238304707063),
                        new LatLng(19.04290589615008, -96.98164303620162),
                        new LatLng(19.041060617664336, -96.98837968694751),
                        new LatLng(19.042580260164677, -96.98784381700787),
                        new LatLng(19.0432074102471, -96.98882624521227),
                        new LatLng(19.046865440452137, -96.98827339391171),
                        new LatLng(19.04773528515169, -96.98953645147222),
                        new LatLng(19.045671528539202, -96.99213474131096),
                        new LatLng(19.047104221804044, -96.99377671613962),
                        new LatLng(19.054506273386327, -96.99328953679307),
                        new LatLng(19.05556368235391, -96.9929106195249),
                        new LatLng(19.056177658661216, -96.99278431376885)
                ).color(Color.RED).width(5));
    }

    private void togglePolylineVisibility() {
        if (ixp != null) {
            if (ixp.isVisible()) {
                ixp.setVisible(false);
            } else {
                ixp.setVisible(true);
            }
        }
    }

    private void animation(final ImageView imv) {
        Animation anim = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setDuration(100);
        anim.setFillAfter(true);

        AnimationSet anims = new AnimationSet(true);
        anims.addAnimation(anim);

        anims.setInterpolator(new DecelerateInterpolator());
        anims.setRepeatCount(1);
        anims.setRepeatMode(Animation.REVERSE);

        anims.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imv.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imv.startAnimation(anims);
    }

    @SuppressLint("PotentialBehaviorOverride")
    private void showFoodMarkers() {
        // Realizar una solicitud de lugares de tipo comida
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.TYPES);
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.builder(placeFields).build();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        placesClient.findCurrentPlace(request).addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                for (PlaceLikelihood placeLikelihood : task.getResult().getPlaceLikelihoods()) {
                    Place place = placeLikelihood.getPlace();
                    List<Place.Type> types = place.getTypes();
                    if (types != null && types.contains(Place.Type.FOOD)) {
                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(place.getLatLng())
                                .title(place.getName());

                        Marker marker = mMap.addMarker(markerOptions);
                        marker.setTag(place.getLatLng()); // Guardar las coordenadas del marcador en el tag

                        mMap.setOnMarkerClickListener(marker3 -> {
                            LatLng latLng = (LatLng) marker3.getTag();

                            // Obtener la dirección del lugar utilizando la API de Geocodificación inversa
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                                if (!addresses.isEmpty()) {
                                    String address = addresses.get(0).getAddressLine(0);
                                    marker3.setSnippet(address);
                                    marker3.showInfoWindow();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            return true;
                        });

                        foodMarkers.add(marker);
                    }
                }
            }
        });
    }


    @SuppressLint("PotentialBehaviorOverride")
    private void showHotelMarkers() {
        // Realizar una solicitud de lugares de tipo comida
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.TYPES);
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.builder(placeFields).build();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        placesClient.findCurrentPlace(request).addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                for (PlaceLikelihood placeLikelihood : task.getResult().getPlaceLikelihoods()) {
                    Place place = placeLikelihood.getPlace();
                    List<Place.Type> types = place.getTypes();
                    if (types != null && types.contains(Place.Type.LODGING)) {
                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(place.getLatLng())
                                .title(place.getName());

                        Marker marker = mMap.addMarker(markerOptions);
                        marker.setTag(place.getLatLng()); // Guardar las coordenadas del marcador en el tag

                        mMap.setOnMarkerClickListener(marker3 -> {
                            LatLng latLng = (LatLng) marker3.getTag();

                            // Obtener la dirección del lugar utilizando la API de Geocodificación inversa
                            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                                if (!addresses.isEmpty()) {
                                    String address = addresses.get(0).getAddressLine(0);
                                    marker3.setSnippet(address);
                                    marker3.showInfoWindow();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            return true;
                        });

                        hotelMarkers.add(marker);
                    }
                }
            }
        });
    }
    private void showMountain() {
        if (mMap != null) {
            marker1 = mMap.addMarker(new MarkerOptions().
                    position(new LatLng(19.043812366349872, -96.97159645134687))
                    .title("Finca de Laurita")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            marker2 = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.045532453669413, -97.00541683381434))
                    .title("Finca La Merino")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        }
    }
    private void deleteMountain() {
        if (marker1 != null) {
            marker1.remove();
            marker1 = null;
        }
        if (marker2 != null) {
            marker2.remove();
            marker2 = null;
        }
    }

    private boolean areMarkersVisible() {
        return marker1 != null && marker2 != null;
    }
}

