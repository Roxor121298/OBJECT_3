package com.example.tp1

import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable
import java.sql.Date
import java.time.LocalDate

class Memo (var memoTexte:String, private var date: LocalDate) :Serializable{
    init {
        this.memoTexte = memoTexte
        this.date = date
    }
}