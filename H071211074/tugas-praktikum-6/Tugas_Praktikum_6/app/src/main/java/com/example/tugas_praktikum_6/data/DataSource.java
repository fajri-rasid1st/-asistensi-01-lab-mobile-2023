package com.example.tugas_praktikum_6.data;

import android.net.Uri;

import com.example.tugas_praktikum_6.R;
import com.example.tugas_praktikum_6.data.model.Post;
import com.example.tugas_praktikum_6.data.model.User;

import java.util.ArrayList;

public class DataSource {
    private static final String BASE_URI = "android.resource://com.example.tugas_praktikum_6/drawable/";
    private ArrayList<User> users = new ArrayList<>();

    public DataSource() {
        this.users.addAll(generateData());
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    private ArrayList<User> generateData() {
        ArrayList<User> users1 = new ArrayList<>();
        for (int i = 0; i < listNames.length; i++) {
            User user = new User(listUsernames[i], listNames[i], listProfilePicture[i],
                    new Post(
                            "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
                            Uri.parse(BASE_URI + listProfilePicture[i])));

        users1.add(user);
        }
        return users1;
    }

    public void addUser(User user) {
        this.users.add(0, user);
    }

    private String[] listNames = {"Minji", "Eunchae", "Chaewon", "Hyunjin", "Chaewon2"};
    private String[] listUsernames = {"minji", "eunchae", "chaewon", "hyunjin", "chaewon2"};
    private int[] listProfilePicture = {R.drawable.profile_5, R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_3, R.drawable.profile_4};
}
