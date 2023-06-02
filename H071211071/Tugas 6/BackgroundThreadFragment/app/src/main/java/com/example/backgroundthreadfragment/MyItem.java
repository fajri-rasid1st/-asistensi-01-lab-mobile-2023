package com.example.backgroundthreadfragment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MyItem implements Parcelable {

    String caption;
    Uri imgPost;

    public MyItem(String caption, Uri imgPost) {
        this.caption = caption;
        this.imgPost = imgPost;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Uri getImgPost() {
        return imgPost;
    }

    public void setImgPost(Uri imgPost) {
        this.imgPost = imgPost;
    }

    public MyItem() {
    }

    protected MyItem(Parcel in) {
        imgPost = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<MyItem> CREATOR = new Creator<MyItem>() {
        @Override
        public MyItem createFromParcel(Parcel in) {
            return new MyItem(in);
        }

        @Override
        public MyItem[] newArray(int size) {
            return new MyItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(imgPost, i);
    }
}

