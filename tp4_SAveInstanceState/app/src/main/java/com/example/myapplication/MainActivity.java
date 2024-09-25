package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    ActivityResultLauncher lanceur;

    Button connaitre, confirmer;
    TextView bonjour;
    EditText dateici, champdate;

    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        connaitre = findViewById(R.id.connaitre);
        bonjour = findViewById(R.id.bonjour);

        ec = new Ecouteur();
        connaitre.setOnClickListener(ec);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Version de Eric ici



    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View event) {


            if(event == connaitre) {
                setContentView(R.layout.donnee);
                confirmer = findViewById(R.id.confirmer);
                dateici = findViewById(R.id.dateici);
                champdate = findViewById(R.id.champdate);

                confirmer.setOnClickListener(ec);

            }

            else if(event == confirmer){
                setContentView(R.layout.activity_main);

                bonjour = findViewById(R.id.bonjour);


                connaitre = findViewById(R.id.connaitre);
                connaitre.setOnClickListener(ec);



            }


        }
    }
}