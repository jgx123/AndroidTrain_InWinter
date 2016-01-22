package com.example.jgx.androidtrain_inwinter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Myview
 *
 * @author: jgx
 * @time: 2016/1/22 8:22
 */
public class Myview  extends View {
    private Paint paint;
    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /*
     *
     *  初始化
     */
   private void init(){
          paint = new Paint();
          paint.setColor(Color.RED);//设置画笔的颜色
          paint.setAntiAlias(true);

      //    x=getWidth();
         // y=getHeight();
   }
    public Myview(Context context) {
        super(context);
        init();
    }
    public void draw(Canvas canvas){
        super.draw(canvas);
       // drawTest(canvas);
        drawPath(canvas);
        drawBitmap(canvas);
    }


    private void drawTest(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//设置画笔的模式
        paint.setStrokeWidth(15);//设置画笔的宽度
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
        //画圆
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200,100,100,paint);
        //画矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(100,500,300,600,paint);
        //画三角形
        //canvas.drawRoundRect(100,700,200,800,10,10,paint);

    }
    private void drawPath(Canvas canvas){
        Path path1 = new Path();
        path1.moveTo(100, 100);//初始点
        path1.lineTo(100, 300);
        path1.lineTo(200, 250);
        paint.setColor(Color.YELLOW);
        canvas.drawPath(path1, paint);
        Path Path1=new Path();
       // path1.addCircle(500,500,200,Path.Direction.CW);
        path1.addCircle(500,500,180,Path.Direction.CCW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawPath(path1,paint);
    }
private void drawBitmap(Canvas canvas){
    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    canvas.drawBitmap(bitmap, 0, 0, paint);
    canvas.translate(getWidth() / 2, getHeight() / 2);//设置坐标系原点的位置


    canvas.save();
    canvas.rotate(90);
    canvas.restore();
    canvas.drawBitmap(bitmap, 0, 0, paint);

    canvas.drawCircle(200,200,50,paint);
}
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int) event.getX();
        y=(int) event.getY();
        invalidate();
        return super.onTouchEvent(event);
    }*/
}
