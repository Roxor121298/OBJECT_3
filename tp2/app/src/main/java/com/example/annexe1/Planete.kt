package com.example.annexe1

 // classe d'exemple de note de cours parce que mon annexe narrive plus atrouver le activity_main res file
class Planete (var nom:String, private var nbSatellite: Int){
    init {
        if(nom == "Terre")
            nbSatellite = 5
    }


    fun getNom(): String {
        val n = ""
        this.nom = n
        return n
    }
}
