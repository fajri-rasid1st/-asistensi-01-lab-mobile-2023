package com.example.quizassignment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private TextView totalQuest, question;
    private Button optionA, optionB, optionC, optionD;
    int score = 0;
    int currentQuestionIndex = 0;
    int totalQuestion = QuestionAnswer.question.length;
    String selectedAnswer = "";
    Player player;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        question = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        totalQuest = findViewById(R.id.totalQuestion);

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result->{
            if (result.getResultCode() == RESULT_OK){
                assert result.getData() !=null;
                Player player = result.getData().getParcelableExtra("player");
                Intent intent = new Intent();
                intent.putExtra("player", player);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);

        totalQuest.setText(currentQuestionIndex+1 + " out of " +totalQuestion);
        player = getIntent().getParcelableExtra("player");

        loadNewQuestion();

    }
    void loadNewQuestion() {

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        totalQuest.setText(currentQuestionIndex+1 + " out of "+ totalQuestion);

        optionA.setBackgroundColor(Color.WHITE);
        optionB.setBackgroundColor(Color.WHITE);
        optionC.setBackgroundColor(Color.WHITE);
        optionD.setBackgroundColor(Color.WHITE);

        question.setText(QuestionAnswer.question[currentQuestionIndex]);
        optionA.setText(QuestionAnswer.option[currentQuestionIndex][0]);
        optionB.setText(QuestionAnswer.option[currentQuestionIndex][1]);
        optionC.setText(QuestionAnswer.option[currentQuestionIndex][2]);
        optionD.setText(QuestionAnswer.option[currentQuestionIndex][3]);
    }

    private void finishQuiz(){
        Intent next = new Intent(this, MainActivity3.class);
        next.putExtra("player", player);
        resultLauncher.launch(next);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;

        selectedAnswer = String.valueOf(clickedButton.getText());

        if(selectedAnswer == QuestionAnswer.correctAnswers[currentQuestionIndex]){
            clickedButton.setBackgroundColor(Color.GREEN);
            score = score + 10;
            player.setScore(score);
        }else{
            clickedButton.setBackgroundColor(Color.RED);
        }

        delay();
    }

    void delay(){
        currentQuestionIndex++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadNewQuestion();
            }
        }, 1000);
    }

}