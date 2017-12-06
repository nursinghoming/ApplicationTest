package com.example.raytine.applicationtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.core.Mat;
import org.opencv.android.Utils;
import org.opencv.core.Scalar;

import static org.opencv.core.CvType.CV_8U;

public class MainActivity extends AppCompatActivity {
    private Button btnProc;
    private ImageView imageView;
    private Bitmap bmpStand;
    private Bitmap bmpTest;
    private Button section;
    private Button result;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (Button) findViewById(R.id.btn_result);
        imageView = (ImageView) findViewById(R.id.image_view);
        bmpStand = BitmapFactory.decodeResource(getResources(), R.drawable.stand);
        bmpTest = BitmapFactory.decodeResource(getResources(), R.drawable.mytest);
        imageView.setImageBitmap(bmpTest);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Wstand = bmpStand.getWidth();
                int Hstand = bmpStand.getHeight();
                Mat mat1 = new Mat();
                Utils.bitmapToMat(bmpStand, mat1);
                int a=mat1.rows();
                int[] Spixels = new int[Wstand * Hstand];
                bmpStand.getPixels(Spixels, 0, Wstand, 0, 0, Wstand, Hstand);
                int Wtest = bmpTest.getWidth();
                int Htest = bmpTest.getHeight();
                int[] Tpixels = new int[Wtest * Htest];
                bmpTest.getPixels(Tpixels, 0, Wtest, 0, 0, Wtest, Htest);

                //int [] mOutArray = new int[1500 * 1500];
                //Bitmap resultImg = Bitmap.createBitmap(1500, 1500, Bitmap.Config.ALPHA_8);
                int[] resultInt = ResultTest(Spixels, Tpixels, Wstand, Hstand, Wtest, Htest);
                Bitmap resultImg = Bitmap.createBitmap(Wstand, Hstand, Bitmap.Config.RGB_565);//RGB_565
                //Bitmap resultImg = Bitmap.createBitmap(1500, 1500, Bitmap.Config.RGB_565);
                resultImg.setPixels(resultInt, 0, Wstand, 0, 0, Wstand, Hstand);
                //resultImg.setPixels(resultInt, 0, 1500, 0, 0, 1500, 1500);


                imageView.setImageBitmap(resultImg);
            }
        });
    }

    static {
        System.loadLibrary("native-lib");
    }

//    public static native int[] grayProc(int[] pixels, int w, int h);
//    public static native int[] Tsection(int[] pixels, int w, int h);

    public static native int[] ResultTest(int[] Spixels, int[] Tpixels, int Wstand, int Hstand, int Wtest, int Htest);
    //public static native boolean ResultTest(int[] Spixels, int[] Tpixels, int Wstand, int Hstand, int Wtest, int Htest,int [] mOutArray);

    @Override
    public void onResume() {
        super.onResume();
    }


}

