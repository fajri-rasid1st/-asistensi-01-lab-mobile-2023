package com.example.tugas3_quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class    QuizActivity extends AppCompatActivity {

    TextView quiztext, ans, bans, cans, dans;

    List<QuestionsItem> questionsItems;

    CardView cv_question, cv_ans1, cv_ans2, cv_ans3, cv_ans4;
    int currentQuestions = 0;
    int correct = 0, wrong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quiztext = findViewById(R.id.tv_question);
        ans = findViewById(R.id.tv_question_a);
        bans = findViewById(R.id.tv_question_b);
        cans = findViewById(R.id.tv_question_c);
        dans = findViewById(R.id.tv_question_d);
        cv_question = findViewById(R.id.question);
        cv_ans1 = findViewById(R.id.option_a);
        cv_ans2 = findViewById(R.id.option_b);
        cv_ans3 = findViewById(R.id.option_c);
        cv_ans4 = findViewById(R.id.option_d);


        loadAllQuestions();
        Collections.shuffle(questionsItems);
        setQuestionScreen(currentQuestions);


        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentQuestions >= 0 && currentQuestions < questionsItems.size()) {
                    if (questionsItems.get(currentQuestions).getAnswer1().equals(questionsItems.get(currentQuestions).getCorrect())) {
                        correct++;
                        ans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        ans.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        wrong++;
                        ans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        ans.setTextColor(getResources().getColor(R.color.white));
                    }

                    if (currentQuestions < questionsItems.size() - 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                currentQuestions++;
                                setQuestionScreen(currentQuestions);
                                ans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                ans.setTextColor(getResources().getColor(R.color.black));

                            }
                        }, 500);
                    } else {
                        Bundle user = getIntent().getExtras();
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        intent.putExtra("profilePict", user.getString("profilePict"));
                        intent.putExtra("username", user.getString("username"));
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        startActivity(intent);
                        finish();
                    }

                }

            }
        });

        bans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentQuestions >= 0 && currentQuestions < questionsItems.size()) {
                    if (questionsItems.get(currentQuestions).getAnswer2().equals(questionsItems.get(currentQuestions).getCorrect())) {
                        correct++;
                        bans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        bans.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        wrong++;
                        bans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        bans.setTextColor(getResources().getColor(R.color.white));
                    }

                    if (currentQuestions < questionsItems.size() - 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                currentQuestions++;
                                setQuestionScreen(currentQuestions);
                                bans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                bans.setTextColor(getResources().getColor(R.color.black));

                            }
                        }, 500);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle user = getIntent().getExtras();
                        intent.putExtra("profilePict", user.getString("profilePict"));
                        intent.putExtra("username", user.getString("username"));
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        startActivity(intent);
                        finish();
                    }

                }

            }
        });

        cans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentQuestions >= 0 && currentQuestions < questionsItems.size()) {
                    if (questionsItems.get(currentQuestions).getAnswer3().equals(questionsItems.get(currentQuestions).getCorrect())) {
                        correct++;
                        cans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        cans.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        wrong++;
                        cans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        cans.setTextColor(getResources().getColor(R.color.black));
                    }

                    if (currentQuestions < questionsItems.size() - 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                currentQuestions++;
                                setQuestionScreen(currentQuestions);
                                cans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                cans.setTextColor(getResources().getColor(R.color.black));

                            }
                        }, 500);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle user = getIntent().getExtras();
                        intent.putExtra("profilePict", user.getString("profilePict"));
                        intent.putExtra("username", user.getString("username"));
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        startActivity(intent);
                        finish();
                    }

                }

            }
        });

        dans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestions >= 0 && currentQuestions < questionsItems.size()) {
                    if (questionsItems.get(currentQuestions).getAnswer4().equals(questionsItems.get(currentQuestions).getCorrect())) {
                        correct++;
                        dans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        dans.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        wrong++;
                        dans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        dans.setTextColor(getResources().getColor(R.color.white));
                    }

                    if (currentQuestions < questionsItems.size() - 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                currentQuestions++;
                                setQuestionScreen(currentQuestions);
                                dans.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                dans.setTextColor(getResources().getColor(R.color.black));

                            }
                        }, 500);
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle user = getIntent().getExtras();
                        intent.putExtra("profilePict", user.getString("profilePict"));
                        intent.putExtra("username", user.getString("username"));
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        startActivity(intent);
                        finish();
                    }

                }

            }
        });

    }

    private void setQuestionScreen(int currentQuestions) {

        if (!questionsItems.isEmpty() && currentQuestions < questionsItems.size()) {
            quiztext.setText(questionsItems.get(currentQuestions).getQuestions());
            ans.setText(questionsItems.get(currentQuestions).getAnswer1());
            bans.setText(questionsItems.get(currentQuestions).getAnswer2());
            cans.setText(questionsItems.get(currentQuestions).getAnswer3());
            dans.setText(questionsItems.get(currentQuestions).getAnswer4());
        }
    }

    private void loadAllQuestions() {
        questionsItems = new ArrayList<>();
        String jsonquiz = loadJsonFromAsset("questions.json");
        try {
            JSONObject jsonObject = new JSONObject(jsonquiz);
            JSONArray questions = jsonObject.getJSONArray("questions");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);

                String questionsString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");

                questionsItems.add(new QuestionsItem(questionsString, answer1String, answer2String, answer3String, answer4String, correctString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJsonFromAsset(String s) {
        String json = "";
        try {
            InputStream inputStream = getAssets().open(s);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
