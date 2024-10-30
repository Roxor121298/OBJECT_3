package com.example.annexe6_thread;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Auto vert, jaune;

    ImageView ImageJaune, ImageVert;
    Handler h;
    int screenWidth; // Store screen width

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageJaune = findViewById(R.id.jaune);  // ImageView for yellow car
        ImageVert = findViewById(R.id.vert);    // ImageView for green car

        // Get screen width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;

        vert = new Auto("vert");
        jaune = new Auto("jaune");
        jaune.start();
        vert.start();
        h = new Handler();
    }

    public class Auto extends Thread {
        private int positionX;
        private String couleur;
        private boolean arret;

        public Auto(String couleur) {
            super(couleur); // Thread name
            this.couleur = couleur;
            positionX = 0;
        }

        public void run() {
            while (!arret) {
                System.out.println("In the run of Thread " + Thread.currentThread().getName());
                positionX = positionX + new Random().nextInt(80); // Move randomly

                // If the ImageView reaches the screen width, reset position to 0

                if (positionX >= screenWidth) {
                    positionX = 0;
                }

                h.post(new Thread() {
                    public void run() {
                        if (couleur.equals("jaune")) {
                            ImageJaune.setTranslationX(positionX);  // Move yellow car horizontally
                        } else if (couleur.equals("vert")) {
                            ImageVert.setTranslationX(positionX);   // Move green car horizontally
                        }
                    }
                });


                // Pour que les auto aille plus vite
                /*try {
                    Thread.sleep(1000);  // Wait for 1 second before moving again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}
