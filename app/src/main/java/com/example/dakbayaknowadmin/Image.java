package com.example.dakbayaknowadmin;

public class Image {
    private String imageUrl;

    public Image(){

    }

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
