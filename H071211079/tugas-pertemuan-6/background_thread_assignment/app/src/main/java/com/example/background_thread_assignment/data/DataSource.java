package com.example.background_thread_assignment.data;

import android.net.Uri;

import com.example.background_thread_assignment.R;
import com.example.background_thread_assignment.data.model.Post;
import com.example.background_thread_assignment.data.model.User;

import java.util.ArrayList;

public class DataSource {
    private static final String BASE_URI = "android.resource://com.example.background_thread_assignment/drawable/";
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

    private String[] listNames = {"Jeno", "Jisung", "Taeyong", "Sungchan", "Hendery"};
    private String[] listUsernames = {"jenolee", "jisungpark", "taeyonglee", "sungchanjung", "henderyhuang"};
    private int[] listProfilePicture = {R.drawable.jeno, R.drawable.jisung, R.drawable.leetaeyong, R.drawable.sungchan, R.drawable.hendery};
}
