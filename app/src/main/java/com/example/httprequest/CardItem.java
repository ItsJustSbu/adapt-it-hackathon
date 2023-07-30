package com.example.httprequest;

import java.util.ArrayList;

public class CardItem {
    private int imageResource;
    private String text;

    private ArrayList<String> Symptoms;
    private ArrayList<String> Treatment;

    private Boolean picvis;

    public CardItem(int imageResource, String text, ArrayList<String> Symptoms, ArrayList<String> Treatment, Boolean picvis) {
        this.imageResource = imageResource;
        this.text = text;
        this.Symptoms = Symptoms;
        this.Treatment = Treatment;
        this.picvis = picvis;
    }

    public int getImageResource() {
        return imageResource;
    }


    public String getText() {
        return text;
    }

    public ArrayList<String> getSymptoms() {
        return Symptoms;
    }
    public ArrayList<String> getTreatments() {
        return Treatment;
    }

    public Boolean getPicVis(){ return picvis;}
}
