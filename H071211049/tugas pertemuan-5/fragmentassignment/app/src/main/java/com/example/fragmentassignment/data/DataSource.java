package com.example.fragmentassignment.data;

import android.net.Uri;

import com.example.fragmentassignment.R;
import com.example.fragmentassignment.data.models.Post;
import com.example.fragmentassignment.data.models.User;

import java.util.ArrayList;

public class DataSource {
    private final ArrayList<User> users = new ArrayList<>();

    public DataSource() {
        this.users.addAll(generateUsers());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(0, user);
    }

    private ArrayList<User> generateUsers(){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < fullname.length ; i++) {
            User user = new User(
                    fullname[i],
                    username[i],
                    photo[i],
                    new Post(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et.",
                            Uri.parse("android.resource://com.example.fragmentassignment/drawable/"+ photo[i])
                    )
            );

            users.add(user);
        }

        return users;
    }

    private final String[] fullname = new String[] {
            "aaa","bbb", "ccc","ddd","eee"

    };

    private final String[] username = new String[] {
            "aaa","bbb", "ccc","ddd","eee"

    };

    private final int[] photo = new int[] {
           R.drawable.pfp, R.drawable.pfp, R.drawable.pfp, R.drawable.pfp, R.drawable.pfp

    };
}
