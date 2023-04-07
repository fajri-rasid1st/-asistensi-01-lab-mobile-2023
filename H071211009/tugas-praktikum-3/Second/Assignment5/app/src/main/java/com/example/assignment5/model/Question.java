package com.example.assignment5.model;

//ndk perlu parcelable karena hanya untuk tampung data (sbg database)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//samakan nama constructor dgn nama class
public class Question {
    private String question;
    private String[] answer;

    private int correctAnswer;
    private int score;

    //di set di constructornya (constructur untuk ngisi semua atribut sekaligus)
    public Question(String question, String[] answer, int correctAnswer, int score) {
        this.question = question;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getScore() {
        return score;
    }

    //tempat tampung pertanyaan //kalau static ndk perlu dibuatkan object, bisa lgsg dipanggil
    public static List<Question> getQuestionsList(){
        List<Question> questionsList = new ArrayList<Question>();
        questionsList.add(new Question(
                "Mars merupakan planet keberapa di tata surya?",
                new String[]{
                        "Planet Pertama", "Planet Kedua", "Planet Ketiga", "Planet Keempat"
                },
                3,
                20
                ));
        questionsList.add(new Question(
                "Tumbuhan membutuhkan air antara lain untuk proses?",
                new String[]{
                        "Respirasi", "Fotosintesis", "Pengguguran", "Pelapukan"
                },
                1,
                20
        ));
        questionsList.add(new Question(
                "Dalam struktur cerita narasi, pengenalan nama tokoh, latar dan konflik merupakan bagian?",
                new String[]{
                        "Orientasi" , "Komplikasi", "Identifikasi", "Resolusi"
                },
                0,
                20
        ));
        questionsList.add(new Question(
                "Hewan yang cara perkembangbiakannya sama dengan cacing pipih adalah?",
                new String[]{
                        "lebah" , "hydra" , "planaria" , "belalang"
                },
                2,
                20
        ));
        questionsList.add(new Question(
                "Alat yang digunakan untuk mengukur tegangan listrik adalah?",
                new String[]{
                        "ampermeter" , "ohmmeter" , "wattmeter" , "voltmeter"
                },
                3,
                20
        ));
        questionsList.add(new Question(
                "Kemampuan makhluk hidup untuk menyesuaikan diri dengan lingkungan disebut?",
                new String[]{
                        "Adaptasi" , "Iritabilitas" , "Elektabilitas" , "Spontanitas"
                },
                0,
                20
        ));
        questionsList.add(new Question(
                "Bilangan biner adalah bilangan yang berbasis?",
                new String[]{
                        "Bilangan yang berbasis 2 yaitu 1 dan 2" , "Bilangan yang berbasis 2 yaitu 0 dan 1" , "Bilangan yang berbasis 10 yaitu 0 – 9" , "Bilangan yang berbasis 8 yaitu 0 – 7"
                },
                1,
                20
        ));
        questionsList.add(new Question(
                "Interface adalah kata lain dari?",
                new String[]{
                        "Terhalang jarak" , "Antarmuka" , "Berselisih" , "Pendekatan"
                },
                1,
                20
        ));
        questionsList.add(new Question(
                "Proses debug berfungi untuk?",
                new String[]{
                        "Menghapus" , "Menyalin" , "Mencari" , "Menganalisis"
                },
                2,
                20
        ));
        questionsList.add(new Question(
                "Cara mengatasi masalah UI yang berantakan agar menciptakan kenyamanan pengguna adalah?",
                new String[]{
                        "Membuat elemen yang besar" , "Membuat persona" , "Meminimalkan elemen antarmuka" , "Memilih warna yang baik"
                },
                3,
                20
        ));

        Collections.shuffle(questionsList);
        return questionsList.subList(0, 5);
    }
}
