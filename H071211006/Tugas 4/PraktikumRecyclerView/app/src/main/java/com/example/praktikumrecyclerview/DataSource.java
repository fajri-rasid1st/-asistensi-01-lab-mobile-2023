package com.example.praktikumrecyclerview;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Chat> getListChat() {
        ArrayList<Chat> listChat = new ArrayList<>();

        listChat.add(new Chat("Susanti", "Saya mau balik ke Jakarta besok", "https://i.pinimg.com/564x/72/84/3d/72843d05b975c0ab6c87ae5b081e3c5e.jpg", "+62 818-8188-8181", "Ada", "December 31, 2021", "13.09"));
        listChat.add(new Chat("Mail", "Sisa sayap, dua ringgitji", "https://i.pinimg.com/564x/f9/68/bd/f968bda4fe2f5ebcddefbe22d01f984f.jpg", "+62 331-1133-3313", "Tersedia ayam potong, fillet dan sayap dengan harga terjangkau", "Januari 01, 2023", "10.30"));
        listChat.add(new Chat("Rembo", "Pok pok pok pok", "https://i.pinimg.com/564x/96/88/f5/9688f5043db081d7e991d1a6563783e8.jpg", "+62 897-0980-3425", "Hi!, I am using WhatsApp", "Februari 20, 2022", "09.00"));
        listChat.add(new Chat("Jarjit", "Dua tiga bulu tangkis, kalau kalah jangan menangis", "https://i.pinimg.com/564x/1e/26/58/1e2658c78fef956db0caa8ce511e73e8.jpg", "+62 231-9875-0022", "Marvelous! Marvelous!", "Maret 11, 2023",  "10.00"));
        listChat.add(new Chat("Ipin", "Betul! Betul! Betul!", "https://i.pinimg.com/564x/70/16/70/701670c6c52f754d8c3d6734d0cf7739.jpg", "+62 777-3467-9876", "Ada", "April 21, 2022", "12.09"));
        listChat.add(new Chat("Cikgu Jasmine", "Lorem ipsum dolor sit", "https://i.pinimg.com/564x/54/c0/c0/54c0c0de1f65a64e64b270c2bc92d1a1.jpg", "+62 758-0110-3654", "Hi!, I am using WhatsApp", "Agustus 15, 2021", "08.09"));
        listChat.add(new Chat("Devi", "Lorem ipsum dolor ", "https://i.pinimg.com/564x/ee/05/51/ee0551becf8d24aec1480c91f84e60c1.jpg", "+62 257-0085-4678", "Go follow: @deviy_", "Juli 12, 2023", "10.08"));
        listChat.add(new Chat("Fizi", "Ada di Intan Payung", "https://i.pinimg.com/564x/b9/e6/68/b9e6683c4dc34485a10b600735b608cf.jpg", "+62 812-0394-0686", ".", "Januari 27, 2021", "12.04"));
        listChat.add(new Chat("Uncle Ahtong", "Lorem ipsum amet", "https://i.pinimg.com/564x/18/fb/a8/18fba83732ee635d43b7c0df7714d14a.jpg", "+62 318-8328-6183", "Jual beli barang bekas", "Juni 24, 2022", "13.09"));
        listChat.add(new Chat("Nurul", "Lorem dolor sit amet", "https://i.pinimg.com/564x/f3/bf/b4/f3bfb48227bc0fdc6fc81d059d2ef83d.jpg", "+62 809-6098-3485", "Ada", "November 09, 2022", "05.00"));
        listChat.add(new Chat("Ehsan", "Lorem dolor sit amet", "https://i.pinimg.com/564x/8a/a2/04/8aa2041c87f990c33113c392ec300206.jpg", "+62 410-2673-0962", "Ini status", "April 14, 2023", "15.10"));

        return listChat;
    }
}
