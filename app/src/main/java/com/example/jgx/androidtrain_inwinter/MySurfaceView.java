/*package com.example.jgx.androidtrain_inwinter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author: jgx
 * @time: 2016/1/22 10:19
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public final static String TAG = "MySyrfaceView";


    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private Thread thread;
    private boolean flag;


    private float x, y;
    private float speedX, speedY;
    private float radius;
    private float color;


    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        holder = getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setColor(Color.RED);//设置画笔的颜色
        paint.setAntiAlias(true);
    }

    private void myDraw(Canvas canvas) {

        paint.setColor(color.WHITE);
        canvas.drawrect(100, 200, 500, 600, paint);
        canvas.drawCircle(x, y,radius, paint);
    }

    public void initGame() {
        x = 0;
        y = 0;
        speedX = 10;
        speedY = 20;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceChanged");
        flag = true;
        thread = new Thread(this);
        Thread.start;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceChanged");
        flag = false;

    }

    private void mydrawl(Canvas canvas) {


    }

    /*
    *逻辑
     */
    private void logic() {
        x += speedX;
        y += speedY;
        if(x>=getWidth()||x<0){
            speedX=-speedX;
        }
        if(y>=getHeight()||y<0){
            speedY=-speedY;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        return super.onTouchEvent(event);
      // color=Color.argb(0,x*y%255,x%255,y%255);
        color =Color.argb(10,(int)event.getX()%255,(int)event.getY()%255,100);
        radius=new Random().nextInt(10)+50;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();

            canvas = holder.lockCanvas();
            if (null != canvas) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);//解锁

            }
            logic();
            long end = System.currentTimeMillis();
            if (end - start < 50) {
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}*/
