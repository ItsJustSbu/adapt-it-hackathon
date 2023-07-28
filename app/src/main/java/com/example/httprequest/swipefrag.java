package com.example.httprequest;

public class swipefrag {
    String title,height, description, date,location,age;
    int image;
    public swipefrag(String title, String description, String religion, String location, int image) {
        this.title= title;
//        this.height=height;
        this.description = description;
        this.date= religion;
        this.image=image;
        this.location =location;
//        this.age=age;
    }
    public String getTitle() {

        return title;
    }
    public void setTitle(String title) {

        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription (String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date= date;
    }
    public int getImage () {
        return image;
    }
    public void setImage (int image) {
        this.image = image;
    }
    public String getLocation() {

        return location;
    }
    public void setLocation(String location) {

        this.location = location;
    }
//    public String getAge() {
//
//        return age;
//    }
//    public void setAge(String age) {
//
//        this.age = age;
//    }
//    public String getHeight() {
//
//        return height;
//    }
//    public void setHeight(String height) {
//
//        this.height = height;
//    }
}