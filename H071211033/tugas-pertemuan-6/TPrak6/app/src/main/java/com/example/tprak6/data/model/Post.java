package com.example.tprak6.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable {

    Uri imgaeUri;
    String caption;

    public Post() {
    }

    public Post(Uri imgaeUri, String caption) {
        this.imgaeUri = imgaeUri;
        this.caption = caption;
    }

    public void setImgaeUri(Uri imgaeUri) {
        this.imgaeUri = imgaeUri;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    protected Post(Parcel in) {
        imgaeUri = in.readParcelable(Uri.class.getClassLoader());
        caption = in.readString();
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

    public Uri getImgaeUri() {
        return imgaeUri;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(imgaeUri, flags);
        dest.writeString(caption);
    }
}
