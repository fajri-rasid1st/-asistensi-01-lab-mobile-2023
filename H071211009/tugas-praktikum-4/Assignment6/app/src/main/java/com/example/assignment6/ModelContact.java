package com.example.assignment6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

//model berfungsi untuk penyimpanan data yang akan ditampilkan dalam bentuk list atau tampilan lainnya
//Parcelable adalah suatu interface yang memungkinkan kita melakukan pengiriman satu objek sekaligus dari suatu Activity ke Activity lain.
public class ModelContact implements Parcelable {

    //atribut for main page
    private String name, pict;

    //atribut for contact details
    private String phoneNumber, status, statusDate;

    //isinya atribut chat dan chat time
    ArrayList<ModelChat> chats;

    //Parcel
    protected ModelContact(Parcel in) {
        name = in.readString();
        pict = in.readString();
        phoneNumber = in.readString();
        status = in.readString();
        statusDate = in.readString();
        chats = in.createTypedArrayList(ModelChat.CREATOR);
    }

    public static final Creator<ModelContact> CREATOR = new Creator<ModelContact>() {
        @Override
        public ModelContact createFromParcel(Parcel in) {
            return new ModelContact(in);
        }

        @Override
        public ModelContact[] newArray(int size) {
            return new ModelContact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(pict);
        parcel.writeString(phoneNumber);
        parcel.writeString(status);
        parcel.writeString(statusDate);
        parcel.writeTypedList(chats);
    }

    //constructor
    public ModelContact(String name, String pict, String phoneNumber, String status, String statusDate, ArrayList<ModelChat> chats) {
        this.name = name;
        this.pict = pict;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.statusDate = statusDate;
        this.chats = chats;
    }

    //set n get
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public ArrayList<ModelChat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<ModelChat> chats) {
        this.chats = chats;
    }
}

