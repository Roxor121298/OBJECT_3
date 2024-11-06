package com.example.annexe8b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button gauche_droite, animation_titre, splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        gauche_droite = findViewById(R.id.gauche_droite);
        animation_titre = findViewById(R.id.animation_titre);
        splash = findViewById(R.id.splash);

        Ecouteur ec = new Ecouteur(MainActivity.this);
        gauche_droite.setOnClickListener(ec);
        animation_titre.setOnClickListener(ec);
        splash.setOnClickListener(ec);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public class Ecouteur implements View.OnClickListener {

        private Context context;

        // Constructor to accept the context and any views
        public Ecouteur(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            if(v == gauche_droite){
                Intent intent = new Intent(context, Gauche_droite.class);
                context.startActivity(intent);
            }

            else if (v == animation_titre){
                Intent intent = new Intent(context, Animation_titre.class);
                context.startActivity(intent);

            }
            else if (v == splash) {
                Intent intent = new Intent(context, Splash.class);
                context.startActivity(intent);

            }

        }
    }



}