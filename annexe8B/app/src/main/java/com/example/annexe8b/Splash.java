package com.example.annexe8b;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

        image.setOnClickListener(v -> SoleilAnimation());
    }

    private void SoleilAnimation(){
        float target = ui ? 0.5f : 5f;
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatory = ObjectAnimator.ofFloat(image, "ScaleY", target);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(image, "ScaleX", target);

        animatorx.setDuration(300);
        animatory.setDuration(300);

        animatorSet.playTogether(animatory,animatorx);


        animatorSet.start();
        ui = !ui;


    }


}