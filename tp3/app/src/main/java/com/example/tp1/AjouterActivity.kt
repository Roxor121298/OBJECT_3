package com.example.tp1

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.Buffer
import java.time.LocalDate


class AjouterActivity : AppCompatActivity() {
    lateinit var ajouterFlux: Button
    lateinit var champ: EditText

    lateinit var ajouterDate: Button
    lateinit var champdate: EditText

    val ecouteur = Ecouteur();
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.milieu)

        ajouterFlux = findViewById(R.id.ajouterFlux)
        champ = findViewById(R.id.champ)

        ajouterDate = findViewById(R.id.ajouterDate)
        champdate = findViewById(R.id.champdate)

        ajouterFlux.setOnClickListener(ecouteur);

        ajouterDate.setOnClickListener(ecouteur);

    }

    inner class Ecouteur : OnClickListener, OnDateSetListener {
        var date: LocalDate = LocalDate.now();
        override fun onClick(v: View?) {
            var texteMemo = champ.text.toString()

            if( v== ajouterDate){
                val d = DatePickerDialog(this@AjouterActivity)
                d.setOnDateSetListener(this)
                d.show()
            }
            else { // ajouterFlux
                Singletonmemos.getInstance().ajoutermemo(Memo(texteMemo,date))
                finish()
            }

        }

        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            date = LocalDate.of(year, month + 1, dayOfMonth)
            champdate.setText(date.toString())
        }

    }
}
