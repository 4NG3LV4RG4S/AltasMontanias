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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class tradiciones_ixhua extends AppCompatActivity {
    ImageView btn;
    ImageView t1, t2, t3, t4, t5, t6, t7;
    private int[] images = {R.drawable.trad_ixhua_pina, R.drawable.danzas_ixhuatlan, R.drawable.trad_ixhua_macuilillo, R.drawable.trad_ixhua_viuda, R.drawable.ixhua5};
    private String[] titles = {"Fiesta Patronal", "Danzas de Santiagos", "Tamales de hoja de Macuilillo", "La Viuda del Café", "Título 5", "Título 6", "Título 7"};
    private String[] descriptions = {"Fiesta patronal en honor al Señor del Piña en el municipio de Ixhuatlan del Café, es una fiesta Conmemorativa que se festeja del 24 de Febrero al 5 de Marzo de cada año.", "Las Danzas de Santiagos del municipio de Ixhuatlan son una de las más representativas del estado de Veracruz y de Mexico, se caracterizan por tener trajes tipicos artesanales, mascaras de madera de Gasparito, etc.", "Hojas de ", "Descripción 4", "Descripción 5", "Descripción 6", "Descripción 7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tradiciones_ixhua);
        btn = findViewById(R.id.btn_back);

        t1 = findViewById(R.id.imv_trad1);
        t2 = findViewById(R.id.imv_trad2);
        t3 = findViewById(R.id.imv_trad3);
        t4 = findViewById(R.id.imv_trad4);
        t5 = findViewById(R.id.imv_trad5);
        t6 = findViewById(R.id.imv_trad6);
        t7 = findViewById(R.id.imv_trad7);

        btn.setOnClickListener(view -> {
            animation(btn);
            Intent i = new Intent(getApplicationContext(), Ixhua.class);
            startActivity(i);
            finish();
        });

        t1.setOnClickListener(view -> {
            animation(t1);
            showImageDialog(0);
        });

        t2.setOnClickListener(view -> {
            animation(t2);
            showImageDialog(1);
        });

        t3.setOnClickListener(view -> {
            animation(t3);
            showImageDialog(2);
        });

        t4.setOnClickListener(view -> {
            animation(t4);
            showImageDialog(3);
        });

        t5.setOnClickListener(view -> {
            animation(t5);
        });

        t6.setOnClickListener(view -> {
            animation(t6);
        });

        t7.setOnClickListener(view -> {
            animation(t7);
        });
    }

    private void showImageDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        ImageView dialogImageView = dialogView.findViewById(R.id.selected_image);
        TextView dialogTitleTextView = dialogView.findViewById(R.id.title);
        TextView dialogDescriptionTextView = dialogView.findViewById(R.id.description);

        dialogImageView.setImageResource(images[position]);
        dialogTitleTextView.setText(titles[position]);
        dialogDescriptionTextView.setText(descriptions[position]);

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
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
}