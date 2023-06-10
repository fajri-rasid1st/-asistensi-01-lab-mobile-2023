package com.example.assignment8;

import java.util.ArrayList;
import java.util.Locale;

public class DataSource {

    // Array untuk menampung data-data post
    public static ArrayList<ModelPost> posts = generateDummyModelPosts();

    // Method untuk menghasilkan data dummy ModelPost
    private static ArrayList<ModelPost> generateDummyModelPosts() {
        ArrayList<ModelPost> searchContact = new ArrayList<>();

        // Menambahkan objek ModelPost ke dalam ArrayList
        searchContact.add(new ModelPost("aaaaaaaaaaaaaaaaaaaaaa","Miku001","Hatsune Miku","https://i.pinimg.com/564x/66/27/04/66270491b12ccc63ab146279ab3be27c.jpg","https://i.pinimg.com/564x/66/27/04/66270491b12ccc63ab146279ab3be27c.jpg"));
        searchContact.add(new ModelPost("bbbbbbbbbbbb", "Rin002", "Kagamine Rin", "https://i.pinimg.com/originals/ab/cf/b7/abcfb72177e92038cd5ae8e7c932e3a5.jpg", "https://i.pinimg.com/originals/ab/cf/b7/abcfb72177e92038cd5ae8e7c932e3a5.jpg"));
        searchContact.add(new ModelPost("cccccccc", "Kaito005", "Kaito Shion", "https://i.pinimg.com/originals/61/26/7b/61267bbb9d3cc0cd2c2282b89d1cc1ea.jpg", "https://i.pinimg.com/originals/61/26/7b/61267bbb9d3cc0cd2c2282b89d1cc1ea.jpg"));
        searchContact.add(new ModelPost("dddddddd", "Len002", "Kagamine Len", "https://i.pinimg.com/originals/10/a5/b2/10a5b2c122047942b4ed9566ef15ace5.jpg", "https://i.pinimg.com/originals/10/a5/b2/10a5b2c122047942b4ed9566ef15ace5.jpg"));
        searchContact.add(new ModelPost("eeeeeeeee", "CV03Luka", "Megurine Luka", "https://i.pinimg.com/564x/de/17/a5/de17a5adb79db424b1a2368b1b11fac7.jpg", "https://i.pinimg.com/564x/de/17/a5/de17a5adb79db424b1a2368b1b11fac7.jpg"));

        return searchContact;
    }

    // Method untuk mendapatkan ArrayList posts
    public ArrayList<ModelPost> getPosts() {
        return posts;
    }

    // Method untuk mendapatkan ArrayList posts berdasarkan query pencarian
    public static ArrayList<ModelPost> getPostsByQuery(String query){
        ArrayList<ModelPost> searchData = new ArrayList<>();

        ModelPost modelPost = posts.get(0);
        for (int i = 0; i< posts.size(); i++){
            ModelPost post = posts.get(i);

            if (i>0){
                if (modelPost.getFullName().equals(post.getFullName()) || modelPost.getUserName().equals(post.getUserName() )){
                    continue;
                }
            }

            String q = query.toLowerCase();

            String fullName = post.getFullName().toLowerCase();
            String username = post.getUserName().toLowerCase();

            // Memeriksa apakah fullName atau username mengandung query pencarian
            if (fullName.startsWith(q) || username.startsWith(q)){
                searchData.add(post);
            }

            // Mengupdate perbandingan untuk iterasi selanjutnya
            modelPost = post;
        }

        return searchData;
    }
}