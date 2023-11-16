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

public class Cordoba extends AppCompatActivity {

    ImageView s,a,t,r,h,c, hi, art, bback;
    LinearLayout crd;
    private List<Imagen> imagenes = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cordoba);

        s = findViewById(R.id.Sitios);
        a = findViewById(R.id.Actividades);
        t = findViewById(R.id.Tradiciones);
        r = findViewById(R.id.Ruta);
        h = findViewById(R.id.Hoteles);
        c = findViewById(R.id.Conciencia);
        hi = findViewById(R.id.Historia);
        art = findViewById(R.id.Artesania);
        crd = findViewById(R.id.marqueeCordoba);
        bback = findViewById(R.id.bBack);

        imagenes.add(new Imagen("Palacio Municipal de Cordoba", "Una vista panoramica del palacio municipal de la ciudad de Cordoba, ubicado" +
                "en el parque 21 de Mayo.", R.drawable.cordoba1));

        imagenes.add(new Imagen("Parque 21 de Mayo", "El parque 21 de Mayo es el parque más representativo de la ciudad de Cordoba, siendo" +
                "el principal, rodeado de los famosos portales con una variedad de restaurantes, comercios, museos, etc.", R.drawable.cordoba2));

        imagenes.add(new Imagen("El Mexicano", "Sobre el bulevar Tratados de Córdoba y la avenida 11, La ‘Luisa’ como es conocida popularmente la locomotora- evoca tiempos pasados, cuando" +
                " un ejército de máquinas echando humo hacían vibrar las vías de “El Huatusquito”, la ruta que conectaba a Córdoba con el municipio de Coscomatepec.", R.drawable.cordoba3));

        imagenes.add(new Imagen("Barrio Las Estaciones" ,"El barrio Las Estaciones es una zona de la ciudad de Cordoba que cuenta con multiples" +
                "estaciones de trenes, rieles, cruces y señalamientos, todos muy antiguos, lo que refleja la actividad ferroviaria de la region.", R.drawable.cordoba4));

        imagenes.add(new Imagen("Catedral de la Inmaculada Concepcion", "Fachada de la Catedral de La Inmaculada Concepción. Fotografía" +
                " tomada por Enrique Carpio Martínez.", R.drawable.cordoba5));

        for (Imagen imagen : imagenes) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(600,800));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imagen.getResourceId());
            imageView.setOnClickListener(v ->
                    imageDialog(imagen));

            crd.addView(imageView);
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