package com.example.mycourses;

public class post {
    String thumbnail, time, vidurl, title, key, chpname;

    public post(){
    }

    public post(String thumbnail, String time, String vidurl, String title, String key, String chpname) {
        this.thumbnail = thumbnail;
        this.time = time;
        this.vidurl = vidurl;
        this.title = title;
        this.key = key;
        this.chpname = chpname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getChpname() {
        return chpname;
    }

    public void setChpname(String chpname) {
        this.chpname = chpname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVidurl() {
        return vidurl;
    }

    public void setVidurl(String vidurl) {
        this.vidurl = vidurl;
    }
}
