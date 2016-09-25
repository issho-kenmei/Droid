package com.example.kuri.droid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //droid bounds
    float CurrentX = 0;
    float CurrentY = 0;
    //for birdy2
    float randomX;
    float randomY;

    //for bird
    float rX;
    float rY;

    //for moveBird method
    //for birdy2
    float boundX;
    float boundY;

    //for black bird
    float bX;
    float bY;

    //screen dimensions
    int height;
    int width;
    float[] point = {0,0,0,0,0,0,0,0,0};
    float[] bird_point = {0,0,0,0,0,0,0,0,0};
    float[] bird_black_point = {0,0,0,0,0,0,0,0,0};
    int heightLayout;
    int widthLayout;
    int score = 0;
    int highScore = 0;

    TextView txt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        runtime(image);

    }

    private class PlayAreaView extends View {

        private GestureDetector gestures;
        private Matrix translate;
        private Bitmap droid;

        private Bitmap bird;
        private Bitmap black_bird;
        private Matrix bird_translate;
        private Matrix bird_black_translate;

        //constructor
        public PlayAreaView(Context context) {
            super(context);
            translate = new Matrix();
            gestures = new GestureDetector(MainActivity.this,
                    new GestureListener(this));
            //ImageView droid_ = (ImageView)findViewById(R.drawable.droid_g); //DOESN'T WORK
            //bird_translate.setTranslate(50.0f,50.0f);
            droid = BitmapFactory.decodeResource(getResources(),R.drawable.frog);
            bird = BitmapFactory.decodeResource(getResources(),R.drawable.birdy2);
            bird_translate = new Matrix();
            black_bird = BitmapFactory.decodeResource(getResources(),R.drawable.bird);
            bird_black_translate = new Matrix();
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

        public void moveBird(float randomX, float randomY, float rX, float rY){

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

//            if((boundX<=0)||(boundY<=0)){
//                randomX = 300;
//                randomY = 300;
//            }

            int c = 0;

            if ((boundX >= CurrentX) && (boundX <= (CurrentX + droid.getWidth()))) {
                if ((boundY >= CurrentY) && (boundY <= (CurrentY + droid.getHeight()/2))) {
                    if(c == 0){
                        c++;
                        score++;
                    }
                    //handler.postDelayed(this, 300);
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
                    txt.setText("SCORE: " + Integer.toString(score) + "\t HIGH-SCORE: " + Integer.toString(highScore));
                }
            }



            bird_translate.postTranslate(randomX,randomY);
            bird_black_translate.postTranslate(rX,rY);
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
            // Log.v(DEBUG_TAG, "onDraw");
            canvas.drawBitmap(droid, translate, null);
            canvas.drawBitmap(bird,bird_translate,null);
            canvas.drawBitmap(black_bird,bird_black_translate,null);
            //Matrix m = canvas.getMatrix();
            //Log.d(DEBUG_TAG, "Matrix: " + translate.toShortString());
            //Log.d(DEBUG_TAG, "Canvas: " + m.toShortString());

            //scoring
//            final Handler handler = new Handler();
//            handler.post(new Runnable() {
//                @Override
//                public void run() {


//                    handler.postDelayed(this,10000);
//                }
//            });




//            if((boundX >= CurrentX)&&(boundX <= (CurrentX+droid.getWidth()))) {
//                a++;
//            }
//            if((boundY >= CurrentY)&&(boundY <= (CurrentY+droid.getHeight()))){
//                b++;
//            }
//
//            if((a == 1)&&(b == 1)){
//                score++;
//                txt.setText(Integer.toString(score));
//            }

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
                randomX = (float)((Math.random()) - 0.5);
                randomX *= 1000;
                randomY = (float)((Math.random()) - 0.5);
                randomY *= 1000;

                rX = (float)((Math.random()) - 0.5);
                rX *= 1000;
                rY = (float)((Math.random()) - 0.5);
                rY *= 1000;

                playAreaView.moveBird(randomX,randomY,rX,rY);
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
            return false;
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
