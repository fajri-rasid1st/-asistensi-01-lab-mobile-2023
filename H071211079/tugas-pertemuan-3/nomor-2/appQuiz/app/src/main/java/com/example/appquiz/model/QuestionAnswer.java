package com.example.appquiz.model;

public class QuestionAnswer {

    public static String question[] ={
            "Mantra apa yang digunakan Harry untuk membunuh Lord Voldemort?",
            "Siapa yang menyamar sebagai Mad-Eye Moody, profesor Pertahanan Terhadap Ilmu Hitam tahun ke-4 Harry?",
            "Siapa nama ayah baptis Harry Potter?",
            "Ada berapa asrama dalam hogwarts?",
            "Hewan apa yang dipelihara Harry Potter?",
            "Siapa yang membunuh Albus Dumbledore?",
            "Siapa nama peri rumah keluarga Malfoy?"
    };

    public static String choises[][] = {
            {"Expelliarmus", "Expecto Patronum", "Avada Kedavra", "Accio"},
            {"Voldemort", "Peter Pettigrew", "Sirius Black", "Barty Crouch Jr."},
            {"Severus Snape", "Albus Dumbledore", "Sirius Black", "Tom Marvolo Riddle"},
            {"4", "3", "9", "2"},
            {"Tikus", "Katak", "Kucing", "Burung Hantu"},
            {"Draco Malfoy", "Severus Snape", "Hadrig", "Voldemort"},
            {"Dipsy", "Dobby", "Winky", "Kreacher"}
    };

    public static String correctAnswer[] = {
            "Avada Kedavra",
            "Barty Crouch Jr.",
            "Sirius Black",
            "4",
            "Burung Hantu",
            "Severus Snape",
            "Dobby"
    };
}
