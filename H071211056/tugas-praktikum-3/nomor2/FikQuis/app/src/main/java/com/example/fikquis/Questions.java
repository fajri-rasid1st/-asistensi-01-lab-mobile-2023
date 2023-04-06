package com.example.fikquis;

public class Questions {

    private static String textQuestions[] = {
            "1. Siapa Bapak naruto?",
            "2. Siapa Mama naruto?",
            "3. Siapa Kakek naruto?",
            "4. Siapa Yang Mengkorupsi Uang Naruto? ",
            "5. Siapa Beban Naruto?"
    };

    private static String multipleChoice[][]={
            {"Hiruzen", "Minato", "Yamato", "Orochimaru"},
            {"Kushina", "Datebane", "Windah", "Tsunade"},
            {"Konohamaru","Zoro","Jiraiya","Danzo"},
            {"Danzo","Hiruzen Uchiha","guy","Kaguya"},
            {"Sakura","Boruto","Himawari","Penduduk Desa Konoha"}

    };
    private static String mCorrectAnswers[] = {"Minato","Kushina","Jiraiya","Hiruzen Uchiha","Sakura"};

    public int getLength(){return textQuestions.length;}


    public String getQuestion(int a){
        String question = textQuestions[a];
        return question;
    }

    public String getChoice(int index, int num){
        String choice0 = multipleChoice[index][num-1];
        return choice0;
    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }
}