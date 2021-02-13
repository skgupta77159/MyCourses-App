package com.example.mycourses.model;

public class messageModel {

    String iconURl, msgTitle, message;

    public messageModel() {
    }

    public messageModel(String iconURl, String msgTitle, String message) {
        this.iconURl = iconURl;
        this.msgTitle = msgTitle;
        this.message = message;
    }

    public String getIconURl() {
        return iconURl;
    }

    public void setIconURl(String iconURl) {
        this.iconURl = iconURl;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
