package com.example.tgsprak3quiz.model;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    public String soal;
    public String[] opsi;

    public int jawabanBenar;
    public int bobot;

    public Quiz(String soal, String[] opsi, int jawabanBenar, int bobot) {
        this.soal = soal;
        this.opsi = opsi;
        this.jawabanBenar = jawabanBenar;
        this.bobot = bobot;
    }



     public static Quiz[] getQuizSample(){

        ArrayList daftarQuiz = new ArrayList<Quiz>();

        daftarQuiz.add(new Quiz("Siapa Presiden Rusia", new String[]{"Barrack Yomama", "Vladimir Putin", "Michael Jordan", "Pierre E Aubameyang"}, 1, 13));
        daftarQuiz.add(new Quiz("Dimana Letak Colosseum", new String[]{"Sudiang", "Roma", "PIK", "Ponggerang"}, 1, 10));
        daftarQuiz.add(new Quiz("Siapa Striker Timnas Perancis di WC 2022", new String[]{"Mbappe", "Andi Oddang", "Stephen Curry", "Makoto Nagano"}, 0, 10));
        daftarQuiz.add(new Quiz("Siapa nama presiden keempat Indonesia?", new String[]{"Soekarno", "Soeharto", "B.J. Habibie", "GUSDUR"}, 3, 10));
        daftarQuiz.add(new Quiz("Siapa nama presiden kelima Indonesia?", new String[]{"SBY", "Soeharto", "B.J. Habibie", "Megawati"}, 3, 10));
        daftarQuiz.add(new Quiz("Apa Ibu Kota Sulawesi Selatan?", new String[]{"Sudiang", "Maros", "Makassar", "Markland"}, 2, 20));
        daftarQuiz.add(new Quiz("Berapa Jumlah Provinsi Indonesia", new String[]{"36", "34", "32", "38"}, 3, 12));

        Collections.shuffle(daftarQuiz);
        return (Quiz[]) daftarQuiz.toArray(new Quiz[]{});


    }
    public int getJawabanBenar() {
        return jawabanBenar;
    }

    public String getPilihanBenar(int index) {
        return opsi[index];
    }

}
