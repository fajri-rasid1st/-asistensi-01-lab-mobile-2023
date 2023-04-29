package com.example.chatt;

public class ItemModel {

    String name1, chat1, time1, number;
    int img1;

    public ItemModel(String name1, String chat1, String time1, int img1, String number) {
        this.name1 = name1;
        this.chat1 = chat1;
        this.time1 = time1;
        this.img1 = img1;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getChat1() {
        return chat1;
    }

    public void setChat1(String chat1) {
        this.chat1 = chat1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }
}
