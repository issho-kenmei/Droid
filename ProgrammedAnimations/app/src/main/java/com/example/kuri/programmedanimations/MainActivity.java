package com.example.kuri.programmedanimations;

import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    RotateAnimation rotateAnimation;
    RotateAnimation rotateAnimation2;
    TextView text;
    ImageView image;
    int x = 0;
    int i = 0;
    float speed;
    int stopTime;
    int startTime;
    int time;
    float endDegrees = 90;
    float startDegrees = 0;
    int duration = 1000;
    float angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {
        image = (ImageView) findViewById(R.id.imageView);
        text = (TextView) findViewById(R.id.text);
        /*TranslateAnimation translateAnimation = new TranslateAnimation(100, 0, 10, 10);
        TranslateAnimation translateAnimation_ = new TranslateAnimation(Animation.ABSOLUTE, 10,
                Animation.ABSOLUTE, 30,
                Animation.RELATIVE_TO_SELF, 10,
                Animation.RELATIVE_TO_PARENT, 10);*/
        rotateAnimation = new RotateAnimation(startDegrees, endDegrees, 0.1f, 0.1f);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        x = 1;
        startTime = (int)System.currentTimeMillis();//(int)rotateAnimation.getStartTime();



        //rotateAnimation2.setDuration(0);


        image.startAnimation(rotateAnimation);
        //x = 1;

        /*if(x<0){
           image.startAnimation(rotateAnimation);
        }
        if(x>0){
            image.startAnimation(rotateAnimation2);
        }*/

        //rotateAnimation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
        //image.setAnimation(rotateAnimation);

        //SET ANIMATION

        /*RotateAnimation rotate = new RotateAnimation(0,360,0.5f,0.5f);
        ScaleAnimation scale = new ScaleAnimation(0,1,0,1);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        set.setDuration(2000);
        //set.setRepeatMode(Animation.REVERSE);
        //set.setRepeatCount(Animation.INFINITE);*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {





        if(event.getAction() == MotionEvent.ACTION_DOWN){

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i++;
                    String y = Integer.toString(i);
                    text.setText(y);
                    click(v);
                }
            });
            //resets --> not this way
            //rotateAnimation.setRepeatCount(0);
            //rotateAnimation.setDuration(0);
            if(x>0) {
                stopTime = (int) System.currentTimeMillis();
            }
            time = stopTime - startTime;
            //rotateAnimation.gets
            speed = (endDegrees - startDegrees)/duration;

            //if(x>0)
            angle = speed*time;


            angle = angle%90;

            x = -1;
//            float blah = image.getRotation();
//            float x1 = image.getRotationX();
//            float x2 = image.getRotationY();
//
//            long bl = image.getAnimation().getDuration();
            //long bl1 = image.getAnimation().getInterpolator();


            //rotateAnimation2 = new RotateAnimation(image.getRotation(),image.getRotation(),0.1f,0.1f);
            rotateAnimation2 = new RotateAnimation((int)angle,(int)angle,0.1f,0.1f);

            rotateAnimation2.setDuration(10);
            rotateAnimation2.setRepeatCount(Animation.INFINITE);
            //rotateAnimation2.setRepeatCount(0);
            //rotateAnimation2.setRepeatMode(Animation.REVERSE);


            image.startAnimation(rotateAnimation2);




        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}





/*
 getStartTime()
 Added in API level 1
 long getStartTime ()
 When this animation should start. If the animation has not startet yet,
 this method might return START_ON_FIRST_FRAME.
 */


/*void setDuration (long durationMillis)
How long this animation should last. The duration cannot be negative.*/

/**void setRepeatCount (int repeatCount)
 Sets how many times the animation should be repeated. If the repeat count is 0,
 the animation is never repeated. If the repeat count is greater than 0 or INFINITE,
 the repeat mode will be taken into account. The repeat count is 0 by default./


 /*void setRepeatMode (int repeatMode)
 Defines what this animation should do when it reaches the end.
 This setting is applied only when the repeat count is either greater than 0 or INFINITE. Defaults to RESTART.

 Related XML Attributes:

 android:repeatMode
 Parameters
 repeatMode	int: RESTART or REVERSE*/


/*TranslateAnimation(float fromXDelta, float toXDelta,
                   float fromYDelta, float toYDelta)

TranslateAnimation(int fromXType, float fromXValue,
                   int toXType, float toXValue,
                   int fromYType, float fromYValue,
                   int toYType, float toYValue)

AlphaAnimation(float fromAlpha, float toAlpha)

RotateAnimation(float fromDegrees, float toDegrees)

RotateAnimation(float fromDegrees, float toDegrees,
                float pivotX, float pivotY)

RotateAnimation(float fromDegrees, float toDegrees,
                int pivotXType, float pivotXValue,
                int pivotYType, float pivotYValue)

ScaleAnimation(float fromX, float toX,
               float fromY, float toY)

ScaleAnimation(float fromX, float toX,
               float fromY, float toY,
               float pivotX, float pivotY)

ScaleAnimation(float fromX, float toX,
               float fromY, float toY,
               int pivotXType, float pivotXValue,
               int pivotYType, float pivotYValue)

TranslateAnimation(float fromXDelta, float toXDelta,
                   float fromYDelta, float toYDelta)

TranslateAnimation(int fromXType, float fromXValue,
                   int toXType, float toXValue,
                   int fromYType, float fromYValue,
                   int toYType, float toYValue)

AnimationSet(boolean shareInterpolator)*/
