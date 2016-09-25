package com.example.kuri.animationsintroduction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void imageClick(View view){
        ImageView resourceImage = (ImageView)view;
        ImageView animatedImage = (ImageView)findViewById(R.id.imageView3);
        animatedImage.setImageDrawable(resourceImage.getDrawable()); //set the view to be visible
        animatedImage.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_animation01); //obtain the animation file
        animatedImage.startAnimation(animation);

    }
}
