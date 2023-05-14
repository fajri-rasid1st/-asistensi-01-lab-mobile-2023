package com.example.chatapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserModel implements Parcelable {

    private final String tanggal;
    private final String nama;
    private final String nomor;
    private final String status;
    private final int profil;


    public UserModel(String nama, String nomor, String status, int profil, String tanggal) {
        this.nama = nama;
        this.nomor = nomor;
        this.status = status;
        this.profil = profil;
        this.tanggal = tanggal;
    }

    protected UserModel(Parcel in) {
        nama = in.readString();
        nomor = in.readString();
        status = in.readString();
        profil = in.readInt();
        tanggal = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public String getNomor() {
        return nomor;
    }

    public String getStatus() {
        return status;
    }

    public int getProfil() {
        return profil;
    }

    public String getTanggal() {
        return tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(nomor);
        parcel.writeString(status);
        parcel.writeInt(profil);
        parcel.writeString(tanggal);
    }
}
