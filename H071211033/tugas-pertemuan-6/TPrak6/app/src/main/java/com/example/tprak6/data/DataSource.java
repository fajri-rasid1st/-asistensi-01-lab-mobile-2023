package com.example.tprak6.data;

import android.net.Uri;

import com.example.tprak6.R;
import com.example.tprak6.data.model.Post;
import com.example.tprak6.data.model.User;

import java.util.ArrayList;

public class DataSource {

    private static final String URI_CONST = "android.resource://com.example.tprak6/drawable/";
    private ArrayList<User> users = new ArrayList<>();

    public DataSource() {
        this.users.addAll(generateData());
    }

    public ArrayList<User> getUsers() {

        return users;
    }

    public void addUser(User user) {

        users.add(0, user);
    }

    private ArrayList<User> generateData() {

        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {

            User user = new User(usernames[i], names[i], profilePictures[i],
                    new Post(
                            Uri.parse(URI_CONST + profilePictures[i]),
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labo")
            );

            userArrayList.add(user);
        }

        return userArrayList;
    }

    private final String[] names = {"Andi Bruhman", "Angga Semutan", "Bambang Blackhole", "Uzumaki Narto", "Abdul Naga"};
    private final String[] usernames = {"andbrhmn", "anggsmtan", "b_blckhole", "nartoofficial", "abd_naga"};
    private final int[] profilePictures = {R.drawable.bruh, R.drawable.ant, R.drawable.blackhole, R.drawable.narto, R.drawable.dragon};

}
