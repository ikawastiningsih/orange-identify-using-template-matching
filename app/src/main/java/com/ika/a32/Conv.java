package com.ika.a32;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;

public class Conv extends AppCompatActivity {


    private static String TAG = "MainActivity";
    static {
        if(OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCv Sukses di Install");
        }else {
            Log.d(TAG, "OpenCv Gagal di Install");
        }
    }
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv);

        //Reading the image

        Mat src = null;
        try {
            src = Utils.loadResource(Conv.this, R.drawable.bm1,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //Writing the image set to bitmap
        Bitmap bitmap = Bitmap.createBitmap(src.cols(), src.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(src, bitmap);

        ImageView iv = (ImageView) findViewById(R.id.coba);
        iv.setImageBitmap(bitmap);








    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}