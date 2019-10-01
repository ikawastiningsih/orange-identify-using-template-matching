package com.ika.a32;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RGBtoGray extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(new SampleView(this));
        }

        private static class SampleView extends View {

            // CONSTRUCTOR
            public SampleView(Context context) {
                super(context);
                setFocusable(true);

            }

            @Override
            protected void onDraw(Canvas canvas) {
                Paint paint = new Paint();

                canvas.drawColor(Color.RED);


                // you need to insert a image flower_blue into res/drawable folder

                paint.setFilterBitmap(true);
                Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
                        R.drawable.rgb);
                canvas.drawBitmap(bitmapOrg, 10, 10 , paint);
                int width, height;

                ColorMatrix cm = new ColorMatrix(
                        new float[]{
                                0.5f,0.5f,0.5f,0,0,
                                0.5f,0.5f,0.5f,0,0,
                                0.5f,0.5f,0.5f,0,0,
                                0,0,0,1,0,0,
                                0,0,0,0,1,0
                        });
                cm.setSaturation(0);
                ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
                paint.setColorFilter(f);
                int h = bitmapOrg.getHeight();
                //canvas.drawBitmap(bitmapOrg, 10, 10, paint);
                canvas.drawBitmap(bitmapOrg, 10, 10 + h + 10, paint);

            }

        }
    }