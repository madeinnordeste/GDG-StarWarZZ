package com.euqueroserummacaco.beto.starwarzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    float x1,x2;
    float y1, y2;
    int swipe_event = 0;//1-"Left to Right, 2-Right to Left, 3-UP to Down,4-Down to UP
    int current_event = 3;
    int score = 0;
    TextView textViewScore;
    ImageView imageViewArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textViewScore = (TextView)findViewById(R.id.textViewScore);
        imageViewArrow = (ImageView)findViewById(R.id.imageView);

        imageViewArrow.setAlpha(50);


    }


    private void whaitEvent() {

        if( current_event == swipe_event ){
            score++;
            textViewScore.setText(""+score);
        }

        Random r = new Random();
        int i1 = r.nextInt(4 - 3) + 3;


        Log.v("###", ">> " + swipe_event+"  : "+i1);

    }





    public boolean onTouchEvent(MotionEvent touchevent)
    {

        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                if (x1 < x2)
                {
                    swipe_event = 1;
                }

                if (x1 > x2)
                {
                    swipe_event = 2;
                }

                if (y1 < y2)
                {
                    swipe_event = 3;
                }

                if (y1 > y2)
                {
                    swipe_event = 4;
                }
                break;
            }
        }

        whaitEvent();

        return false;
    }




}

