package com.example.shchat;

public class ModelChat {
    String waktu;
    String message;

    public String getWaktu() {
        return waktu;
    }

    public String getMessage() {
        return message;
    }

    public ModelChat(String waktu, String message) {
        this.waktu = waktu;
        this.message = message;
    }
}
