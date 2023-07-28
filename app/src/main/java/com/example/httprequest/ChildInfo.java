package com.example.httprequest;

public class ChildInfo {
    private String username;
    private String age;
    private String race;
    private String religion;
    private String childId;
    private String location;
    private String profilePic;
    private String aboutMe;
    private String type;
    private String field;
    private String gender;
    private String heightFt;
    private String heightIn;
    private String personId;

    public ChildInfo(String username, String age, String race, String religion, String childId, String location, String profilePic, String aboutMe, String type, String field, String gender, String heightFt, String heightIn, String personId) {
        this.username = username;
        this.age = age;
        this.race = race;
        this.religion = religion;
        this.childId = childId;
        this.location = location;
        this.profilePic = profilePic;
        this.aboutMe = aboutMe;
        this.type = type;
        this.field = field;
        this.gender = gender;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeightFt() {
        return heightFt;
    }

    public void setHeightFt(String heightFt) {
        this.heightFt = heightFt;
    }

    public String getHeightIn() {
        return heightIn;
    }

    public void setHeightIn(String heightIn) {
        this.heightIn = heightIn;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
