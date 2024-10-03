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

        val adpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, conversion())
        liste.setAdapter(adpater)


    }

    fun conversion(): Vector<String>{
        var listeTexteMemos = Vector<String>();
        var listeMemo = Singletonmemos.getInstance(applicationContext).liste
        for (memo in listeMemo){
            listeTexteMemos.add(memo.memoTexte)
        }
        return listeTexteMemos;
    }


}

