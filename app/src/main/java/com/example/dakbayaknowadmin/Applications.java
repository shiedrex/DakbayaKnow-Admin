package com.example.dakbayaknowadmin;

public class Applications {
    private  String fullname;
    private String destination;
    private String status;
    private String health;
    private String travellerType;
    private String origin;
    private String departure;
    private String arrival;
    private String govId;
    private String govIdImage;
    private String vaccCardImage;

    public Applications() {

    }

    public Applications(String fullname, String destination, String status, String health, String travellerType, String origin, String departure, String arrival, String govId, String govIdImage, String vaccCardImage) {
        this.fullname = fullname;
        this.destination = destination;
        this.status = status;
        this.health = health;
        this.travellerType = travellerType;
        this.origin = origin;
        this.departure = departure;
        this.arrival = arrival;
        this.govId = govId;
        this.govIdImage = govIdImage;
        this.vaccCardImage = vaccCardImage;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(String travellerType) {
        this.travellerType = travellerType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getGovId() {
        return govId;
    }

    public void setGovId(String govId) {
        this.govId = govId;
    }

    public String getGovIdImage() {
        return govIdImage;
    }

    public void setGovIdImage(String govIdImage) {
        this.govIdImage = govIdImage;
    }

    public String getVaccCardImage() {
        return vaccCardImage;
    }

    public void setVaccCardImage(String vaccCardImage) {
        this.vaccCardImage = vaccCardImage;
    }
}
