package com.euqueroserummacaco.beto.starwarzz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.math.BigInteger;
import java.security.SecureRandom;


public class QuizzActivity extends AppCompatActivity {

    int certainQuestions = 0;
    int loadedQuestions = 0;
    int maxQuestions = 5;
    int xmlQuestionsCount = 0;


    //views
    protected TextView textViewQuestion;
    protected Button buttonAnswerOne;
    protected Button buttonAnswerTwo;
    protected Button buttonAnswerThree;
    protected ProgressBar progressBarForce;
    protected ProgressBar progressBarProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        buttonAnswerOne = (Button) findViewById(R.id.buttonAnswerOne);
        buttonAnswerTwo = (Button) findViewById(R.id.buttonAnswerTwo);
        buttonAnswerThree = (Button) findViewById(R.id.buttonAnswerThree);
        progressBarForce = (ProgressBar) findViewById(R.id.progressBarForce);
        progressBarProgress = (ProgressBar) findViewById(R.id.progressBarProgress);

        /*
        progressBarForce.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBarProgress.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        */

        progressBarForce.setProgress(0);
        progressBarProgress.setProgress(0);






        loadQuestion();
    }

    protected void calculateForce(){
        //force
        if( certainQuestions > 1 ){
            int f = (100 * certainQuestions) / maxQuestions;
            progressBarForce.setProgress(f);
        }
    }

    private void calculateProgress() {
        //progress
        if( loadedQuestions > 1 ){
            int p = (100 * loadedQuestions) / maxQuestions;
            progressBarProgress.setProgress(p);
        }
    }

    protected void loadQuestion(){

        calculateForce();
        calculateProgress();

        loadedQuestions++;

        if( loadedQuestions > maxQuestions){

            openResultsActivity();

        }else{

            SecureRandom random = new SecureRandom();
            String rr = new BigInteger(130, random).toString(32);

            textViewQuestion.setText("Questao 1 "+rr);

            buttonAnswerOne.setText("Resposta 1 1");
            buttonAnswerOne.setTag("1");

            buttonAnswerTwo.setText("Resposta 2 0");
            buttonAnswerTwo.setTag("0");

            buttonAnswerThree.setText("Resposta 3 0");
            buttonAnswerThree.setTag("0");

        }




    }



    public void submitAnswer(View view) {

        //calcula os pontos
        int points = Integer.parseInt((String) view.getTag());
        certainQuestions+=points;


        String text = "Pontos: "+certainQuestions+" p:"+certainQuestions;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        loadQuestion();



    }


    private void openResultsActivity(){

        Intent intent = new Intent(this, ResultActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, playerName);
        startActivity(intent);

    }
}
