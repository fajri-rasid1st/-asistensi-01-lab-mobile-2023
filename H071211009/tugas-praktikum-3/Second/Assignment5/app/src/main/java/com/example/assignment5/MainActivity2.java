package com.example.assignment5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment5.model.Question;
import com.example.assignment5.model.User;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";

    List<Question> questionList = Question.getQuestionsList();

    //isinya button
    Button[] optionButtons;
    private Button btnA, btnB, btnC, btnD;
    private TextView tvCurrentQuestion, tvQuestion;

    //ini awal current questionnya (nanti bertambah)
    int questionNumber = 0;

    //scoreny bakal tambah klo jwbnnya bnr
    int score = 0;

    boolean isClickable = true;

    private final ActivityResultLauncher<Intent> gameResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK){
            Intent intent = result.getData();
            if (intent != null){
                Intent intent1 = new Intent();
                intent1.putExtra(MainActivity3.EXTRA_SCORE, score);
                setResult(RESULT_OK, intent1);
                finish();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvCurrentQuestion = findViewById(R.id.currentQuestion);
        tvQuestion = findViewById(R.id.question);
        btnA = findViewById(R.id.optionA);
        btnB = findViewById(R.id.optionB);
        btnC = findViewById(R.id.optionC);
        btnD = findViewById(R.id.optionD);

        optionButtons = new Button[]{
                btnA, btnB, btnC, btnD,
        };

        questionList = Question.getQuestionsList();

        btnA.setOnClickListener(view -> btnOnClick(0));
        btnB.setOnClickListener(view -> btnOnClick(1));
        btnC.setOnClickListener(view -> btnOnClick(2));
        btnD.setOnClickListener(view -> btnOnClick(3));

        //untuk tmpilkan pertanyaan
        renderQuestion();
    }

    private void renderQuestion() {
        tvCurrentQuestion.setText(String.valueOf(questionNumber + 1));
        tvQuestion.setText(questionList.get(questionNumber).getQuestion()); //ngambil pertanyaan
        for (int i = 0; i < optionButtons.length ;i++){ //ulang 4 kali,sebanyak btn
            optionButtons[i].setText(questionList.get(questionNumber).getAnswer()[i]); //ngambil jawaban di tiap bttn
            optionButtons[i].setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.blue))); //default btn color
        }

    }

    void btnOnClick(int index){
        //biar ndk bisa d klik 2 kali
        if (isClickable){
            isClickable = false;

            //ambil satu objek Question class
            Question question = questionList.get(questionNumber);
            boolean isTrue = index == question.getCorrectAnswer();

            // ubah warna tombol saat di click
            optionButtons[index].setBackgroundTintList(ColorStateList.valueOf(getColor(isTrue? R.color.green : R.color.red)));

            //score
            score += isTrue? question.getScore() : -10 ;

            //waktu delay pertanyaan sblm pindah pertanyaan
            new Handler().postDelayed(() -> {   //anonymous or lamda function
                if (questionList.size() == questionNumber + 1){
                    User user = getIntent().getParcelableExtra(MainActivity3.EXTRA_USER);
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);

                    intent.putExtra(MainActivity3.EXTRA_USER, user);
                    intent.putExtra(MainActivity3.EXTRA_SCORE, score);

                    gameResult.launch(intent);
                }else {
                    isClickable = true;
                    questionNumber += 1;
                    renderQuestion();
                }
            }, 1000);


        }
    }
}