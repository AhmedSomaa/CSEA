package com.sample.ahmed.csea.Models;

public class Model_Course {

    //  variables
    private String course_code;
    private String course_name;
    private String course_prerequisite;
    private String course_description;
    private String course_when_offered;
    private String course_credits;
    private String course_imgURL;


    //  constructor
    public Model_Course(String course_code, String course_name, String course_prerequisite, String course_description, String course_when_offered, String course_credits, String course_imgURL) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.course_prerequisite = course_prerequisite;
        this.course_description = course_description;
        this.course_when_offered = course_when_offered;
        this.course_credits = course_credits;
        this.course_imgURL = course_imgURL;
    }

    //  setters
    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_prerequisite(String course_prerequisite) {
        this.course_prerequisite = course_prerequisite;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public void setCourse_when_offered(String course_when_offered) {
        this.course_when_offered = course_when_offered;
    }

    public void setCourse_credits(String course_credits) {
        this.course_credits = course_credits;
    }

    public void setCourse_imgURL(String course_imgURL) {
        this.course_imgURL = course_imgURL;
    }

    //  getters

    public String getCourse_code() {
        return course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_prerequisite() {
        return course_prerequisite;
    }

    public String getCourse_description() {
        return course_description;
    }

    public String getCourse_when_offered() {
        return course_when_offered;
    }

    public String getCourse_credits() {
        return course_credits;
    }

    public String getCourse_imgURL() {
        return course_imgURL;
    }
}
