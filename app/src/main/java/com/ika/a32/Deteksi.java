package com.ika.a32;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class Deteksi extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{


    String gab="";
    int jd=78;
    Button btnCek;
    String[]arJeruk;
    String[]arNama;

    String keterangan="";
    String kategori="";
    String analisa="";
    String nmin="";
    String nmin1="";


    private static final String TAG = "OCVSample::Activity";
    private static final int REQUEST_PERMISSION = 100;
    private int w, h;
    private CameraBridgeViewBase mOpenCvCameraView;
    TextView tvName;
    Scalar RED = new Scalar(255, 0, 0);
    Scalar GREEN = new Scalar(0, 255, 0);
    FeatureDetector detector;
    DescriptorExtractor descriptor;
    DescriptorMatcher matcher;

    Mat descriptors2;///kamera
    MatOfKeyPoint keypoints2;

    MatOfKeyPoint [] key1;//kamera
    Mat desc1[],citra1[];

    static {
        if (!OpenCVLoader.initDebug())
            Log.d("ERROR", "Maaf tidak bisa membuka kamera");
        else
            Log.d("SUCCESS", "Mohon tunggu");
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                    // mOpenCvCameraView.enableView();
                    try {
                        initializeOpenCVDependencies();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    private void initializeOpenCVDependencies() throws IOException {
        mOpenCvCameraView.enableView();
        detector = FeatureDetector.create(FeatureDetector.ORB);
        descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        Button btnCek=(Button)findViewById(R.id.btnCek);
        btnCek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i=new Intent(Deteksi.this,Hasil.class);
                i.putExtra("keterangan",keterangan);
                i.putExtra("kategori",kategori);
                i.putExtra("analisa",analisa);
                i.putExtra("nmin",nmin1);
                startActivity(i);


                finish();
            }});


        jd=105;
        arJeruk=new String[jd];
        arNama=new String[jd];
        desc1=new Mat[jd];
        citra1=new Mat[jd];
        key1=new MatOfKeyPoint[jd];

        arJeruk[0]="bm1.jpg";
        arJeruk[1]="bm2.jpg";
        arJeruk[2]="bm3.jpg";
        arJeruk[3]="bm4.jpg";
        arJeruk[4]="bm5.jpg";
        arJeruk[5]="bm6.jpg";
        arJeruk[6]="bm7.jpg";
        arJeruk[7]="bm8.jpg";
        arJeruk[8]="bm9.jpg";
        arJeruk[9]="bm10.jpg";
        arJeruk[10]="bm11.jpg";
        arJeruk[11]="bm12.jpg";
        arJeruk[12]="bm13.jpg";
        arJeruk[13]="bm14.jpg";
        arJeruk[14]="bm15.jpg";
        arJeruk[15]="bm16.jpg";
        arJeruk[16]="bm17.jpg";
        arJeruk[17]="bm18.jpg";
        arJeruk[18]="bm19.jpg";
        arJeruk[19]="bm20.jpg";
        arJeruk[20]="bm21.jpg";
        arJeruk[21]="bm22.jpg";
        arJeruk[22]="bm23.jpg";
        arJeruk[23]="bm24.jpg";
        arJeruk[24]="bm25.jpg";
        arJeruk[25]="bm26.jpg";
        arJeruk[26]="bm27.jpg";
        arJeruk[27]="bm28.jpg";
        arJeruk[28]="bm29.jpg";
        arJeruk[29]="bm30.jpg";
        arJeruk[30]="bm31.jpg";
        arJeruk[31]="bm32.jpg";
        arJeruk[32]="bm33.jpg";
        arJeruk[33]="bm34.jpg";
        arJeruk[34]="bm35.jpg";
        arJeruk[35]="m1.jpg";
        arJeruk[36]="m2.jpg";
        arJeruk[37]="m3.jpg";
        arJeruk[38]="m4.jpg";
        arJeruk[39]="m5.jpg";
        arJeruk[40]="m6.jpg";
        arJeruk[41]="m7.jpg";
        arJeruk[42]="m8.jpg";
        arJeruk[43]="m9.jpg";
        arJeruk[44]="m10.jpg";
        arJeruk[45]="m11.jpg";
        arJeruk[46]="m12.jpg";
        arJeruk[47]="m13.jpg";
        arJeruk[48]="m14.jpg";
        arJeruk[49]="m15.jpg";
        arJeruk[50]="m16.jpg";
        arJeruk[51]="m17.jpg";
        arJeruk[52]="m18.jpg";
        arJeruk[53]="m19.jpg";
        arJeruk[54]="m20.jpg";
        arJeruk[55]="m21.jpg";
        arJeruk[56]="m22.jpg";
        arJeruk[57]="m23.jpg";
        arJeruk[58]="m24.jpg";
        arJeruk[59]="m25.jpg";
        arJeruk[60]="m26.jpg";
        arJeruk[61]="m27.jpg";
        arJeruk[62]="m28.jpg";
        arJeruk[63]="m29.jpg";
        arJeruk[64]="m30.jpg";
        arJeruk[65]="m31.jpg";
        arJeruk[66]="m32.jpg";
        arJeruk[67]="m33.jpg";
        arJeruk[68]="m34.jpg";
        arJeruk[69]="m35.jpg";
        arJeruk[70]="kl1.jpg";
        arJeruk[71]="kl2.jpg";
        arJeruk[72]="kl3.jpg";
        arJeruk[73]="kl4.jpg";
        arJeruk[74]="kl5.jpg";
        arJeruk[75]="kl6.jpg";
        arJeruk[76]="kl7.jpg";
        arJeruk[77]="kl8.jpg";
        arJeruk[78]="kl9.jpg";
        arJeruk[79]="kl10.jpg";
        arJeruk[80]="kl11.jpg";
        arJeruk[81]="kl12.jpg";
        arJeruk[82]="kl13.jpg";
        arJeruk[83]="kl14.jpg";
        arJeruk[84]="kl15.jpg";
        arJeruk[85]="kl16.jpg";
        arJeruk[86]="kl17.jpg";
        arJeruk[87]="kl18.jpg";
        arJeruk[88]="kl19.jpg";
        arJeruk[89]="kl20.jpg";
        arJeruk[90]="kl21.jpg";
        arJeruk[91]="kl22.jpg";
        arJeruk[92]="kl23.jpg";
        arJeruk[93]="kl24.jpg";
        arJeruk[94]="kl25.jpg";
        arJeruk[95]="kl26.jpg";
        arJeruk[96]="kl27.jpg";
        arJeruk[97]="kl28.jpg";
        arJeruk[98]="kl29.jpg";
        arJeruk[99]="kl30.jpg";
        arJeruk[100]="kl31.jpg";
        arJeruk[101]="kl32.jpg";
        arJeruk[102]="kl33.jpg";
        arJeruk[103]="kl34.jpg";
        arJeruk[104]="kl35.jpg";





        arNama[0]="Muda";
        arNama[1]="Muda";
        arNama[2]="Muda";
        arNama[3]="Muda";
        arNama[4]="Muda";
        arNama[5]="Muda";
        arNama[6]="Muda";
        arNama[7]="Muda";
        arNama[8]="Muda";
        arNama[9]="Muda";
        arNama[10]="Muda";
        arNama[11]="Muda";
        arNama[12]="Muda";
        arNama[13]="Muda";
        arNama[14]="Muda";
        arNama[15]="Muda";
        arNama[16]="Muda";
        arNama[17]="Muda";
        arNama[18]="Muda";
        arNama[19]="Muda";
        arNama[20]="Muda";
        arNama[21]="Muda";
        arNama[22]="Muda";
        arNama[23]="Muda";
        arNama[24]="Muda";
        arNama[25]="Muda";
        arNama[26]="Muda";
        arNama[27]="Muda";
        arNama[28]="Muda";
        arNama[29]="Muda";
        arNama[30]="Muda";
        arNama[31]="Muda";
        arNama[32]="Muda";
        arNama[33]="Muda";
        arNama[34]="Muda";
        arNama[35]="Masak";
        arNama[36]="Masak";
        arNama[37]="Masak";
        arNama[38]="Masak";
        arNama[39]="Masak";
        arNama[40]="Masak";
        arNama[41]="Masak";
        arNama[42]="Masak";
        arNama[43]="Masak";
        arNama[44]="Masak";
        arNama[45]="Masak";
        arNama[46]="Masak";
        arNama[47]="Masak";
        arNama[48]="Masak";
        arNama[49]="Masak";
        arNama[50]="Masak";
        arNama[51]="Masak";
        arNama[52]="Masak";
        arNama[53]="Masak";
        arNama[54]="Masak";
        arNama[55]="Masak";
        arNama[56]="Masak";
        arNama[57]="Masak";
        arNama[58]="Masak";
        arNama[59]="Masak";
        arNama[60]="Masak";
        arNama[61]="Masak";
        arNama[62]="Masak";
        arNama[63]="Masak";
        arNama[64]="Masak";
        arNama[65]="Masak";
        arNama[66]="Masak";
        arNama[67]="Masak";
        arNama[68]="Masak";
        arNama[69]="Masak";
        arNama[70]="Layu";
        arNama[71]="Layu";
        arNama[72]="Layu";
        arNama[73]="Layu";
        arNama[74]="Layu";
        arNama[75]="Layu";
        arNama[76]="Layu";
        arNama[77]="Layu";
        arNama[78]="Layu";
        arNama[79]="Layu";
        arNama[80]="Layu";
        arNama[81]="Layu";
        arNama[82]="Layu";
        arNama[83]="Layu";
        arNama[84]="Layu";
        arNama[85]="Layu";
        arNama[86]="Layu";
        arNama[87]="Layu";
        arNama[88]="Layu";
        arNama[89]="Layu";
        arNama[90]="Layu";
        arNama[91]="Layu";
        arNama[92]="Layu";
        arNama[93]="Layu";
        arNama[94]="Layu";
        arNama[95]="Layu";
        arNama[96]="Layu";
        arNama[97]="Layu";
        arNama[98]="Layu";
        arNama[99]="Layu";
        arNama[100]="Layu";
        arNama[101]="Layu";
        arNama[102]="Layu";
        arNama[103]="Layu";
        arNama[104]="Layu";



        AssetManager assetManager = getAssets();

        for(int k=0;k<jd;k++) {
            citra1[k] = new Mat();
            InputStream istr1 = assetManager.open(arJeruk[k]);//a.jpeg
            Bitmap bitmap1 = BitmapFactory.decodeStream(istr1);
            Utils.bitmapToMat(bitmap1, citra1[k] );
            Imgproc.cvtColor(citra1[k] , citra1[k] , Imgproc.COLOR_RGB2GRAY);
           // Log.d("citra1", "=" + citra1 + "");


            citra1[k] .convertTo(citra1[k] , 0);
            desc1[k] = new Mat();
            key1[k] = new MatOfKeyPoint();
            detector.detect(citra1[k] , key1[k]);
            descriptor.compute(citra1[k] , key1[k], desc1[k]);//bobot  citra
           Log.d("nilai bobot", "@=" + desc1 [k]+ "" );
        }
        //============================================1
    }
    public Deteksi() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_deteksi);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
        }
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.deteksi);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Library Open CV tidak ditemukan");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, Deteksi.this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV dapat digunakan!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        w = width;
        h = height;
    }

    @Override
    public void onCameraViewStopped() {

    }

    public Mat recognize(Mat aInputFrame) {//aInputFrame=objek dari Kamera realtime
        double nmin = 10000000;
        int indexKe=0;

        Imgproc.cvtColor(aInputFrame, aInputFrame, Imgproc.COLOR_RGB2GRAY);
        descriptors2 = new Mat();
        keypoints2 = new MatOfKeyPoint();
        detector.detect(aInputFrame, keypoints2);
        descriptor.compute(aInputFrame, keypoints2, descriptors2);
        Log.d("nilai", "=" + descriptors2 + "" );


        MatOfDMatch matches = null;
        //menghitung jumlah yang sama
        Double max_dist = 0.0;
        Double min_dist = 100.0;
        int JM = 0;

        matches = new MatOfDMatch();
        for(int k=0;k<jd;k++){
            if (citra1[k].type() == aInputFrame.type()) {
                try {
                    matcher.match(desc1[k], descriptors2, matches);

                    List<DMatch> matchesList1 = matches.toList();

                    max_dist = 0.0;//u hitung jumlah yg sama
                    min_dist = 255.0;//jarak

                    JM = matchesList1.size();
                    for (int i = 0; i < JM; i++) {
                        Double dist = (double) matchesList1.get(i).distance;
                        if (dist < min_dist)
                            min_dist = dist;
                        if (dist > max_dist)
                            max_dist = dist;
                    }
                    //Log.d("MAXMIN15", "@=" + max_dist + "@" + min_dist + "@" + (1.2 * min_dist));
                    if (min_dist < nmin) {//elitisme crai jarak terdekat
                        indexKe = k;
                        nmin = min_dist;
                        Log.d("nmin", "=" + nmin + "");
                    }

                } catch (Exception ee) {
                }
            } else {
                return aInputFrame;
            }

        }//loop


        //=================================================================================3view
        matches = new MatOfDMatch();
        if (citra1[indexKe].type() == aInputFrame.type()) {
            try {
                matcher.match(desc1[indexKe], descriptors2, matches);
            } catch (Exception ee) {
            }

        } else {
            return aInputFrame;
        }
        Log.d("matches", "=" + matches + "");

        List<DMatch> matchesListOut = matches.toList();
        JM = matchesListOut.size();
        List<DMatch>  listBest=null;
        Mat imgBest=new Mat();
        MatOfKeyPoint keyBest=null;

        listBest=matchesListOut;
        imgBest=citra1[indexKe];
        keyBest=key1[indexKe];
        /*Log.d("imgBest", "=" + imgBest + "");
        Log.d("keyBest", "=" + keyBest + "");*/
        Log.d("nilai minimum", "=" + nmin + "");
        nmin1 = String.valueOf(nmin);
        keterangan=arNama[indexKe];
        kategori=arJeruk[indexKe];


        String kat="Kurang Layak";
        if(indexKe<34){kat="Kurang Layak";}
        else         if(indexKe<69){kat="Layak";}
        else         if(indexKe<104){kat="Kurang Layak";}


        analisa=kat;

        //=================================================================================================LAST
        int mm=0;
        LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
        for (int i = 0; i < JM; i++) {
            if (listBest.get(i).distance <= (1.2 * min_dist)) {
                good_matches.addLast(listBest.get(i));
                mm++;
            }
        }
        gab=gab+String.valueOf(mm)+"#";
       /* Log.d("gab", "=" + gab + "");*/

        MatOfDMatch goodMatches = new MatOfDMatch();
        goodMatches.fromList(good_matches);
        Mat outputImg = new Mat();


        MatOfByte drawnMatches = new MatOfByte();
        if (aInputFrame.empty() || aInputFrame.cols() < 1 || aInputFrame.rows() < 1) {
            return aInputFrame;
        }
        Features2d.drawMatches(imgBest, keyBest, aInputFrame, keypoints2, goodMatches, outputImg, GREEN, RED, drawnMatches, Features2d.NOT_DRAW_SINGLE_POINTS);
        Imgproc.resize(outputImg, outputImg, aInputFrame.size());


        return outputImg;
    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return recognize(inputFrame.rgba());
    }


    /*public void hasil(){
        new AlertDialog.Builder(this)
                .setTitle("Hasil Analisa")
                .setMessage("Hasil: "+keterangan +" !")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                        Intent i=new Intent(Deteksi.this,Hasil.class);
                        i.putExtra("keterangan",keterangan  );
                        i.putExtra("kategori",kategori);
                        i.putExtra("analisa",analisa);
                        startActivity(i);


                        finish();
                    }}).show();
    }*/

}

