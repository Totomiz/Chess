package com.zt.tz.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by asus on 2016-05-29 21:51
 * QQ:xxxxxxxx
 */
public class ChessView extends View {

    public int widthPixels;
    public int heightPixels;
    private Chess chess;

    //    private
    public ChessView(Context context) {
        this(context,null);
    }

    public ChessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕高宽
        widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        chess = new Chess(this);
        Log.i("testthresd","chess view"+Thread.currentThread().getName());
    }

    public ChessView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthPixels, heightPixels);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制棋盘
        chess.drawCompass(canvas);
        //绘制旗子
        chess.drawChess(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理actionup,手指放开后落子
        if(event.getAction()==MotionEvent.ACTION_UP){
            //判断点的位置在哪个交叉点,是否该落子
            int eventX= (int) event.getX();
            int eventY= (int) event.getY();
            //判断是否落子
            chess.luozhi(eventX,eventY);
            //最后刷新界面
            invalidate();
        }
        return true;
    }
}
