package com.euqueroserummacaco.beto.starwarzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    String playerName;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playerName = getIntent().getExtras().getString("playerName");
        score = getIntent().getExtras().getInt("score");

        Log.v("STARWARZZ", "" + playerName );
        Log.v("STARWARZZ", "" + score);

        TextView textViewResultName = (TextView) findViewById(R.id.textViewResultName);
        textViewResultName.setText(playerName);

        TextView textViewResultDescription = (TextView) findViewById(R.id.textViewResultDescription);

        if( score <= 25){
            textViewResultDescription.setText("Infelizmente você não possui \"Força\" suficiente para ser um Jedi");
        }else if(score > 25 && score <= 50){
            textViewResultDescription.setText("Você é um forte Padawan");
        }else if(score > 50 && score <= 75){
            textViewResultDescription.setText("Você tem o mesmo potencial de um Cavaleiro Jedi");
        }else if(score > 75){
            textViewResultDescription.setText("Tenho certeza que você será um Mestre Jedi");
        }

    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, QuizzActivity.class);
        intent.putExtra("playerName", playerName);
        startActivity(intent);
    }
}
