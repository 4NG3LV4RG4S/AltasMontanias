package com.example.altasmontaas;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Ixhua extends AppCompatActivity {
    ImageView s,a,t,r,h,c, hi, art, bback;
    LinearLayout ix;
    private List<Imagen> imagenes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ixhua);

        s = findViewById(R.id.Sitios);
        a = findViewById(R.id.Actividades);
        t = findViewById(R.id.Tradiciones);
        r = findViewById(R.id.Ruta);
        h = findViewById(R.id.Hoteles);
        c = findViewById(R.id.Conciencia);
        hi = findViewById(R.id.Historia);
        art = findViewById(R.id.Artesania);
        ix = findViewById(R.id.MarqueeIxhua);
        bback = findViewById(R.id.bBack);

        imagenes.add(new Imagen("Taza Gigante de Café", "Escultura de una Cafetera sirviendo Café, ubicada en el Parque Centenario", R.drawable.ixhua1));
        imagenes.add(new Imagen("Bienvenida y Mirador", "Arco de la Entrada a Ixhuatla y Mirador del Café", R.drawable.ixhua2));
        imagenes.add(new Imagen("Ixhuatlan del Café", "Explanada del Parque Bicentenario", R.drawable.ixhua3));
        imagenes.add(new Imagen("El Cortador de Café", "Escultura del Cortador del Café, el Eslabon más Importante", R.drawable.ixhua4));
        imagenes.add(new Imagen("Cabecera Municipal", "Vista Aerea de la Cabecera Municipal", R.drawable.ixhua5));

        for (Imagen imagen : imagenes) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(600,800));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imagen.getResourceId());
            imageView.setOnClickListener(v ->
                    imageDialog(imagen));

            ix.addView(imageView);
        }
        botones();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void imageDialog(Imagen imv){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout, null);
        ImageView selectedImage = view.findViewById(R.id.selected_image);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);
        selectedImage.setImageDrawable(getResources().getDrawable(imv.getResourceId()));
        title.setText(imv.getTitulo());
        description.setText(imv.getDescripcion());

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void animation(final ImageView imv){
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

    public void botones(){
        //Sitios
        s.setOnClickListener(view -> {
            animation(s);
            Intent i = new Intent(getApplicationContext(), SitiosIxhua.class);
            startActivity(i);
        });

        //Actividades
        a.setOnClickListener(view -> {
            animation(a);
            Intent i = new Intent(getApplicationContext(), ActividadesIxhua.class);
            startActivity(i);
        });

        //Tradiciones
        t.setOnClickListener(view -> {
            animation(t);
            Intent i = new Intent(getApplicationContext(), tradiciones_ixhua.class);
            startActivity(i);
        });

        //Rutas
        r.setOnClickListener(view -> {
            animation(r);
        });

        //Hoteles
        h.setOnClickListener(view -> {
            animation(h);
        });

        //Conciencia
        c.setOnClickListener(view -> {
            animation(c);
        });

        //Historia
        hi.setOnClickListener(view -> {
            animation(hi);
        });

        //Artesano
        art.setOnClickListener(view -> {
            animation(art);
        });

        //Regresar
        bback.setOnClickListener(view -> {
            animation(bback);
            Intent i = new Intent(getApplicationContext(), Municipios.class);
            startActivity(i);
            finish();
        });
    }

}