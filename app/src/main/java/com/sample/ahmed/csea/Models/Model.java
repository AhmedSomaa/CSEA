package com.sample.ahmed.csea.Models;

public class Model {

    //
    private int img;
    private String heading;
    private String subheading;
    private String details;

    // Constructors
    public Model(int img, String heading, String subheading, String details) {
        this.img = img;
        this.heading = heading;
        this.subheading = subheading;
        this.details = details;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
