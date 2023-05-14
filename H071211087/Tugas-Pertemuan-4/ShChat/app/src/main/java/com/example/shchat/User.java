package com.example.shchat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private int profile;
    private String name;
    private String nomor;
    private String status;
    private String tglstatus;

    public int getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public String getNomor() {
        return nomor;
    }

    public String getStatus() {
        return status;
    }

    public String getTglstatus() {
        return tglstatus;
    }

    public User(int profile, String name, String nomor, String status, String tglstatus) {
        this.profile = profile;
        this.name = name;
        this.nomor = nomor;
        this.status = status;
        this.tglstatus = tglstatus;
    }

    protected User(Parcel in) {
        profile = in.readInt();
        name = in.readString();
        nomor = in.readString();
        status = in.readString();
        tglstatus = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(profile);
        parcel.writeString(name);
        parcel.writeString(nomor);
        parcel.writeString(status);
        parcel.writeString(tglstatus);
    }
}
