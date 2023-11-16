package com.example.altasmontaas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class ActividadesIxhua extends AppCompatActivity {
    ImageView a, dx, g, d, m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_ixhua);

        a = findViewById(R.id.act_artesanos);
        dx = findViewById(R.id.act_dExtremos);
        g = findViewById(R.id.act_gastronomia);
        d = findViewById(R.id.act_deportes);
        m = findViewById(R.id.act_medicos);

        a.setOnClickListener(view -> {
            animation(a);
        });

        dx.setOnClickListener(view -> {
            animation(dx);
        });

        g.setOnClickListener(view -> {
            animation(g);
        });

        d.setOnClickListener(view -> {
            animation(d);
        });

        m.setOnClickListener(view -> {
            animation(m);
        });
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