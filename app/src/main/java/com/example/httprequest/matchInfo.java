package com.example.httprequest;

public class matchInfo {
    private String username;
    private String email;
    private String age;
    private String phoneNumber;
    private String profilePic;
    private String aboutMe;
    private String location;
    private String field;
    private String race;

    public matchInfo(String username, String email, String age, String phoneNumber, String profilePic, String aboutMe, String location, String field, String race) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.profilePic = profilePic;
        this.aboutMe = aboutMe;
        this.location = location;
        this.field = field;
        this.race = race;
    }

    // Getters and Setters for each attribute

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return  "+27 " + phoneNumber.substring(0, 2) + " " + phoneNumber.substring(2, 5) + " " + phoneNumber.substring(5);

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
