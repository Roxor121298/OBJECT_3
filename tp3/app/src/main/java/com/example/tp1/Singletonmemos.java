package com.example.tp1;

import java.util.ArrayList;

public class Singletonmemos {
    //instance unique de la classe Singleton GestionBD
    private static Singletonmemos instance;

    private ArrayList<Memo> liste;

    private Singletonmemos()
    {
        liste = new ArrayList<>();
    }

    // méthode de base pour un Singleton
    public static Singletonmemos getInstance()
    {
        if (instance == null)
            instance = new Singletonmemos();
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
}