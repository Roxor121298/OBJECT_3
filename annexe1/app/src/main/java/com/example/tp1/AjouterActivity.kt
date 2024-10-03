package com.example.tp1

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.Buffer


class AjouterActivity : AppCompatActivity() {
    lateinit var ajouterFlux: Button
    lateinit var champ: EditText

    val ecouteur = Ecouteur();
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.milieu)

        ajouterFlux = findViewById(R.id.ajouterFlux)
        champ = findViewById(R.id.champ)

        ajouterFlux.setOnClickListener(ecouteur);

    }

    inner class Ecouteur : OnClickListener {
        override fun onClick(v: View?) {
            var texteMemo = champ.text.toString()
            val fos : FileOutputStream = openFileOutput("fichier.txt", MODE_APPEND) // append pour ajouter les valeur une a la suite des AUTRE ,
                                                                                            // sinon private pour écraser ce qu'il yt avait avant
            val osw = OutputStreamWriter(fos) // traduit en caractère
            val bw = BufferedWriter(osw) // plus rapide (avec le buffer)

            bw.write(texteMemo) // pour écrire dans le buffer
            bw.newLine() // pour changer de ligne
            bw.close()

            finish() // pour revenir au menu principal



        }

    }
}
