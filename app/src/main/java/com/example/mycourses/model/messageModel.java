package com.example.mycourses.model;

public class messageModel {

    String iconUrl, msgTitle, message;

    public messageModel() {
    }

    public messageModel(String iconUrl, String msgTitle, String message) {
        this.iconUrl = iconUrl;
        this.msgTitle = msgTitle;
        this.message = message;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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
