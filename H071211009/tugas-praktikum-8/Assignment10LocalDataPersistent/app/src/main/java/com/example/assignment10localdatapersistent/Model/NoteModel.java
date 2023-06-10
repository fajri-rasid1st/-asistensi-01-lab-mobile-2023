package com.example.assignment10localdatapersistent.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NoteModel implements Parcelable {
    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel in) {
            return new NoteModel(in);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };
    private int id;
    private String name;
    private String description;
    private String createdDate;
    private String createdTime;

    public NoteModel() {
    }

    public NoteModel(int id, String name, String description, String createdDate, String createdTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
    }

    protected NoteModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        createdDate = in.readString();
        createdTime = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(createdDate);
        dest.writeString(createdTime);
    }
}