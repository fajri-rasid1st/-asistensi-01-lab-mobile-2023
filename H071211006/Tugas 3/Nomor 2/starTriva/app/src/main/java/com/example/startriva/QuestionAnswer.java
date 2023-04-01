package com.example.startriva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuestionAnswer {
    private String question;
    private String [] answer;
    private int score;
    private int correctAnswer;

    public QuestionAnswer(String question, String[] answer, int score, int correctAnswer) {
        this.question = question;
        this.answer = answer;
        this.score = score;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public int getScore() {
        return score;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public static List<QuestionAnswer> getAllQuestion(){
        List<QuestionAnswer> QnA = new ArrayList<>();
        QnA.add(new QuestionAnswer("Planet terdekat dari matahari adalah",
                new String[] {"Jupiter", "Merkurius", "Saturnus", "Mars"}, 40, 1));
        QnA.add(new QuestionAnswer("Apa nama bintang terdekat dari Bumi?",
                new String[] {"Alpha Centauri", "Betelgeuse", "Sirius", "Proxima Centauri"}, 40, 0));
        QnA.add(new QuestionAnswer("Apa nama galaksi tempat tinggal bumi?",
                new String[] {"Andromeda", "Bima Sakti", "Triangulum", "Whirlpool"}, 40, 1));
        QnA.add(new QuestionAnswer("Planet terjauh dari matahari adalah?",
                new String[] {"Neptunus", "Venus", "Mars", "Saturnus"}, 40, 0));
        QnA.add(new QuestionAnswer("Apa yang terjadi ketika bulan berada di antara Matahari dan Bumi?",
                new String[] {"Gerhana Matahari", "Gerhana Bulan", "Bulan Purnama", "Bulan Baru"}, 40, 0));
        QnA.add(new QuestionAnswer("Planet terbesar kedua di tata surya kita adalah?",
                new String[] {"Jupiter", "Venus", "Mars", "Saturnus"}, 40, 3));
        QnA.add(new QuestionAnswer("Penyebab matahari terbit dan terbenam setiap hari adalah?",
                new String[] {"Rotasi bumi pada sumbunya", "Rotasi matahari pada sumbunya", "Gerakan bulan di sekitar bumi", "Gerakan planet di tata surya"}, 40, 0));
        QnA.add(new QuestionAnswer("Apa nama rasi bintang yang terlihat paling terang di malam hari?",
                new String[] {"Orion", "Scorpius", "Ursa Major", "Kamu"}, 40, 0));
        QnA.add(new QuestionAnswer("Penyebab terjadinya fenomena langit utara yang disebut aurora borealis adalah? ",
                new String[] {"Ledakan di matahari", "Terjadinya Gempa Bumi", "Kehadiran awan bercahaya", "Gerakan planet"}, 40, 0));
        QnA.add(new QuestionAnswer("Apa yang dimaksud dengan tahun cahaya?",
                new String[] {"Jarak yang ditempuh oleh cahaya dalam waktu satu tahun", "Waktu yang diperlukan untuk suatu planet mengorbit matahari", "Satuan waktu dalam kalender astronomi", "Waktu yang diperlukan satu bintang mengelilingi pusat galaksi"}, 40, 0));

        Collections.shuffle(QnA);

        return QnA.subList(0,5);
    }
}
