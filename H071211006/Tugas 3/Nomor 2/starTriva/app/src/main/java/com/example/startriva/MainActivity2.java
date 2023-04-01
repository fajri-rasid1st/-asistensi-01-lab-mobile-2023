package com.example.startriva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private int currentIndex = 0, userScore = 0;
    private List<QuestionAnswer> questionAnswerList;
    public static final String EXTRA_PLAYER = "extra_player";
    private TextView[] answers;
    private TextView question, noQuestion, answerA, answerB, answerC, answerD;

    private  View[] viewAnswers;
    private View viewA, viewB, viewC, viewD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        question = findViewById(R.id.tv_question);
        noQuestion = findViewById(R.id.tv_noQuestion);
        answerA = findViewById(R.id.tv_answerA);
        answerB = findViewById(R.id.tv_answerB);
        answerC = findViewById(R.id.tv_answerC);
        answerD = findViewById(R.id.tv_answerD);
        viewA = findViewById(R.id.v_answerA);
        viewB = findViewById(R.id.v_answerB);
        viewC = findViewById(R.id.v_answerC);
        viewD = findViewById(R.id.v_answerD);

        questionAnswerList = QuestionAnswer.getAllQuestion();

        answers = new TextView[] {answerA, answerB, answerC, answerD};
        viewAnswers = new View[] {viewA, viewB, viewC, viewD};

        renderQuestion();
        answers[0].setOnClickListener(view -> onAnswerClicked(0));
        answers[1].setOnClickListener(view -> onAnswerClicked(1));
        answers[2].setOnClickListener(view -> onAnswerClicked(2));
        answers[3].setOnClickListener(view -> onAnswerClicked(3));
    }

    private void renderQuestion() {
        QuestionAnswer questionAnswer = questionAnswerList.get(currentIndex);
        noQuestion.setText(String.format("Quiz %d of 5", currentIndex +1));
        question.setText(questionAnswer.getQuestion());
        for (int i = 0; i<answers.length; i++){
            answers[i].setText(questionAnswer.getAnswer()[i]);
        }
    }

    private void onAnswerClicked(int index) {
        QuestionAnswer questionAnswer = questionAnswerList.get(currentIndex);
        boolean isTrue = index == questionAnswer.getCorrectAnswer();

        userScore += isTrue ? questionAnswer.getScore() : 0;

        viewAnswers[index].setBackground(isTrue ? getDrawable(R.drawable.correct_background) :  getDrawable(R.drawable.wrong_background));

        new Handler().postDelayed(()-> {
            if (currentIndex == 4){
                Player player = getIntent().getParcelableExtra(EXTRA_PLAYER);
                Intent intent = new Intent(this, MainActivity3.class);
                intent.putExtra(MainActivity3.EXTRA_SCORE, userScore);
                intent.putExtra(EXTRA_PLAYER, player);
                startActivity(intent);
            }else {
                viewAnswers[index].setBackground(getDrawable(R.drawable.answer_background));
                currentIndex++;
                renderQuestion();
            }
        }, 500) ;

    }
}