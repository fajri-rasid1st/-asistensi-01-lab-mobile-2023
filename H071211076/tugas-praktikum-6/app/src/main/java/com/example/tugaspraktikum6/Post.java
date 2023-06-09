package com.example.tugaspraktikum6;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable {

    private User user;
    private String caption;
    private Uri imageUri;

    public Post(User user, String caption, Uri imageUri) {
        this.user = user;
        this.caption = caption;
        this.imageUri = imageUri;
    }

    protected Post(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        caption = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public User getUser() {
        return user;
    }

    public String getCaption() {
        return caption;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(user, i);
        parcel.writeString(caption);
        parcel.writeParcelable(imageUri, i);
    }
}