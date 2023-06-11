package com.example.fragmentassignment.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String fullname;
    String username;
    int photo;
    Post post;

    public User(String fullname, String username, int photo, Post post) {
        this.fullname = fullname;
        this.username = username;
        this.photo = photo;
        this.post = post;
    }

    public User() {

    }

    protected User(Parcel in) {
        fullname = in.readString();
        username = in.readString();
        photo = in.readInt();
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullname);
        parcel.writeString(username);
        parcel.writeInt(photo);
        parcel.writeParcelable(post, i);
    }
}
