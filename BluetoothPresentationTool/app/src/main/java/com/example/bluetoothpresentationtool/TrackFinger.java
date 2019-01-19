package com.example.bluetoothpresentationtool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;


public class TrackFinger {
    private Paint paint;
    private Bitmap bmp;
    private Canvas canvas;
    private float radius;

    public TrackFinger(int width, int height) {
        this.paint = new Paint();
        this.bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(bmp);
        this.radius = 60;
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.LTGRAY);
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void DrawTrack(ImageView imageView,float cx, float cy) {
        this.canvas.drawCircle(cx, cy, radius, paint);
        imageView.setImageBitmap(bmp);
    }
    public void DrawTrack1(ImageView imageView,float cx, float cy) {
        this.radius = 30;
        this.canvas.drawCircle(cx, cy, radius, paint);
        imageView.setImageBitmap(bmp);
    }

    public void Appear(){
        this.paint.setColor(Color.LTGRAY);
    }

    public void Gone(){
        this.paint.setColor(Color.TRANSPARENT);
    }
}
