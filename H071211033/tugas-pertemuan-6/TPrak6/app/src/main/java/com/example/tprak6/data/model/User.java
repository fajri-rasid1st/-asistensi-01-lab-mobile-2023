package com.example.tprak6.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class User implements Parcelable {
    
    String username, name;

    @DrawableRes
    int profilePic;

    Post post;

    public User() {
    }

    public User(String username, String name, int profilePic, Post post) {
        this.username = username;
        this.name = name;
        this.profilePic = profilePic;
        this.post = post;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public Post getPost() {
        return post;
    }

    protected User(Parcel in) {
        username = in.readString();
        name = in.readString();
        profilePic = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeInt(profilePic);
        dest.writeParcelable(post, flags);
    }
}
