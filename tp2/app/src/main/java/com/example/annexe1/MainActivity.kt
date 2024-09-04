package com.example.annexe1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Vector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retourner()
        signer()
    }

    fun retournerLigneProf(): Int{
        val fis : FileInputStream = openFileInput("fichier_annexe1.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        var compteur = 0

        while (br.readLine() != null){
            compteur++
        }

        br.close()
        return compteur
    }

    fun retournerCharProf(): Int{
        var compteurChar = 0
        val fis : FileInputStream = openFileInput("fichier_annexe1.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        var ligne = br.readLine()

        while (ligne != null){
            compteurChar += ligne.length
            ligne = br.readLine()
        }

        br.close()
        return compteurChar
    }


    fun nombreDeCProf() : Int {
        var compteurChar = 0
        val fis : FileInputStream = openFileInput("fichier_annexe1.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        var c:Int = br.read()
        while(c != -1){
            if (c.toChar() == 'c' || c.toChar() == 'C')
                compteurChar ++
            c = br.read()
        }
        br.close()
        return compteurChar
    }

    // la ligne (le nom) est passé en paramètre
    fun signerProf(ligne:String){
        val fos : FileOutputStream = openFileOutput("fichier_annexe1.txt", MODE_APPEND)
        val osw = OutputStreamWriter(fos)
        val bw = BufferedWriter(osw)

        bw.newLine();
        bw.write(ligne)

        bw.close()
    }

    fun retourner() {
        var char = 0
        var c = 0
        var ligne = 0
        val fis : FileInputStream = openFileInput("fichier_annexe1.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)


        br.forEachLine { line ->
            ligne += 1
            char += line.filter { it != ' ' }.length
            c += line.filter { it == 'c' || it == 'C' }.length
        }


        println("nombre de lignes : $ligne")
        println("nombre de characteres : $char")
        println("nombre de 'C' et 'c' : $c")

        br.close()
        return
    }


    fun signer(){

        // val bwOptimisé : BufferedWriter = openFileOutput("fichier_annexe1.txt", MODE_APPEND).bufferedWriter()

        val nom = "Edouard"
        val fos : FileOutputStream = openFileOutput("fichier_annexe1.txt", MODE_APPEND)
        val osw = OutputStreamWriter(fos)
        val bw = BufferedWriter(osw)



        bw.write("\n$nom")

        bw.close()

    }

}