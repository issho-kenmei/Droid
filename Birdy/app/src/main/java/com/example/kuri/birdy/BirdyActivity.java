package com.example.kuri.birdy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BirdyActivity extends AppCompatActivity {

    //droid bounds
    float CurrentX = 0;
    float CurrentY = 0;
    //for birdy2
    float randomX;
    float randomY;
    //for black bird
    float rX;
    float rY;
    //for black bird 2
    float rX_black_bird2;
    float rY_black_bird2;
    //for moveBird method
    //for birdy2
    float boundX;
    float boundY;
    //for black bird
    float bX;
    float bY;
    //for black bird 2
    float bX_blackBird2;
    float bY_blackBird2;
    //screen dimensions
    int height;
    int width;
    float[] point = {0,0,0,0,0,0,0,0,0};
    float[] bird_point = {0,0,0,0,0,0,0,0,0};
    float[] bird_black_point = {0,0,0,0,0,0,0,0,0};
    float[] bird_black_point2 = {0,0,0,0,0,0,0,0,0};
    int heightLayout;
    int widthLayout;
    int score = 0;
    int highScore = 0;
    TextView txt;
    boolean running = true;
    int currentTime;
    int nextTime;
    //int time;
    //int birdSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birdy);
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        PlayAreaView image = new PlayAreaView(this);
        frame.addView(image);
        txt = (TextView) findViewById(R.id.textView);
        //score = 0;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;
        //FrameLayout frameLayout = (FrameLayout)findViewById(R.id.graphics_holder);
        //heightLayout = frameLayout.getHeight();
        //widthLayout =  frameLayout.getWidth();
        currentTime = (int)System.currentTimeMillis();
        runtime(image);

    }

    private class PlayAreaView extends View {

        private GestureDetector gestures;
        private Matrix translate;
        private Bitmap droid;
        private Bitmap bird;
        private Bitmap black_bird;
        private Bitmap black_bird2;
        private Matrix bird_translate;
        private Matrix bird_black_translate;
        private Matrix bird_black_translate2;

        //constructor
        public PlayAreaView(Context context) {
            super(context);
            translate = new Matrix();
            gestures = new GestureDetector(BirdyActivity.this,
                    new GestureListener(this));
            //ImageView droid_ = (ImageView)findViewById(R.drawable.droid_g); //DOESN'T WORK
            //bird_translate.setTranslate(50.0f,50.0f);
            droid = BitmapFactory.decodeResource(getResources(),R.drawable.frog);
            bird = BitmapFactory.decodeResource(getResources(),R.drawable.birdy2);
            bird_translate = new Matrix();
            black_bird = BitmapFactory.decodeResource(getResources(),R.drawable.bird);
            bird_black_translate = new Matrix();
            black_bird2 = BitmapFactory.decodeResource(getResources(),R.drawable.blackbird2);
            bird_black_translate2 = new Matrix();
        }

        public void onMove(float dx, float dy) {
           //HARDCODED
           /* if((CurrentX + dx) > (width-700))
                return;
            if((CurrentY + dy) > (height-1000))
                return;
            if((CurrentX + dx) < 0)
                return;
            if((CurrentY + dy) < 0)
                return;
            CurrentX += dx;
            CurrentY += dy;*/

            translate.getValues(point); //Copy 9 values from the matrix into the array.
            CurrentX = point[2];
            CurrentY = point[5];

            if (((CurrentX + dx) < 0) || (CurrentX + dx) > (width - droid.getWidth()))
                return;
            if (((CurrentY + dy) < 0) || (CurrentY + dy) > (height - droid.getHeight()))
                return;

            translate.postTranslate(dx, dy);
            invalidate(); //To force a view to draw, call invalidate().
        }

        public void moveBird(float randomX, float randomY, float rX, float rY, float rX_black_bird2,float rY_black_bird2){

            //check for bounds
            //birdy2
            bird_translate.getValues(bird_point);
            boundX = bird_point[2];
            boundY = bird_point[5];
            if((boundX+randomX) < 0){randomX = 10;} //57 randomX *= -1;
            if((boundY+randomY) < 0){randomY = 10;} //50 randomY *= -1;
            if((boundX+randomX) > width - bird.getWidth()){randomX = -10;} //62 randomX *= -1;
            if((boundY+randomY) > height - bird.getHeight()){randomY = -10;} //70 randomY *= -1;

            //black bird
            bird_black_translate.getValues(bird_black_point);
            bX = bird_black_point[2];
            bY = bird_black_point[5];
            if((bX+rX) < 0){rX = 10;} //57 rX *= -1;
            if((bY+rY) < 0){rY = 10;} //50 rY *= -1;
            if((bX+rX) > width - bird.getWidth()){rX = -10;} //62 rX *= -1;
            if((bY+rY) > height - bird.getHeight()){rY = -10;} //70 rY *= -1;
            //debug block
            //txt.setText(Float.toString(boundX) + " :: " + Float.toString(boundY) + " :: " + Float.toString(randomX) + " :: " + Float.toString(randomY));
            //draw at random locations

            int c = 0;

            if ((boundX >= CurrentX) && (boundX <= (CurrentX + droid.getWidth()))) {
                if ((boundY >= CurrentY) && (boundY <= (CurrentY + droid.getHeight()/2))) {
                    if(c == 0){
                        c++;
                        score++;
                    }
                    //txt.setText("SCORE: " + Integer.toString(score) + "\t HIGH-SCORE: " + Integer.toString(highScore));
                }
            }

            if(score>=highScore){
                highScore = score;
            }
            int d = 0;

            if ((bX >= CurrentX) && (bX <= (CurrentX + droid.getWidth()))) {
                if ((bY >= CurrentY) && (bY <= (CurrentY + droid.getHeight()))) {
                    if(d == 0){
                        d++;
                        score--;
                    }
                    //handler.postDelayed(this, 300);
                    txt.setText("SCORE: " + Integer.toString(score) + "\t" +" HIGH-SCORE: " + Integer.toString(highScore));
                    if(score == -10){
                        running=false;
                        Intent intent2 = new Intent(BirdyActivity.this,EndActivity.class);
                        intent2.putExtra(EndActivity.HIGH_SCORE,highScore);
                        startActivity(intent2);
                    }
                }
            }

            if(highScore>=5){
                bird_black_translate2.getValues(bird_black_point2);
                bX_blackBird2 = bird_black_point2[2];
                bY_blackBird2 = bird_black_point2[5];
                if((bX_blackBird2+rX_black_bird2) < 0){rX_black_bird2 = 10;} //57 rX *= -1;
                if((bY_blackBird2+rY_black_bird2) < 0){rY_black_bird2 = 10;} //50 rY *= -1;
                if((bX_blackBird2+rX_black_bird2) > width - bird.getWidth()){rX_black_bird2 = -10;} //62 rX *= -1;
                if((bY_blackBird2+rY_black_bird2) > height - bird.getHeight()){rY_black_bird2 = -10;} //70 rY *= -1;

                int e = 0; //checks

                if((bX_blackBird2>=CurrentX)&&(bX_blackBird2<=CurrentX+black_bird2.getWidth())){
                    if((bY_blackBird2>=CurrentY)&&(bY_blackBird2<=CurrentY+black_bird2.getHeight())) {
                        if (e == 0) {
                            e++;
                            score--;
                        }
                    }
                }
                if(score == -10){
                    running=false;
                    Intent intent2 = new Intent(BirdyActivity.this,EndActivity.class);
                    intent2.putExtra(EndActivity.HIGH_SCORE,highScore);
                    startActivity(intent2);
                }

                bird_black_translate2.preTranslate(rX_black_bird2,rY_black_bird2);

            }
            bird_translate.postTranslate(randomX,randomY);
            bird_black_translate.postTranslate(rX,rY);
            nextTime = (int)System.currentTimeMillis();
            invalidate();
        }


        public void onResetLocation(){
            translate.reset(); //Set the matrix to identity
            invalidate();
        }

        /*public void onSetLocation(float dx, float dy) {
            translate.postTranslate(dx, dy);
        }*/

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(droid, translate, null);
            canvas.drawBitmap(bird,bird_translate,null);
            canvas.drawBitmap(black_bird,bird_black_translate,null);
            if(highScore>=5){
                canvas.drawBitmap(black_bird2,bird_black_translate2,null);
            }

             /*if((boundX >= CurrentX)&&(boundX <= (CurrentX+droid.getWidth()))) {
                a++;
            }
            if((boundY >= CurrentY)&&(boundY <= (CurrentY+droid.getHeight()))){
                b++;
            }

            if((a == 1)&&(b == 1)){
                score++;
                txt.setText(Integer.toString(score));
            }*/
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return gestures.onTouchEvent(event);
        }

    }

    public void runtime(final PlayAreaView playAreaView){ //PlayAreaView is accessed within the inner class,
        // needs to be declared final
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(running) {
                    randomX = (float) ((Math.random()) - 0.5);
                    randomX *= 1000;
                    randomY = (float) ((Math.random()) - 0.5);
                    randomY *= 1000;

                    rX = (float) ((Math.random()) - 0.5);
                    rX *= 1000;
                    rY = (float) ((Math.random()) - 0.5);
                    rY *= 1000;

                    rX_black_bird2 = (float) ((Math.random()) - 0.5);
                    rX_black_bird2 *= 1000;
                    rY_black_bird2 = (float) ((Math.random()) - 0.5);
                    rY_black_bird2 *= 1000;
                    playAreaView.moveBird(randomX, randomY, rX, rY, rX_black_bird2, rY_black_bird2);
                }else{
                    return;
                }
                /*if((nextTime - currentTime)>10000){
                    handler.postDelayed(this,297);
                }*/
                handler.postDelayed(this,300);
            }
        });
    }


    private class GestureListener implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {

        PlayAreaView view;

        public GestureListener(PlayAreaView view) {
            this.view = view;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               final float velocityX, final float velocityY) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            view.onResetLocation();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            view.onMove(-distanceX, -distanceY);
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
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }
    }
}
