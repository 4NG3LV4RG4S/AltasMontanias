package com.example.altasmontaas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Huatusco extends AppCompatActivity {
    ImageView s,a,t,r,h,c, hi, art, bback;
    LinearLayout ix;
    private List<Imagen> imagenes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huatusco);

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

        imagenes.add(new Imagen("Los alrededores de Coscomatepec", "Se apreca parte del municipio desde una vista lejana " +
                "que incluye algunas cabezas de ganado (Una de las principales actividades del lugar).", R.drawable.coscom1));

        imagenes.add(new Imagen("Coscomatepec y el Citlaltépetl", "Vista del municipio y del volcan Citlaltépetl los cuales se encuentran" +
                "muy cercanos, siendo Coscomatepec el inicio de la ruta hacia el volcan.", R.drawable.coscom2));

        imagenes.add(new Imagen("Parque Recreativo Coscomatepec", "Un hermoso atardecer desde el parque recreativo, un lugar para conocer " +
                "más sobre la cultura de este municipio.", R.drawable.coscom3));

        imagenes.add(new Imagen("Coscomatepec", "El parque tematico visto desde fuera.", R.drawable.coscom4));

        imagenes.add(new Imagen("Celebracion de alfombras", "Alfombras hechas de acerrin pintado de colores, muy comunes en las celebraciones de " +
                "navidad, año nuevo y en fiestas patronales.", R.drawable.coscom5));

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
        });

        //Tradiciones
        t.setOnClickListener(view -> {
            animation(t);
        });

        //Rutas
        r.setOnClickListener(view -> {
            animation(r);
        });

        //Hoteles
        h.setOnClickListener(view -> {
            animation(h);
            Intent i = new Intent(getApplicationContext(), huaho.class);
            startActivity(i);


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