package com.euqueroserummacaco.beto.starwarzz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class QuizzActivity extends AppCompatActivity {

    int certainQuestions = 0;
    int loadedQuestions = 0;
    int maxQuestions = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
    }

    public void submitAnswer(View view) {

        int points = Integer.parseInt( (String)view.getTag() );
        certainQuestions+=points;

        ProgressBar progressBarForce = (ProgressBar) findViewById(R.id.progressBarForce);

        progressBarForce.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBarForce.setProgress(50);



        String text = "Pontos: "+certainQuestions;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();



    }
}
