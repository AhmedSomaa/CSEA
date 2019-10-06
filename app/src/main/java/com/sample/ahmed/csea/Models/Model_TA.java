package com.sample.ahmed.csea.Models;

public class Model_TA {

    private String OfficeHours;
    private String Name;
    private String Mail;
    private String Rating;
    private String Phone;
    private int imgURL;


    //constructor


    public Model_TA(String officeHours, String name, String mail, String rating, String phone, int imgURL) {
        this.OfficeHours = officeHours;
        this.Name = name;
        this.Mail = mail;
        this.Rating = rating;
        this.Phone = phone;
        this.imgURL = imgURL;
    }

    //setters

    public void setOfficeHours(String officeHours) {
        this.OfficeHours = officeHours;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setMail(String mail) {
        this.Mail = mail;
    }

    public void setRating(String rating) {this.Rating = rating;}

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }

    //getters
    public String getOfficeHours() {
        return OfficeHours;
    }

    public String getName() {
        return Name;
    }

    public String getMail() {
        return Mail;
    }

    public String getRating() {return Rating;}

    public String getPhone() {
        return Phone;
    }

    public int getImgURL() {
        return imgURL;
    }
}
