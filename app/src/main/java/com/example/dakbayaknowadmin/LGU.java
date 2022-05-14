package com.example.dakbayaknowadmin;

public class LGU {
    private String city;
    private String policy;
    private String alert;

    public LGU() {

    }

    public LGU(String city, String policy, String alert) {
        this.city = city;
        this.policy = policy;
        this.alert = alert;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getAlert() {
        return alert;
    }
}
