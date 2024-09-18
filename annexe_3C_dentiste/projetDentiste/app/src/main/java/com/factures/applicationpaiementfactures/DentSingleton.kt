package com.factures.applicationpaiementfactures

import android.content.Context
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class DentSingleton private constructor(private val contexte: Context) {

    var liste: ArrayList<Dent> = ArrayList()

    companion object {
        @Volatile
        private var instance: DentSingleton? = null

        fun getInstance(contexte: Context): DentSingleton {
            return instance ?: synchronized(this) {
                instance ?: DentSingleton(contexte).also { instance = it }
            }
        }
    }

    fun ajouterdent(d: Dent) {
        liste.add(d)
    }

    @Throws(Exception::class)
    fun serializerListe() {
        contexte.openFileOutput("fichier.ser", Context.MODE_PRIVATE).use { fos ->
            ObjectOutputStream(fos).use { oos ->
                oos.writeObject(liste)
            }
        }
    }

    @Throws(Exception::class)
    fun deserializerListe() {
        contexte.openFileInput("fichier.ser").use { fis ->
            ObjectInputStream(fis).use { ois ->
                @Suppress("UNCHECKED_CAST")
                liste = ois.readObject() as ArrayList<Dent>
            }
        }
    }
}