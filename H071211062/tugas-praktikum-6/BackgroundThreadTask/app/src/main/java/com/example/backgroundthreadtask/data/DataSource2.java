package com.example.backgroundthreadtask.data;

import android.net.Uri;

import com.example.backgroundthreadtask.R;
import com.example.backgroundthreadtask.data.model.Post;
import com.example.backgroundthreadtask.data.model.User;

import java.util.ArrayList;

public class DataSource2 {
    private static final String URI_CONST = "android.resource://com.example.backgroundthreadtask/drawable/";
    private ArrayList<User> users = new ArrayList<>();

    public DataSource2() {
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
                            "Lorem ipsum dolor sit amet",
                            Uri.parse(URI_CONST + profilePictures[i]))
            );

            userArrayList.add(user);
        }

        return userArrayList;
    }

    private final String[] names = { "H071211056", "H071211060", "H071211066"};
    private final String[] usernames = { "Fikri", "Ibnu", "faiz"};
    private final int[] profilePictures = { R.drawable.satu, R.drawable.empat, R.drawable.lima};
}
