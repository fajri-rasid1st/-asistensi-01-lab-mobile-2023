package com.example.fragmentassigment;

import java.util.ArrayList;

public class PostDataSource {

    public static ArrayList<PostModel> dataList = generateDataDummyPostModels();

    private static ArrayList<PostModel> generateDataDummyPostModels() {
        ArrayList<PostModel> dataList = new ArrayList<>();

        dataList.add(new PostModel("Nunu", "Nurul", "2 pwetty best friend", "https://i.pinimg.com/564x/f3/bf/b4/f3bfb48227bc0fdc6fc81d059d2ef83d.jpg", "https://i.pinimg.com/564x/f3/bf/b4/f3bfb48227bc0fdc6fc81d059d2ef83d.jpg"));
        dataList.add(new PostModel("J", "Jasmine", "Bersama anak-anakku", "https://i.pinimg.com/564x/54/c0/c0/54c0c0de1f65a64e64b270c2bc92d1a1.jpg", "https://i.pinimg.com/564x/54/c0/c0/54c0c0de1f65a64e64b270c2bc92d1a1.jpg"));
        dataList.add(new PostModel("Ini devi", "Devi", "Lorem ipsum dolor sit amet ", "https://i.pinimg.com/564x/ee/05/51/ee0551becf8d24aec1480c91f84e60c1.jpg", "https://i.pinimg.com/564x/ee/05/51/ee0551becf8d24aec1480c91f84e60c1.jpg"));
        dataList.add(new PostModel("Susanti", "Susi Susanti", "Pisss", "https://i.pinimg.com/564x/72/84/3d/72843d05b975c0ab6c87ae5b081e3c5e.jpg", "https://i.pinimg.com/564x/72/84/3d/72843d05b975c0ab6c87ae5b081e3c5e.jpg"));
        dataList.add(new PostModel("Fizi123", "Fizi", "Hmmm", "https://i.pinimg.com/564x/b9/e6/68/b9e6683c4dc34485a10b600735b608cf.jpg", "https://i.pinimg.com/564x/b9/e6/68/b9e6683c4dc34485a10b600735b608cf.jpg"));
        dataList.add(new PostModel("Ahtongjie", "Uncle Ahtong", "hehehehehe", "https://i.pinimg.com/564x/18/fb/a8/18fba83732ee635d43b7c0df7714d14a.jpg", "https://i.pinimg.com/564x/18/fb/a8/18fba83732ee635d43b7c0df7714d14a.jpg"));
        dataList.add(new PostModel("Ehsan ni boss", "Ehsan", ":)", "https://i.pinimg.com/564x/8a/a2/04/8aa2041c87f990c33113c392ec300206.jpg", "https://i.pinimg.com/564x/8a/a2/04/8aa2041c87f990c33113c392ec300206.jpg"));
        dataList.add(new PostModel("Ipinn", "Ipin", "uwoah!", "https://i.pinimg.com/564x/70/16/70/701670c6c52f754d8c3d6734d0cf7739.jpg", "https://i.pinimg.com/564x/70/16/70/701670c6c52f754d8c3d6734d0cf7739.jpg"));
        dataList.add(new PostModel("Ismail", "Ismail bin mail", "...", "https://i.pinimg.com/564x/f9/68/bd/f968bda4fe2f5ebcddefbe22d01f984f.jpg", "https://i.pinimg.com/564x/f9/68/bd/f968bda4fe2f5ebcddefbe22d01f984f.jpg"));
        dataList.add(new PostModel("Remb000", "Rembo", "Pok pok pok pok", "https://i.pinimg.com/564x/96/88/f5/9688f5043db081d7e991d1a6563783e8.jpg", "https://i.pinimg.com/564x/96/88/f5/9688f5043db081d7e991d1a6563783e8.jpg"));
        dataList.add(new PostModel("Jarjit", "Jarjit Singh", "Dua tiga bulu tangkis, kalau kalah jangan menangis", "https://i.pinimg.com/564x/1e/26/58/1e2658c78fef956db0caa8ce511e73e8.jpg", "https://i.pinimg.com/564x/1e/26/58/1e2658c78fef956db0caa8ce511e73e8.jpg"));

        return dataList;
    }

    public static ArrayList<PostModel> searchPostModels(String query) {
        ArrayList<PostModel> searchedPostModels = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            final PostModel postModel = dataList.get(i);
            String q = query.toLowerCase();
            String name = postModel.getName().toLowerCase();
            String username = postModel.getUsername().toLowerCase();
            if (name.startsWith(query) || username.startsWith(query)) {
                searchedPostModels.add(postModel);
            }
        }
        return searchedPostModels;
    }
}