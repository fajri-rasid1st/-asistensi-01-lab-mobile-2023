package com.example.chatapp.DataSource;

import com.example.chatapp.Models.UserModel;
import com.example.chatapp.R;

import java.util.ArrayList;

public class UserDataSource {

    public static ArrayList<UserModel> getUserList() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<>();

        userModelArrayList.add(new UserModel("Monkey", "+62 812-1212-1212", "monyet", R.drawable.monkey, "Mei 20, 2020"));
        userModelArrayList.add(new UserModel("Black Hole", "+62 813-1313-1313", "lubang hitam", R.drawable.blackhole, "November 02, 2022"));
        userModelArrayList.add(new UserModel("Bruh", "+62 814-1414-1414", "broh", R.drawable.bruh, "November 02, 2021"));
        userModelArrayList.add(new UserModel("Dragon", "+62 815-1515-1315", "naga", R.drawable.dragon, "November 02, 2020"));
        userModelArrayList.add(new UserModel("Cosmic", "+62 816-1616-1616", "cosmos", R.drawable.cosmic, "November 02, 2019"));
        userModelArrayList.add(new UserModel("Mam", "+62 817-1717-1717", "gajah beta", R.drawable.mammoth, "Januari 01, 2015"));
        userModelArrayList.add(new UserModel("Narto", "+62 818-1818-1818", "saske", R.drawable.narto, "Februari 02, 2012"));
        userModelArrayList.add(new UserModel("Bee", "+62 819-1919-1319", "lebah", R.drawable.bee, "Maret 03, 2001"));
        userModelArrayList.add(new UserModel("Mut", "+62 812-1413-9999", "semut", R.drawable.ant, "April 04, 2018"));
        userModelArrayList.add(new UserModel("Sadboi", "+62 813-7455-8888", "sgt sad", R.drawable.sadboi, "Agustus 17, 2020"));

        return userModelArrayList;
    }
}
