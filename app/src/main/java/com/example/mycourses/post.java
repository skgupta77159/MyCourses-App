package com.example.mycourses;

public class post {
    String thumbnail, time, vidurl, title;

    public post(){
    }

    public post(String thumbnail, String time, String vidurl, String title) {
        this.thumbnail = thumbnail;
        this.time = time;
        this.vidurl = vidurl;
        this.title = title;
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
