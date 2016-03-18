package com.euqueroserummacaco.beto.starwarzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class QuizzActivity extends AppCompatActivity {

    String playerName;
    int certainQuestions = 0;
    int loadedQuestions = 0;
    int questionsCounter = 0;
    JSONArray questionsList;
    int maxQuestions = 5;

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

        playerName = getIntent().getExtras().getString("playerName");

        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        buttonAnswerOne = (Button) findViewById(R.id.buttonAnswerOne);
        buttonAnswerTwo = (Button) findViewById(R.id.buttonAnswerTwo);
        buttonAnswerThree = (Button) findViewById(R.id.buttonAnswerThreez);
        progressBarForce = (ProgressBar) findViewById(R.id.progressBarForce);
        progressBarProgress = (ProgressBar) findViewById(R.id.progressBarProgress);

        progressBarForce.setProgress(0);
        progressBarProgress.setProgress(0);


        try {
            JSONObject reader = new JSONObject( loadJSONFromAsset() );
            questionsList = reader.getJSONArray("list");
            questionsCounter = questionsList.length();
            /*
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject objj = jsonarray.getJSONObject(i);
                Log.v("JDEBUGTITLE", ""+objj.getString("title"));
            }
            */

        } catch (JSONException e) {
            e.printStackTrace();
        }

        loadQuestion();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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

            Random r = new Random();
            int questionIndex = r.nextInt(questionsCounter - 1) + 1;
            //Log.v("JDEBUGTITLE", ""+questionIndex);

            try {

                JSONObject currentQuestion = questionsList.getJSONObject(questionIndex);
                textViewQuestion.setText(currentQuestion.getString("title"));

                JSONArray alternativesList = currentQuestion.getJSONArray("alternatives");

                JSONObject alternativeOne = alternativesList.getJSONObject(0);
                buttonAnswerOne.setText( alternativeOne.getString("title") );
                buttonAnswerOne.setTag(alternativeOne.getString("points"));

                JSONObject alternativeTwo = alternativesList.getJSONObject(1);
                buttonAnswerTwo.setText( alternativeTwo.getString("title") );
                buttonAnswerTwo.setTag(alternativeTwo.getString("points"));

                JSONObject alternativeThree = alternativesList.getJSONObject(2);
                buttonAnswerThree.setText(alternativeThree.getString("title"));
                buttonAnswerThree.setTag(alternativeThree.getString("points"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }



    public void submitAnswer(View view) {

        //calcula os pontos
        int points = Integer.parseInt((String) view.getTag());
        certainQuestions+=points;

        loadQuestion();

    }


    private void openResultsActivity(){

        Double percent = ((double)certainQuestions/maxQuestions) * 100;
        // Log.v("JDEBUGTITLE", ""+objj.getString("title"));
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("playerName", playerName);
        intent.putExtra("score", percent.intValue());
        startActivity(intent);

    }
}
