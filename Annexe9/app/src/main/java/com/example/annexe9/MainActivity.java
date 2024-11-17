package com.example.annexe9;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Déclaration des TextViews pour afficher les réponses
    TextView reponse1, reponse2, reponse3, reponse4, reponse5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuration de l'affichage
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des TextViews
        reponse1 = findViewById(R.id.reponse1);
        reponse2 = findViewById(R.id.reponse2);
        reponse3 = findViewById(R.id.reponse3);
        reponse4 = findViewById(R.id.reponse4);
        reponse5 = findViewById(R.id.reponse5);

        // URL du JSON en ligne
        String url = "https://api.jsonbin.io/v3/b/6733b233ad19ca34f8c9149a?meta=false";

        // Appel de la méthode pour effectuer une requête avec Volley
        fetchJsonWithVolley(url);

        // Lecture du fichier JSON local et traitement avec GSON
        processLocalJson();
    }

    /**
     * Méthode pour effectuer une requête JSON en ligne avec Volley.
     */
    private void fetchJsonWithVolley(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Extraction et affichage des données
                            JSONObject menu = response.getJSONObject("menu");
                            String header = menu.getString("header");
                            Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_LONG).show();
                            // Affichage dans les TextViews
                            reponse1.setText(header);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Erreur de requête : " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }

    /**
     * Méthode pour lire un fichier JSON local et le traiter avec GSON.
     */
    private void processLocalJson() {
        // Lecture du fichier JSON local
        String jsonString = readLocalJson();

        // Traitement du JSON avec GSON
        if (jsonString != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Voiture>>() {}.getType();
            List<Voiture> voitures = gson.fromJson(jsonString, listType);

            // Calcul et affichage de la moyenne des prix
            int total = 0;
            for (Voiture voiture : voitures) {
                total += voiture.getPrix();
            }
            float moyenne = (float) total / voitures.size();
            reponse2.setText("Prix moyen : " + moyenne);
        }
    }

    /**
     * Méthode pour lire le contenu d'un fichier JSON local.
     */
    private String readLocalJson() {
        InputStream is = getResources().openRawResource(R.raw.voiture);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur de lecture du fichier JSON", Toast.LENGTH_LONG).show();
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonBuilder.toString();
    }
}
