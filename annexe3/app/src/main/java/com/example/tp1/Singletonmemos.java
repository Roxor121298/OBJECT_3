package com.example.tp1;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Singletonmemos {
    //instance unique de la classe Singleton GestionBD
    private static Singletonmemos instance;

    private ArrayList<Memo> liste;

    private Context contexte;

    private Singletonmemos(Context contexte)
    {
        liste = new ArrayList<>();
        this.contexte = contexte;
    }

    // méthode de base pour un Singleton
    public static Singletonmemos getInstance(Context contexte)
    {
        if (instance == null)
            instance = new Singletonmemos(contexte);
        return instance;
    }

    public ArrayList<Memo> getListe()
    {
        return liste;
    }

    public void ajoutermemo (Memo m)
    {
        liste.add(m);
    }

    public void serializerListe() throws Exception {

        // try-with -ressource
        try (

            FileOutputStream fos = contexte.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            // Buffer spécial pour traiter des objects
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(liste);
        }
    }

    public void deserializerListe() throws Exception {

        // try-with -ressource
        try (

                FileInputStream fis = contexte.openFileInput("fichier.ser");
                // Buffer spécial pour traiter des objects
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            liste = (ArrayList<Memo>) ois.readObject();
        }
    }
}