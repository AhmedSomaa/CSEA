package com.sample.ahmed.csea.Models;

public class Model_Professor {

    // variables
    private String prof_name;
    private String prof_pos;
    private String prof_email;
    private String prof_office;
    private String prof_brief;
    private String prof_research;
    private String prof_publication;
    private String prof_courses;
    private String prof_imgURL;

    //  constructor
    public Model_Professor(String prof_name, String prof_pos, String prof_email, String prof_office, String prof_brief, String prof_research, String prof_publication, String prof_courses, String prof_imgURL) {
        this.prof_name = prof_name;
        this.prof_pos = prof_pos;
        this.prof_email = prof_email;
        this.prof_office = prof_office;
        this.prof_brief = prof_brief;
        this.prof_research = prof_research;
        this.prof_publication = prof_publication;
        this.prof_courses = prof_courses;
        this.prof_imgURL = prof_imgURL;
    }

    //  another constructor


    public Model_Professor(String prof_name, String prof_email, String prof_imgURL) {
        this.prof_name = prof_name;
        this.prof_email = prof_email;
        this.prof_imgURL = prof_imgURL;
    }

    //  setters
    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public void setProf_pos(String prof_pos) {
        this.prof_pos = prof_pos;
    }

    public void setProf_email(String prof_email) {
        this.prof_email = prof_email;
    }

    public void setProf_office(String prof_office) {
        this.prof_office = prof_office;
    }

    public void setProf_brief(String prof_brief) {
        this.prof_brief = prof_brief;
    }

    public void setProf_research(String prof_research) {
        this.prof_research = prof_research;
    }

    public void setProf_publication(String prof_publication) {
        this.prof_publication = prof_publication;
    }

    public void setProf_courses(String prof_courses) {
        this.prof_courses = prof_courses;
    }

    public void setProf_imgURL(String prof_imgURL) {
        this.prof_imgURL = prof_imgURL;
    }

    //  getters
    public String getProf_name() {
        return prof_name;
    }

    public String getProf_pos() {
        return prof_pos;
    }

    public String getProf_email() {
        return prof_email;
    }

    public String getProf_office() {
        return prof_office;
    }

    public String getProf_brief() {
        return prof_brief;
    }

    public String getProf_research() {
        return prof_research;
    }

    public String getProf_publication() {
        return prof_publication;
    }

    public String getProf_courses() {
        return prof_courses;
    }

    public String getProf_imgURL() {
        return prof_imgURL;
    }
}