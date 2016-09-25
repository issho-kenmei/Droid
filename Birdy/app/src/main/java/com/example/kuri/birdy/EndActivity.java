package com.example.kuri.birdy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    public static final String HIGH_SCORE = "highscore";
    TextView  scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        scoreDisplay = (TextView)findViewById(R.id.highScoreText);
        Intent intent = getIntent();
        int score = intent.getIntExtra(HIGH_SCORE,0);
        scoreDisplay.setText(Integer.toString(score));
    }
}
