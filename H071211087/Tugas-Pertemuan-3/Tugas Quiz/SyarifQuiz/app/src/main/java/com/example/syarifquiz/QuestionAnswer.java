package com.example.syarifquiz;

import android.content.Intent;

public class QuestionAnswer {

    public static String question[] ={
            "1. Siapa Ketua Majelis Wali Amanat Unhas Saat ini?",
            "2. Siapa Rektor Unhas Saat ini?",
            "3. Siapa Dekan Fmipa Unhas Saat ini?",
            "4. Siapa Ketua Departemen Matematika Saat ini?",
            "5. Siapa Ketua Prodi Sistem Informasi Saat ini?"
    };

    public static String choices[][] = {
            {"Jusuf Kalla","Prof.Alimuddin","Dr.Syafruddin","Yasin Limpo"},
            {"Prof.JJ","Prof.Ruslin","Prof.Subehan","Prof.Farida"},
            {"Dr.Hendra","Dr.Hasbi","Dr.Amiruddin","Dr.Armin"},
            {"Pak Eliyah","Pak Jeriko","Pak Armin","Prof.Nurdin"},
            {"Dr.Hendra","Dr.Armin","Prof.Nurdin","Dr.Hasbi"}
    };

    public static String correctAnswers[] = {
            "Prof.Alimuddin",
            "Prof.JJ",
            "Dr.Amiruddin",
            "Prof.Nurdin",
            "Dr.Hendra"
    };


}
