package com.example.mycourses;

public class sublist {
    String subname, suburl;

    public sublist() {
    }

    public sublist(String subname, String suburl) {
        this.subname = subname;
        this.suburl = suburl;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getSuburl() {
        return suburl;
    }

    public void setSuburl(String suburl) {
        this.suburl = suburl;
    }
}
