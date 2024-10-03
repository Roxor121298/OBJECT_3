package com.example.atelier_2;

public class Produit {

    private String nom;
    private String prix;

    public Produit (Builder b)
    {
        this.nom = b.nom;
        this.prix = b.prix;

    }

    public String getNom() {return nom;}
    public String getPrix() {return prix;}
    private static class Builder {
        private String nom;
        private String prix;

        public Builder setNom(String nom){
            this.nom = nom;
            return this;
        }

        public Builder setPrix(String prix){
            this.prix = prix;
            return this;
        }

        public Produit build(){
            return new Produit(this);
        }



    }



}
