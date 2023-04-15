package com.example.tugas4_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Soal {

    private String soal;
    private String[] opsi;
    private String jawaban;

    private Soal(String soal, String[] opsi, String jawaban){
        this.soal = soal;
        this.opsi = opsi;
        this.jawaban = jawaban;
    }

    public String getSoal() {
        return soal;
    }

    public String[] getOpsi() {
        return opsi;
    }

    public String getJawaban() {
        return jawaban;
    }
    public static List<Soal> generateSoal(){
        List<Soal> pertanyaan = new ArrayList<>();

        pertanyaan.add(new Soal("1 + 1 = ...", new String[]{"1", "2", "3", "4"}, "2"));
        pertanyaan.add(new Soal("1 + 2 = ...", new String[]{"0", "5", "3", "4"}, "3"));
        pertanyaan.add(new Soal("1 + 3 = ...", new String[]{"0", "7", "5", "4"}, "4"));
        pertanyaan.add(new Soal("1 + 4 = ...", new String[]{"5", "7", "1", "4"}, "5"));
        pertanyaan.add(new Soal("1 + 5 = ...", new String[]{"3", "9", "7", "6"}, "6"));
        pertanyaan.add(new Soal("1 + 6 = ...", new String[]{"16", "66", "7", "3"}, "7"));
        pertanyaan.add(new Soal("1 + 7 = ...", new String[]{"9", "5", "8", "1"}, "8"));
        pertanyaan.add(new Soal("1 + 8 = ...", new String[]{"9", "7", "55", "123"}, "9"));
        pertanyaan.add(new Soal("1 + 9 = ...", new String[]{"10", "14", "123", "34"}, "10"));
        pertanyaan.add(new Soal("1 + 0 = ...", new String[]{"1", "2", "3", "4"}, "1"));

        Collections.shuffle(pertanyaan);
        pertanyaan = pertanyaan.subList(0,5);
        return pertanyaan;
    }
}