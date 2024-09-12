package com.example.tp1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var ajouter: Button
    lateinit var afficher: Button
    lateinit var quitter: Button

    var ec: Ecouteur? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.acceuil)
        ec = Ecouteur()
        ajouter = findViewById(R.id.ajouter)
        afficher = findViewById(R.id.afficher)
        quitter = findViewById(R.id.quitter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ajouter.setOnClickListener(ec)
        afficher.setOnClickListener(ec)
        quitter.setOnClickListener(ec)

        Singletonmemos.getInstance(applicationContext).deserializerListe();
    }

    override fun onStop() {
        super.onStop()
        // on veut sérialiser la liste ds un fichier pour la récpérer quand on va revenir
        try{
            Singletonmemos.getInstance(applicationContext).serializerListe();
        }
        catch (e:Exception){
            e.printStackTrace()
        }


    }

    inner class Ecouteur : View.OnClickListener {
        override fun onClick(event: View) {
            val i: Intent
            // il faut que ce soit le meme object (this et equal)
            // == est un equal tandit que === est pour deux reference au meme object
            if (event == ajouter) {
                i = Intent(this@MainActivity, AjouterActivity::class.java)
                startActivity(i)
            } else if (event == afficher) {
                i = Intent(this@MainActivity, AfficherActivity::class.java)
                startActivity(i)
            } else {
                System.exit(0)
            }
        }
    }
}