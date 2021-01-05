package com.example.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

   TextView queTextView;
   TextView ansTextView;
   TextView timerTextView;
    Button op1;
    Button op2;
    Button op3;
    Button op4;
    Button goButton;
    Button playAgain;

    TextView resultTextView;
    int locationCorrectAnswer;
    int score = 0;
    int noOfQuestion=0;
    ArrayList<Integer> answers = new ArrayList<>();

    public void chooseAnswer(View view){

        if(Integer.toString(locationCorrectAnswer).equals(view.getTag().toString())){
            score++;

        }
        noOfQuestion++;
        ansTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));
        newQuestion();
    }

   public void start(View view){
       goButton.setVisibility(View.INVISIBLE);
       op1.setVisibility(View.VISIBLE);
       op2.setVisibility(View.VISIBLE);
       op3.setVisibility(View.VISIBLE);
       op4.setVisibility(View.VISIBLE);
       timerTextView.setVisibility(View.VISIBLE);
       queTextView.setVisibility(View.VISIBLE);
       ansTextView.setVisibility(View.VISIBLE);



   }
   public void playAgain(View view){
        score = 0;
        noOfQuestion = 0;
        timerTextView.setText("30s");
       ansTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestion));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);

       new CountDownTimer(30000+100,1000){

           @Override
           public void onTick(long millisUntilFinished) {
               timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
           }

           @Override
           public void onFinish() {
               playAgain.setVisibility(View.VISIBLE);

           }
       }.start();

   }
   public void newQuestion(){
       Random rand = new Random();
       int a = rand.nextInt(21);
       int b = rand.nextInt(21);
       queTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

       locationCorrectAnswer = rand.nextInt(4);
       answers.clear();

       for(int i=0;i<4;i++){
           if(i==locationCorrectAnswer){
               answers.add(a+b);
           }else{
               int wrongAnswer = rand.nextInt(41);

               while (wrongAnswer == a+b)
                   wrongAnswer = rand.nextInt(41);
               answers.add(wrongAnswer);
           }

       }
       op1.setText(Integer.toString(answers.get(0)));
       op2.setText(Integer.toString(answers.get(1)));
       op3.setText(Integer.toString(answers.get(2)));
       op4.setText(Integer.toString(answers.get(3)));


   }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queTextView = findViewById(R.id.queTextView);
        ansTextView = findViewById(R.id.ansTextView);
        timerTextView = findViewById(R.id.timerTextView);
        op1 = findViewById(R.id.opt1);
        op2 = findViewById(R.id.opt2);
        op3 = findViewById(R.id.opt3);
        op4 = findViewById(R.id.opt4);
        goButton = findViewById(R.id.goButton);
        playAgain = findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.timerTextView));

    }
}