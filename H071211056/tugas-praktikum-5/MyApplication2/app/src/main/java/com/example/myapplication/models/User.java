package com.example.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private String fullName;
    private String userName;
    private int img;
    private Post post;

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public int getImg() {
        return img;
    }

    public Post getPost() {
        return post;
    }

    protected User(Parcel in) {
        fullName = in.readString();
        userName = in.readString();
        img = in.readInt();
        post = in.readParcelable(Post.class.getClassLoader());
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

    public User() {
    }

    public User(String fullName, String userName, int img, Post post) {
        this.fullName = fullName;
        this.userName = userName;
        this.img = img;
        this.post = post;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(userName);
        parcel.writeInt(img);
        parcel.writeParcelable(post, i);
    }
}
