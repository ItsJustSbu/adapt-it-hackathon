package com.example.httprequest;

public class CardItem {
    private int imageResource;
    private String text;

    private String Symptoms;
    private String Treatment;

    public CardItem(int imageResource, String text, String Symptoms, String Treatment) {
        this.imageResource = imageResource;
        this.text = text;
        this.Symptoms = Symptoms;
        this.Treatment = Treatment;
    }

    public int getImageResource() {
        return imageResource;
    }


    public String getText() {
        return text;
    }

    public String getSymptoms() {
        return Symptoms;
    }
    public String getTreatments() {
        return Treatment;
    }
}
