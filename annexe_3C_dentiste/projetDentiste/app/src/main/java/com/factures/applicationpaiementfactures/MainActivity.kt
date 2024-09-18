package com.factures.applicationpaiementfactures

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText

import android.widget.LinearLayout

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children


class MainActivity : AppCompatActivity() {

    lateinit var dent1: LinearLayout
    lateinit var dent2: LinearLayout

    private lateinit var singletonDents: DentSingleton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        singletonDents = DentSingleton.getInstance(applicationContext)

        dent1 = findViewById(R.id.dent1)
        dent2 = findViewById(R.id.dent2)



    }


    override fun onStop() {
        super.onStop()

        ecrireDent()
        println("stop")


    }

    fun ecrireDent(){
        var child1 = dent1.getChildAt(0) as? EditText
        var child2 = dent1.getChildAt(1) as? CheckBox
        var child3 = dent1.getChildAt(2) as? EditText

        var input1 = child1?.text?.toString()?.toIntOrNull() ?: 0
        val input2 = child2?.isChecked ?: false
        var input3 = child3?.text?.toString() ?: ""

        var dent = Dent(input1,input2,input3)

        singletonDents.ajouterdent(dent)

        try {
            singletonDents.serializerListe()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
/*
    override fun onPause() {
        super.onPause()
        println("pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("destroy")
    }
*/

}