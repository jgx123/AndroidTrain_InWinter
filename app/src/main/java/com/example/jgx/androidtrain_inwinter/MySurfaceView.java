package com.example.jgx.androidtrain_inwinter;

/**
 * MySurfaceView
 *
 * @author: jgx
 * @time: 2016/1/22 10:19
 */
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

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public final static String TAG = "MySurfaceView";


    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private Thread thread;
    private boolean flag;


    private float x, y;
    private float speedX, speedY;
    private float radius;
    private float color;

    private Vector loca;
    private Vector speed;
    private Vector acc;

    private float rectx,recty;
    private float rectWidth,rectHeight;


    public MySurfaceView(Context context) {
        super(context);
        init();
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


    public void initGame() {
        x = 0;
        y = 0;
        speedX = 10;
        speedY = 20;
        radius=50;
        color=Color.RED;

        loca= new Vector(100,100);
        speed=new Vector(10,20);
        acc=new Vector(1.0f,2.0f);

        rectx=getWidth()/2-100;
        recty=getHeight()/2-80;
        rectWidth=200;
        rectHeight=100;

       // rect1x=0;
        //rect2x=0;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceChanged");
        flag = true;
        thread = new Thread(String.valueOf(this));
        //Thread.start();
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
    private void myDraw(Canvas canvas) {

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor((int) color);
    //    canvas.drawCircle(x, y, radius, paint);
       // canvas.drawRect(rect1x,rect1y,);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(loca.x, loca.y, radius, paint);

        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
       // rect1x=x;
        //rect1y=y;


       //isColl=();
    }
    /*
    *逻辑
     */
    private void logic() {
        //  简单移动
        x += speedX;
        y += speedY;
        if(x>=getWidth()||x<0){
            speedX=-speedX;
        }
        if(y>=getHeight()||y<0){
            speedY=-speedY;
        }
        //向量移动
        speed.limit(50);
        speed.add(acc);
        loca.add(speed);

        if(loca.x >=getWidth()||loca.x<0){
            speed.x=-speed.x;
            acc.x=-acc.x;
        }
        if(loca.y >=getHeight()||loca.y<0){
            speed.y=-speed.y;
            acc.y=-acc.y;
        }/*
        boolean isColl=rectAndrect(rect1x,rect1y,);
        //碰撞检测
        if(isColl){
            speedX
        }else{

        }*/

    }
    private boolean rectAndrect(float rect1x,float rect1y,float rect1W,float rect1H,float rect2x,float rect2y,float rect2W,float rect2H){

        if(rect1x+rect1W<rect2x){
            return false;
        }else if(rect1x>rect2x+rect2W){
            return false;
        }else if(rect1y+rect1H<rect2y) {
            return false;
        }else if(rect1y>rect2y+rect2H){
            return false;
        }

        return true;
    }
    /*
     *圆与圆碰撞
     */
    private boolean circleAndCircle(float circle1X,float circle1Y,float circle1R,
                                    float circle2X,float circle2Y,float circle2R){
        float disSq=(float)(Math.pow(circle2X-circle1X,2)+Math.pow(circle2Y-circle1Y,2));
        if(disSq>Math.pow(circle1R-circle2R,2))
        {
            return false;
        }
        return true;
    }
    /*
     *圆与正方形碰撞
     *
     */
    private  boolean circleAndrect(float circleX,float circleY,float circleR,float rectx,float recty,float rectW,float rectH){
        if(circleX+circleR<rectx){
            return false;
        }else if(circleX-circleR>rectx+recty){
            return false;
        }else if(circleR+circleY<recty){
            return false;
        }else if(circleY-circleR>recty+rectH){
            return false;
        }else if(Math.pow(rectx+rectW - circleX,2)+Math.pow(recty - circleY,2)>circleR*circleR){
            return false;
        }else if(Math.pow(rectx - circleX,2)+Math.pow(recty +rectH- circleY,2)>circleR*circleR){
            return false;
        }
        else if(Math.pow(rectx +rectW- circleX,2)+Math.pow(recty+rectH - circleY,2)>circleR*circleR){
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        int[] colors = new int []{Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
      // color=Color.argb(0,x*y%255,x%255,y%255);
        color = colors[new Random().nextInt(colors.length)];
        radius=new Random().nextInt(10)+50;

        // 引力
        Vector touch = new Vector(x,y);
        Vector.sub(touch, loca);
        acc.normalize();
        acc.mult(1);

        return super.onTouchEvent(event);
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
}
