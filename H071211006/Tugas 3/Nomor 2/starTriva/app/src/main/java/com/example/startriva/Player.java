package com.example.startriva;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Player implements Parcelable {
    String name;
    int bestScore;
    Uri profileImg;

    protected Player(Parcel in) {
        name = in.readString();
        bestScore = in.readInt();
        profileImg = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(bestScore);
        parcel.writeParcelable(profileImg, i);
    }

    public String getName() {
        return name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public Uri getProfileImg() {
        return profileImg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setProfileImg(Uri profileImg) {
        this.profileImg = profileImg;
    }
}
