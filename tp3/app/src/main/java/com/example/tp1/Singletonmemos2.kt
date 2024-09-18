package com.example.tp1

import android.content.Context
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class Singletonmemos2 private constructor(private val contexte: Context) {
    var liste: ArrayList<Memo>
        private set

    init {
        liste = ArrayList()
    }

    fun ajoutermemo(m: Memo) {
        liste.add(m)
    }

    @Throws(Exception::class)
    fun serializerListe() {
        // try-with -ressource

        contexte.openFileOutput("fichier.ser", Context.MODE_PRIVATE).use { fos ->
            ObjectOutputStream(fos).use { oos ->
                oos.writeObject(liste)
            }
        }
    }

    @Throws(Exception::class)
    fun deserializerListe() {
        // try-with -ressource

        contexte.openFileInput("fichier.ser").use { fis ->
            ObjectInputStream(fis).use { ois ->
                liste = ois.readObject() as ArrayList<Memo>
            }
        }
    }

    companion object {
        //instance unique de la classe Singleton GestionBD
        private var instance: Singletonmemos2? = null

        // méthode de base pour un Singleton
        fun getInstance(contexte: Context): Singletonmemos2? {
            if (instance == null) instance = Singletonmemos2(contexte)
            return instance
        }
    }
}