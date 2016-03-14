package com.euqueroserummacaco.beto.starwarzz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    String playerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Context context = getApplicationContext();
        //XmlResourceParser istream = context.getResources().open

        Resources res = this.getResources();
        XmlResourceParser parser = res.getXml(R.xml.questions);

        try {
            int event = parser.getEventType();
            Log.v("MainActivity", ""+R.xml.questions);
            Log.v("event", ""+R.xml.questions);
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                Log.v("event", ""+event+" : "+XmlPullParser.END_DOCUMENT);
                Log.v("name", ""+name);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



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
            Intent intent = new Intent(this, GameActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, playerName);
            startActivity(intent);
        }

    }
}
