package com.example.myapplication;

import android.net.Uri;

import com.example.myapplication.models.Post;
import com.example.myapplication.models.User;

import java.util.ArrayList;

public class DataSource {

    private static final String URI_CONST = "android.resource://com.example.myapplication/drawable/";
    private final ArrayList<User> users = new ArrayList<>();

    public DataSource() {
        users.addAll(generateUsers());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(0, user);
    }

    private ArrayList<User> generateUsers(){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < fullName.length ; i++) {
            Post post = new Post(Uri.parse(URI_CONST + profilePicture[i]), "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor");

            User user = new User(fullName[i],nickName[i], profilePicture[i], post);

            users.add(user);
        }
        return users;
    }


    private int[] profilePicture = {
            R.drawable.doffy, R.drawable.eren, R.drawable.erwinsmit,
            R.drawable.braun, R.drawable.hajji, R.drawable.historia,
            R.drawable.mikasa, R.drawable.levi, R.drawable.sdreren,
            R.drawable.jean
    };

    private String[] fullName = {
            "doflaminggo", "ErenUzumaki", "ErwinSmith", "BraunYuyu",
            "HanjiAckremen", "Historia", "MikasaAkremen",
            "LeviAcremen", "SaudaraEren", "JeanKuda"
    };
    private String[] nickName = {
            "Doffy", "Eren", "Erwin", "Braun", "Hanji", "Historia", "Mikasa"
            ,"Levi", "Saudara", "Jean"
    };
}
