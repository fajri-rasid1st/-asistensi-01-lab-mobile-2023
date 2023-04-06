package com.example.fikquis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Questions2 extends AppCompatActivity implements OnClickListener {
    private Questions mQuestionLibrary = new Questions();
    TextView mQuestionsView, quesNum;
    Button btnA, btnB, btnC, btnD;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2);

        mQuestionsView = findViewById(R.id.questions);
        quesNum = findViewById(R.id.quesNum);
        btnA = findViewById(R.id.a);
        btnB = findViewById(R.id.b);
        btnC = findViewById(R.id.c);
        btnD = findViewById(R.id.d);
        updateQuestion();
        QuestionNumber(mScore);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    private void updateQuestion() {
        if (mQuestionNumber < mQuestionLibrary.getLength()) {
            mQuestionsView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            btnA.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            btnB.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            btnC.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            btnD.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        } else {
            String iName = getIntent().getStringExtra("inputName");
            Intent intent = new Intent(Questions2.this, ResultScore.class);
            intent.putExtra("score", mScore);
            intent.putExtra("inputName", iName);
            startActivity(intent);
        }
    }

    private void QuestionNumber(int point) {
        quesNum.setText("Total Pertanyaan " + mQuestionLibrary.getLength());
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        Button answer = (Button) view;
        if (answer.getText().toString() == mAnswer) {
            answer.setBackgroundColor(Color.GREEN);
            mScore = mScore + 20;
            Toast.makeText(Questions2.this, "Benar!", Toast.LENGTH_SHORT).show();
        } else {
            answer.setBackgroundColor(Color.RED);
            Toast.makeText(Questions2.this, "Salah!", Toast.LENGTH_SHORT).show();

        }

        new Handler().postDelayed(()->{
            updateQuestion();
            answer.setBackgroundColor(android.R.color.white);
        },1000);
    }
}