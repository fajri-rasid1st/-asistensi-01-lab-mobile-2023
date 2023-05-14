package com.example.chatapp.Models;

public class ChatsModel {

    private final String pesan; // list of text messages
    private final String waktu; // time when the message was sent

    public ChatsModel(String pesan, String waktu) {
        this.pesan = pesan;
        this.waktu = waktu;
    }

    public String getPesan() {
        return pesan;
    }

    public String getWaktu() {
        return waktu;
    }
}



