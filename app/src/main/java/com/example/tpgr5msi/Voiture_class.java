package com.example.tpgr5msi;

import org.json.JSONObject;
public class Voiture_class {
    private final int id;
    private final String modele;
    private final String Marque;
    private final String Carburant;
    private final double prix;

    public Voiture_class(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.modele = jObject.optString("modele");
        this.Marque = jObject.optString("Marque");
        this.Carburant = jObject.optString("Carburant");
        this.prix = jObject.optDouble("prix");
    }

    public int getId() {
        return id;
    }

    public String getModele() {
        return modele;
    }

    public String getMarque() {
        return Marque;
    }

    public String getCarburant() {
        return Carburant;
    }

    public double getPrix() {
        return prix;
    }
}
