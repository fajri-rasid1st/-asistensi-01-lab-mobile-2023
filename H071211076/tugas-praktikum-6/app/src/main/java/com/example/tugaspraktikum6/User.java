package com.example.tugaspraktikum6;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private Uri imageUri;
    private String username,nickname;

    public User(Uri imageUri, String username, String nickname) {
        this.imageUri = imageUri;
        this.username = username;
        this.nickname = nickname;
    }

    protected User(Parcel in) {
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        username = in.readString();
        nickname = in.readString();
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

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(imageUri, i);
        parcel.writeString(username);
        parcel.writeString(nickname);
    }
}
