package com.example.annexe8b;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Animation_titre extends AppCompatActivity {

    TextView animation_titre;
    Boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animation_titre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        animation_titre = findViewById(R.id.animation_titre);
        isVisible = true;
        animation_titre.setOnClickListener(v -> animation_titre());
    }

    private void animation_titre(){
        float target = isVisible ? 0.5f : 2f;

        AnimatorSet animatorSet = new AnimatorSet();


        ObjectAnimator animex =  ObjectAnimator.ofFloat(animation_titre, "ScaleX", target);
        animex.setDuration(300); // sur combien de temps on fait cette animation

        ObjectAnimator animey =  ObjectAnimator.ofFloat(animation_titre, "ScaleY", target);
        animey.setDuration(300); // sur combien de temps on fait cette animation

        animatorSet.playTogether(animex,animey);


        animatorSet.start();
        isVisible = !isVisible;
    }
}