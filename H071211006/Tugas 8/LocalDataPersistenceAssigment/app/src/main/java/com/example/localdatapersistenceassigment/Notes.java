package com.example.localdatapersistenceassigment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Notes implements Parcelable {
    private int id;
    private String title, detail, desc;

    public Notes() {
    }

    public Notes(int id, String title, String detail, String desc) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.desc = desc;
    }

    protected Notes(Parcel in) {
        id = in.readInt();
        title = in.readString();
        detail = in.readString();
        desc = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(detail);
        parcel.writeString(desc);
    }
}