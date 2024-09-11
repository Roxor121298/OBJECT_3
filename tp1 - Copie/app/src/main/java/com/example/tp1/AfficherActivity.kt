package com.example.tp1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Vector

class AfficherActivity : AppCompatActivity() {

    lateinit var liste: ListView

    lateinit var array: ArrayAdapter<*>
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fin)

        liste = findViewById(R.id.bigliste)

        val adpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, lesMemos())
        liste.setAdapter(adpater)


    }

    fun lesMemos(): Vector<String> {
        var vecteur = Vector<String>()
        val fis : FileInputStream = openFileInput("fichier.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)

        br.forEachLine { s -> vecteur.add(s) }
        br.close()
        return vecteur

    }

}

