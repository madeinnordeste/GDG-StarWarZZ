package com.euqueroserummacaco.beto.starwarzz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String playerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuizz(View view) {

        EditText editTextName = (EditText)findViewById(R.id.editTextName);
        String name = editTextName.getText().toString();

        if( name.isEmpty() ){

            String text = "Para iniciar é necessário informar seu nome";

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


        }else{
            playerName = name;
            Intent intent = new Intent(this, QuizzActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, playerName);
            startActivity(intent);
        }

    }
}
