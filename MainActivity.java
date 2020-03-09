package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextView;
    ArrayList<Integer> answers;//new ArrayList<>();
    TextView pointsTextView;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions =0;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    TextView sumTextView;
    TextView timerTextView;

    Button playAgain;
    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        generateQuestion();
        new CountDownTimer(10100,1000)
        {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText(String.valueOf(score)+"/"+String.valueOf(numberOfQuestions));
                playAgain.setVisibility(View.VISIBLE) ;

            }
        }.start();
        playAgain.setVisibility(View.INVISIBLE);
    }
    public void generateQuestion()
    {
        answers = new ArrayList<>();
        Random rand  = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(String.valueOf(a)+" + "+String.valueOf(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        for(int i=0;i<4;i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(i,a+b);
            }
            else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    answers.add(i,rand.nextInt(41));
                }
                answers.add(i,incorrectAnswer);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            Log.i("Correct","Correct");
            score++;
            resultTextView.setText("Correct!");

        }
        else
        {
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();
    }
    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        sumTextView = findViewById(R.id.sumTextView);
       // answers = new ArrayList<>();
        //TextView sumTextView = findViewById(R.id.sumTextView);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         button4 = findViewById(R.id.button4);
         timerTextView = findViewById(R.id.timerTextView);
         playAgain = findViewById(R.id.playAgain);
         playAgain.setVisibility(View.INVISIBLE);
         //generateQuestion();
         playAgain(findViewById(R.id.timerTextView));
    }
}
