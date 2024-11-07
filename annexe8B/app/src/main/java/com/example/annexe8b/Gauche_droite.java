package com.example.annexe8b;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Gauche_droite extends AppCompatActivity {

    TextView gauche_droite;
    Boolean isVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gauche_droite);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        isVisible = true;
        gauche_droite = findViewById(R.id.gauche_droite);
        gauche_droite.setOnClickListener(v -> toggleContainerVisibility());

    }

    private void toggleContainerVisibility() {
        float targetTranslationY = isVisible ? 100f : -100f; // vecteur(math == direction) pour l'animation
        ObjectAnimator animator = ObjectAnimator.ofFloat(gauche_droite, "translationX", targetTranslationY); // parametre pour un animation
        animator.setDuration(300); // sur combien de temps on fait cette animation
        animator.setInterpolator(new BounceInterpolator()); // pour mettre des mods sur l'animation
        animator.start();
        isVisible = !isVisible;
    }
}