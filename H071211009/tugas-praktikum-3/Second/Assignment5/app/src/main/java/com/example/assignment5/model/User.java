package com.example.assignment5.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

//butuh parcelable untuk mengirim data dari 1 hal ke hal lain

//samakan nama constructor dgn nama class
public class User implements Parcelable {
    private String name;
    private int score;

    //constuctor untuk inisialisasi score
    public User() {
      score = 0;
    }

    protected User(Parcel in) {
        name = in.readString();
        score = in.readInt();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(score);
    }
}
