package com.example.praktikumrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Chat implements Parcelable {
    private String name;
    private String lastChat;
    private String photo;
    private String phone;
    private String status;
    private String dateOfStatus;
    private String dateOfChat;

    public Chat(String name, String lastChat, String photo, String phone, String status, String dateOfStatus, String dateOfChat) {
        this.name = name;
        this.lastChat = lastChat;
        this.photo = photo;
        this.phone = phone;
        this.status = status;
        this.dateOfStatus = dateOfStatus;
        this.dateOfChat = dateOfChat;
    }

    protected Chat(Parcel in) {
        name = in.readString();
        lastChat = in.readString();
        photo = in.readString();
        phone = in.readString();
        status = in.readString();
        dateOfStatus = in.readString();
        dateOfChat = in.readString();
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLastChat() {
        return lastChat;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getDateOfStatus() {
        return dateOfStatus;
    }

    public String getDateOfChat() {
        return dateOfChat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateOfStatus(String dateOfStatus) {
        this.dateOfStatus = dateOfStatus;
    }

    public void setDateOfChat(String dateOfChat) {
        this.dateOfChat = dateOfChat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(lastChat);
        parcel.writeString(photo);
        parcel.writeString(phone);
        parcel.writeString(status);
        parcel.writeString(dateOfStatus);
        parcel.writeString(dateOfChat);
    }
}
