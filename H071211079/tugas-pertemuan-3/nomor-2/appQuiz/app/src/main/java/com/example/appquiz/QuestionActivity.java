package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.example.appquiz.databinding.QuestionBinding;
import com.example.appquiz.model.QuestionAnswer;

@SuppressLint("SetTextI18n")
public class QuestionActivity extends AppCompatActivity {

    private QuestionBinding binding;

    int score=0;
    int editText1 = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    int amountQuestion = 1;

    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = QuestionBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btn1.setOnClickListener(view -> setTextOnClick(binding.btn1));
        binding.btn2.setOnClickListener(view -> setTextOnClick(binding.btn2));
        binding.btn3.setOnClickListener(view -> setTextOnClick(binding.btn3));
        binding.btn4.setOnClickListener(view -> setTextOnClick(binding.btn4));

        binding.editText1.setText("Quiz "+amountQuestion+" of "+editText1);
        loadNewQuestion();
    }

    private void setTextOnClick(Button clickedButton) {

        binding.btn1.setBackgroundColor(Color.rgb(160, 82, 45));
        binding.btn2.setBackgroundColor(Color.rgb(160, 82, 45));
        binding.btn3.setBackgroundColor(Color.rgb(160, 82, 45));
        binding.btn4.setBackgroundColor(Color.rgb(160, 82, 45));


        if (clickedButton.getId()==R.id.btn1 || clickedButton.getId()==R.id.btn2 || clickedButton.getId()==R.id.btn3 || clickedButton.getId()==R.id.btn4) {
            selectedAnswer = clickedButton.getText().toString();
            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])) {
                clickedButton.setBackgroundColor(Color.GREEN);
                score = score + 100;
            } else  {
                clickedButton.setBackgroundColor(Color.RED);
            }
            currentQuestionIndex++;
            amountQuestion++;

            new Handler().postDelayed(() -> {
                if(currentQuestionIndex == editText1){
                    String name = getIntent().getStringExtra("extra_name");
                    String image = getIntent().getStringExtra("PROFILE_IMAGE_URI");

                    Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
                    intent.putExtra("extra_name", name);
                    intent.putExtra("PROFILE_IMAGE_URI", image);
                    intent.putExtra("total_score", score);
                    startActivity(intent);
                    return;
                }else {
                    loadNewQuestion();
                    clickedButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.sienna3));
                    binding.editText1.setText("Question " + amountQuestion + " of " + editText1);
                }
            },500);

        }


    }

    void loadNewQuestion() {

        binding.editText2.setText(QuestionAnswer.question[currentQuestionIndex]);
        binding.btn1.setText(QuestionAnswer.choises[currentQuestionIndex][0]);
        binding.btn2.setText(QuestionAnswer.choises[currentQuestionIndex][1]);
        binding.btn3.setText(QuestionAnswer.choises[currentQuestionIndex][2]);
        binding.btn4.setText(QuestionAnswer.choises[currentQuestionIndex][3]);
    }


}