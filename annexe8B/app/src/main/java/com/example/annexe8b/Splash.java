package com.example.annexe8b;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {


    Boolean ui;
    TextView splash;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        splash = findViewById(R.id.splash);
        image = findViewById(R.id.image);
        ui = true;

        splash.setOnClickListener(v -> SoleilAnimation());
    }

    private void Animation(){
        AnimatorSet animatorSet = new AnimatorSet();

    }

    private void descendreAnimation(){


    }

    private void changerCouleurAnimation(){

    }

    private void SoleilAnimation(){
        float targetScale = ui ? 8f : 0.5f;
        float targetMove = ui ? 550f : -550f;
        int duration = 400;

        AnimatorSet scale = new AnimatorSet();
        AnimatorSet full = new AnimatorSet();

        ObjectAnimator animatormove = ObjectAnimator.ofFloat(image, "translationY", targetMove);
        animatormove.setDuration(duration);

        ObjectAnimator animatorChangeColor = ObjectAnimator.ofArgb(splash, "textColor", Color.BLACK, Color.WHITE);
        animatorChangeColor.setDuration(duration);

        ObjectAnimator animatory = ObjectAnimator.ofFloat(image, "ScaleY", targetScale);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(image, "ScaleX", targetScale);
        animatorx.setDuration(duration);
        animatory.setDuration(duration);

        scale.playTogether(animatory,animatorx,animatorChangeColor);

        full.playSequentially(animatormove, scale);
        full.start();
        ui = !ui;

    }






}