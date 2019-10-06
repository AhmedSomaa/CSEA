package com.sample.ahmed.csea.Models;

public class Model_News {

    //  variables
    private String categoryLogo;
    private String categoryName;
    private String eventImage;
    private String eventHeading;
    private String eventDetail;

    //  Constructors
    public Model_News(String categoryLogo, String categoryName, String eventImage, String eventHeading, String eventDetail) {
        this.categoryLogo = categoryLogo;
        this.categoryName = categoryName;
        this.eventImage = eventImage;
        this.eventHeading = eventHeading;
        this.eventDetail = eventDetail;
    }

    //  Setters
    public void setCategoryLogo(String categoryLogo) {
        this.categoryLogo = categoryLogo;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public void setEventHeading(String eventHeading) {
        this.eventHeading = eventHeading;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    //  Getters

    public String getCategoryLogo() {
        return categoryLogo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getEventImage() {
        return eventImage;
    }

    public String getEventHeading() {
        return eventHeading;
    }

    public String getEventDetail() {
        return eventDetail;
    }
}
