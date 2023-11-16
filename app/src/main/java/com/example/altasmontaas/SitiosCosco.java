package com.example.altasmontaas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class SitiosCosco extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    ImageView d, r, h, m;
    Polyline coscop;
    //Markers de restaurantes
    Marker marker1, marker2, marker3, marker4, marker5, marker10, marker11, marker12, marker13, marker14, marker15;
    //Markers de hoteles
    Marker marker6, marker7, marker8, marker9;
    boolean markersVisible = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios_cosco);

        d = findViewById(R.id.limites);
        r = findViewById(R.id.restaurants);
        h = findViewById(R.id.hotel);
        m = findViewById(R.id.montana);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapSitiosCosco);
        mapFragment.getMapAsync(this);

        r.setOnClickListener(view -> {
            animation(r);
            showRestaurants();
        });

        h.setOnClickListener(view -> {
            animation(h);
            showHotels();
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

        LatLng cosco = new LatLng(19.06836244371507, -97.04657281472494);
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(cosco, 15.0f);
        mMap.moveCamera(cam);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(19.043686800626595, -96.96523912769419)).title("Mirador de Café").snippet("mirador").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        coscop = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(19.08122460345467, -97.0353671866918),
                        new LatLng(19.08012754972869, -97.03426910361121),
                        new LatLng(19.07991999820643, -97.03373574897205),
                        new LatLng(19.077192154031362, -97.03310827292597),
                        new LatLng(19.073722982971624, -97.03539856049412),
                        new LatLng(19.074464264941124, -97.0364025221678),
                        new LatLng(19.070965384933025, -97.03655939117932),
                        new LatLng(19.069067656403625, -97.03994776182803),
                        new LatLng(19.06625067555464, -97.04026149985106),
                        new LatLng(19.063967403321872, -97.03960265000269),
                        new LatLng(19.0634039936483, -97.04415185133662),
                        new LatLng(19.06470872892278, -97.0472578577646),
                        new LatLng(19.065865190241198, -97.04732060536922),
                        new LatLng(19.065894842989447, -97.04898341689128),
                        new LatLng(19.06805947928465, -97.04910891210048),
                        new LatLng(19.06805947928465, -97.05723472689695),
                        new LatLng(19.070016523384442, -97.05924265024433),
                        new LatLng(19.084248876184752, -97.04688137211747),
                        new LatLng(19.078496780847527, -97.04032424743617),
                        new LatLng(19.08223270055716, -97.0374378576243),
                        new LatLng(19.081343203507092, -97.03533581287)
                ).color(Color.RED).width(5));
    }

    //Metodo para hacer visible la polilinea
    private void togglePolylineVisibility() {
        if (coscop != null) {
            if (coscop.isVisible()) {
                coscop.setVisible(false);
            } else {
                coscop.setVisible(true);
            }
        }
    }

    //Metodo para la animacion de los botones
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

    //Metodos para mostrar los Markers de restaurantes
    private void showRestaurants() {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_restaurantes);
        float scaleFactor = 0.2f; // Factor de escala deseado

        Bitmap btmp = BitmapFactory.decodeResource(getResources(), R.drawable.marker_restaurantes);
        int width = btmp.getWidth();
        int height = btmp.getHeight();

        int newWidth = (int) (width * scaleFactor);
        int newHeight = (int) (height * scaleFactor);

        Bitmap nB = Bitmap.createScaledBitmap(btmp, newWidth, newHeight, false);
        BitmapDescriptor iconN = BitmapDescriptorFactory.fromBitmap(nB);

        if (mMap != null) {
            if (markersVisible) {
                if (marker6 != null) {
                    marker6.remove();
                    marker6 = null;
                }
                if (marker7 != null) {
                    marker7.remove();
                    marker7 = null;
                }
                if (marker4 != null) {
                    marker4.remove();
                    marker4 = null;
                }
                if (marker5 != null) {
                    marker5.remove();
                    marker5 = null;
                }
                if (marker9 != null) {
                    marker9.remove();
                    marker9 = null;
                }if (marker10 != null) {
                    marker10.remove();
                    marker10 = null;
                }if (marker11 != null) {
                    marker11.remove();
                    marker11 = null;
                }if (marker12 != null) {
                    marker12.remove();
                    marker12 = null;
                }if (marker13 != null) {
                    marker13.remove();
                    marker13 = null;
                }if (marker14 != null) {
                    marker14.remove();
                    marker14 = null;
                }if(marker15 != null){
                    marker15.remove();
                    marker15 = null;
                }
                markersVisible = false;
            } else {

                marker6 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.07316772832248, -97.04476964385137))
                        .title("Restaurante Poyautecatl")
                        .icon(iconN));

                marker7 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.07269663331371, -97.04846526009966))
                        .title("Tere´s Restaurante")
                        .icon(iconN));

                marker4 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.071145465222845, -97.04457329243888))
                        .title("La casa de los Abuelos")
                        .icon(iconN));

                marker5 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.071589041209055, -97.04292312805609))
                        .title("Taqueria Reina")
                        .icon(iconN)
                        .snippet("Carretera Fortín-Huatusco, "));

                marker10 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.073461926206143, -97.04807511505817))
                        .title("Cafeteria 88 Centro")
                        .icon(iconN));

                marker9 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.073347885941995, -97.04575292891097))
                        .title("Los Wero´s")
                        .icon(iconN));

                marker11 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.068755337611908, -97.04891938373561))
                        .title("Restaurante El San Juanero")
                        .icon(iconN));

                marker12 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.073152637067285, -97.04462320706207))
                        .title("Pizzeria Angelotti")
                        .icon(iconN));

                marker13 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.073341232511755, -97.04708203957232))
                        .title("Los Portales")
                        .icon(iconN));

                marker14 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.074258983342933, -97.04953551272483))
                        .title("Taqueria Tacho")
                        .icon(iconN));

                marker15 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.06947340510964, -97.04489559685103))
                        .title("Los Caporales")
                        .icon(iconN));


                markersVisible = true;
            }
        }
    }

    //Metodo para mostrar Markers de Hoteles
    public void showHotels() {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_hotel);
        float scaleFactor = 0.1f; // Factor de escala deseado

        Bitmap btmp = BitmapFactory.decodeResource(getResources(), R.drawable.marker_hotel);
        int width = btmp.getWidth();
        int height = btmp.getHeight();

        int newWidth = (int) (width * scaleFactor);
        int newHeight = (int) (height * scaleFactor);

        Bitmap nB = Bitmap.createScaledBitmap(btmp, newWidth, newHeight, false);
        BitmapDescriptor iconN = BitmapDescriptorFactory.fromBitmap(nB);

        if (mMap != null) {
            if (markersVisible) {
                if (marker1 != null) {
                    marker1.remove();
                    marker1 = null;
                }
                if (marker2 != null) {
                    marker2.remove();
                    marker2 = null;
                }
                if (marker3 != null){
                    marker3.remove();
                    marker3 = null;
                }
                markersVisible = false;

            } else {
                marker1 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.07319712851767, -97.04573257525709))
                        .title("Hotel Plaza Real")
                        .icon(iconN));

                marker2 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.073147014145082, -97.04948308735194))
                        .title("Hotel Puente del Virrey")
                        .icon(iconN));

                marker3 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.07281871282689, -97.06204475150139))
                        .title("Rancho Santa Bárbara")
                        .icon(iconN));

                markersVisible = true;
            }
        }
    }
}