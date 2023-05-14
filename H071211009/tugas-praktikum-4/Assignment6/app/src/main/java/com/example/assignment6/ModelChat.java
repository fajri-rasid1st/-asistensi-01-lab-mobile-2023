package com.example.assignment6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

//model berfungsi untuk penyimpanan data yang akan ditampilkan dalam bentuk list atau tampilan lainnya
public class ModelChat implements Parcelable {
    //yg recyclerview di bag chat

    //atribut
    private String chat, time;

    protected ModelChat(Parcel in) {
        chat = in.readString();
        time = in.readString();
    }

    public static final Creator<ModelChat> CREATOR = new Creator<ModelChat>() {
        @Override
        public ModelChat createFromParcel(Parcel in) {
            return new ModelChat(in);
        }

        @Override
        public ModelChat[] newArray(int size) {
            return new ModelChat[size];
        }
    };
    //constructor
    public ModelChat(String chat, String time) {
        this.chat = chat;
        this.time = time;
    }
    //

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(chat);
        parcel.writeString(time);
    }

    //getter n setter
    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
