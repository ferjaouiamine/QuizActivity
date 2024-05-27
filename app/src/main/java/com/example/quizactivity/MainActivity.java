package com.example.quizactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton answerButton1;
    private RadioButton answerButton2;
    private RadioButton answerButton3;
    private Button submitButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions;
    private String[][] options;
    private String[] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        submitButton = findViewById(R.id.submitButton);

        questions = new String[] {
                "What is the capital of France?",
                "What is the largest planet in our solar system?",
                "What is the smallest country in the world?"
        };

        options = new String[][] {
                {"Paris", "London", "Berlin"},
                {"Jupiter", "Saturn", "Mars"},
                {"Vatican City", "Monaco", "San Marino"}
        };

        answers = new String[] {
                "Paris",
                "Jupiter",
                "Vatican City"
        };

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionTextView.setText(questions[currentQuestionIndex]);
            answerButton1.setText(options[currentQuestionIndex][0]);
            answerButton2.setText(options[currentQuestionIndex][1]);
            answerButton3.setText(options[currentQuestionIndex][2]);
        } else {
            Intent intent = new Intent(MainActivity.this, QuizResultActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("totalQuestions", questions.length);
            startActivity(intent);
        }
    }

    private void checkAnswer() {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        String selectedAnswer = "";

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            selectedAnswer = selectedRadioButton.getText().toString();
        }

        if (selectedAnswer.equals(answers[currentQuestionIndex])) {
            score++;
        }

        currentQuestionIndex++;
        displayQuestion();
    }
}