package com.example.dakbayaknowadmin;

public class Feedback {
    private String rating;
    private String username;
    private  String comment;
    private Float star;

    public Feedback() {

    }

    public Feedback(String rating, String username, Float star, String comment) {
        this.rating = rating;
        this.username = username;
        this.star = star;
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
