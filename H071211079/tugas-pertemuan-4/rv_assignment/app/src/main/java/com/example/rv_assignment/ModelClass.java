package com.example.rv_assignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelClass implements Parcelable {

    private int profilePict;

    private String name, userName, userNumber, userStatus, date, lastMessage, time;

    public ModelClass(int profilePict, String name, String userName, String userNumber, String userStatus, String date, String LastMessage, String time) {
        this.profilePict = profilePict;
        this.name = name;
        this.userName = userName;
        this.userNumber = userNumber;
        this.userStatus = userStatus;
        this.date = date;
        this.lastMessage = LastMessage;
        this.time = time;
    }

    public ModelClass() {

    }

    protected ModelClass(Parcel in) {
        profilePict = in.readInt();
        name = in.readString();
        userName = in.readString();
        userNumber = in.readString();
        userStatus = in.readString();
        date = in.readString();
        lastMessage = in.readString();
        time = in.readString();
    }

    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(profilePict);
        parcel.writeString(name);
        parcel.writeString(userName);
        parcel.writeString(userNumber);
        parcel.writeString(userStatus);
        parcel.writeString(date);
        parcel.writeString(lastMessage);
        parcel.writeString(time);
    }

    public int getProfilePict() {
        return profilePict;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getDate() {
        return date;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }
}
