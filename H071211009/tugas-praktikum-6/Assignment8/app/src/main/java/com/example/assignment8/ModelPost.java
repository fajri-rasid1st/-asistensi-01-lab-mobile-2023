package com.example.assignment8;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelPost implements Parcelable {

    String caption;
    String userName;
    String fullName;
    String contactPicture;
    String image;

    //constructor
    public ModelPost(String caption, String userName, String fullName, String contactPicture, String image) {
        this.caption = caption;
        this.userName = userName;
        this.fullName = fullName;
        this.contactPicture = contactPicture;
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactPicture() {
        return contactPicture;
    }

    public String getImage() {
        return image;
    }

    protected ModelPost(Parcel in) {
        caption = in.readString();
        userName = in.readString();
        fullName = in.readString();
        contactPicture = in.readString();
        image = in.readString();
    }

    public static final Creator<ModelPost> CREATOR = new Creator<ModelPost>() {
        @Override
        public ModelPost createFromParcel(Parcel in) {
            return new ModelPost(in);
        }

        @Override
        public ModelPost[] newArray(int size) {
            return new ModelPost[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeString(userName);
        parcel.writeString(fullName);
        parcel.writeString(contactPicture);
        parcel.writeString(image);
    }
}