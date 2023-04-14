package com.example.shchat;

import java.util.ArrayList;

public class UserDataSource {
    static ArrayList<User>getListUser(){
        ArrayList<User> u = new ArrayList<>();
        u.add(new User(R.drawable.satu,"Syarif","08123456789","Saya mengunakan WA","16/04/2023"));
        u.add(new User(R.drawable.dua,"Alfudhail","08123456788","Saya mengunakan WA","17/04/2023"));
        u.add(new User(R.drawable.tiga,"Faiz","08123456787","Saya mengunakan WA","18/04/2023"));
        u.add(new User(R.drawable.empat,"Fikri","08123456786","Saya mengunakan WA","19/04/2023"));
        u.add(new User(R.drawable.lima,"Ibnu","08123456785","Saya mengunakan WA","20/04/2023"));
        u.add(new User(R.drawable.enam,"Taufiq","08123456784","Saya mengunakan WA","21/04/2023"));
        u.add(new User(R.drawable.tujuh,"Naufal","08123456783","Saya mengunakan WA","22/04/2023"));
        u.add(new User(R.drawable.lapan,"Farid","08123456782","Saya mengunakan WA","23/04/2023"));
        u.add(new User(R.drawable.sembilan,"Reza","08123456781","Saya mengunakan WA","24/04/2023"));
        u.add(new User(R.drawable.puluh,"Zhafran","08123456780","Saya mengunakan WA","25/04/2023"));
        u.add(new User(R.drawable.belas,"Fiqqi","08123456790","Saya mengunakan WA","26/04/2023"));
        return u;

    }

}