package com.example.fragmentassigment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DataPost implements Parcelable {
    private String caption;
    private Uri imageUri;

    public DataPost(String caption, Uri imageUri) {
        this.caption = caption;
        this.imageUri = imageUri;
    }
    protected DataPost(Parcel in) {
        caption = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<DataPost> CREATOR = new Creator<DataPost>() {
        @Override
        public DataPost createFromParcel(Parcel in) {
            return new DataPost(in);
        }

        @Override
        public DataPost[] newArray(int size) {
            return new DataPost[size];
        }
    };

    public String getCaption() {
        return caption;
    }

    public Uri getImageUri() {
        return imageUri;
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
        parcel.writeString(caption);
        parcel.writeParcelable(imageUri, i);
    }
}
