package com.example.rv_assignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ModelClass2 implements Parcelable {

    public static final int LAYOUT_ONE = 1; //sender message
    public static final int LAYOUT_TWO = 2; // reciever message

    private final int viewType;
    private String message;
    private String time;

    public ModelClass2(int viewType, String message, String time) {
        this.viewType = viewType;
        this.message = message;
        this.time = time;
    }

    protected ModelClass2(Parcel in) {
        viewType = in.readInt();
        message = in.readString();
        time = in.readString();
    }

    public static final Creator<ModelClass2> CREATOR = new Creator<ModelClass2>() {
        @Override
        public ModelClass2 createFromParcel(Parcel in) {
            return new ModelClass2(in);
        }

        @Override
        public ModelClass2[] newArray(int size) {
            return new ModelClass2[size];
        }
    };

    public int getViewType() {
        return viewType;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeInt(viewType);
        parcel.writeString(message);
        parcel.writeString(time);
    }
}
