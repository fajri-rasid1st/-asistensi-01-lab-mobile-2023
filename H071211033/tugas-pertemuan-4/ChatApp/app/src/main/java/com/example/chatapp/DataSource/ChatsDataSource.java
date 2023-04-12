package com.example.chatapp.DataSource;

import com.example.chatapp.Models.ChatsModel;

import java.util.ArrayList;

public class ChatsDataSource {

    public static ArrayList<ChatsModel> getChatsList(){
        ArrayList<ChatsModel> chatsModelArrayList = new ArrayList<>();

        chatsModelArrayList.add(new ChatsModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "21.00"));
        chatsModelArrayList.add(new ChatsModel("consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "20.00"));
        chatsModelArrayList.add(new ChatsModel("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "19.50"));
        chatsModelArrayList.add(new ChatsModel("ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "19.49"));
        chatsModelArrayList.add(new ChatsModel("et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "16.07"));
        chatsModelArrayList.add(new ChatsModel("magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "16.01"));
        chatsModelArrayList.add(new ChatsModel("sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "12.00"));
        chatsModelArrayList.add(new ChatsModel("elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "10.31"));
        chatsModelArrayList.add(new ChatsModel("tempor incididunt ut labore et dolore magna aliqua. Viverra suspendisse potenti nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "07.30"));
        chatsModelArrayList.add(new ChatsModel("nullam ac tortor vitae purus faucibus. Consequat interdum varius sit amet mattis vulputate enim nulla. Praesent semper feugiat nibh sed pulvinar proin. Nulla facilisi cras fermentum odio eu feugiat.", "07.23"));

        return chatsModelArrayList;
    }
}
