package com.example.mycourses.model;

public class enrolledModel {
    String cName, cPercent, cUrl, cTotal;

    public enrolledModel(String cName, String cPercent, String cUrl, String cTotal) {
        this.cName = cName;
        this.cPercent = cPercent;
        this.cUrl = cUrl;
        this.cTotal = cTotal;
    }

    public enrolledModel() {
    }

    public String getcTotal() {
        return cTotal;
    }

    public void setcTotal(String cTotal) {
        this.cTotal = cTotal;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPercent() {
        return cPercent;
    }

    public void setcPercent(String cPercent) {
        this.cPercent = cPercent;
    }

    public String getcUrl() {
        return cUrl;
    }

    public void setcUrl(String cUrl) {
        this.cUrl = cUrl;
    }
}
