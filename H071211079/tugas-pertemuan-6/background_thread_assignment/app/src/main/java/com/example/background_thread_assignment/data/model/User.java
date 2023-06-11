package com.example.background_thread_assignment.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class User implements Parcelable {
    private String username;
    private String fullname;
    @DrawableRes
    private int profilePicture;
    private Post post;

    public User(String username, String fullname, int profilePicture, Post post) {
        this.username = username;
        this.fullname = fullname;
        this.profilePicture = profilePicture;
        this.post = post;
    }

    public User() {

    }

    protected User(Parcel in) {
        username = in.readString();
        fullname = in.readString();
        profilePicture = in.readInt();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(fullname);
        dest.writeInt(profilePicture);
        dest.writeParcelable(post, flags);
    }
}
