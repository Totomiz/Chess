package com.zt.tz.chess;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016-05-29 22:15
 * QQ:xxxxxxxx
 */
public class Chess {
    private final int w,h;
    private final int unit=40;//单个格子尺寸
    private final int totalline,totalcolum;//多少行列
    private final Paint paint;
    private static final int radius=17;
    private int cx,cy;
    private int cLine,cColumn;
    private boolean isblack;
    private List<Point> list=new ArrayList<Point>();
    private int top,bottom,left,right;

    public Chess( ChessView chessView){
        this.w=chessView.widthPixels;
        this.h=chessView.heightPixels;
        totalcolum=w/unit;
        totalline=h/unit;
        top=155;
        left=155;
        right=w-155-w%unit;
        bottom=h-h%unit-315;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(3);
        paint.setStrokeWidth(3);
    }
    /**
     * 画棋盘
     * @param canvas
     */
    public void drawCompass(Canvas canvas) {
        canvas.save();
        canvas.clipRect(left, top, right, bottom);
        canvas.drawColor(Color.parseColor("#D5B292"));
        paint.setColor(Color.BLACK);
        for (int i = 0; i < totalline; i++) {
            canvas.drawLine(0,i*unit,w,i*unit,paint);
        }
        for (int i = 0; i < totalcolum; i++) {
            canvas.drawLine(i*unit,0,i * unit, h, paint);
        }
        canvas.restore();

    }

    /**
     * 画棋子
     * @param canvas
     */
    public void drawChess(Canvas canvas) {
        canvas.save();
        canvas.clipRect(left-unit/2, top-unit/2, right+unit/2, bottom+unit/2);
        Point point=null;
        for (int i = 0; i < list.size(); i++) {
            paint.setColor(i%2==1?Color.BLACK:Color.WHITE);
            point=list.get(i);
            canvas.drawCircle(point.x,point.y,radius,paint);
        }
        canvas.restore();
    }

    /**
     * 哪个交叉点
     * 是否应该落子
     * @param eventX
     * @param eventY
     */
    public void luozhi(int eventX, int eventY) {
        if(eventX<left-unit/15||eventX>right+unit/15||eventY<top-unit/15||eventY>bottom+unit/15){
            return;
        }
        cLine=eventY/unit;
        cColumn=eventX/unit;
        int modeX=eventX%unit;
        int modeY=eventY%unit;
        if(modeX>unit/2){
            cLine++;
        }
        if(modeY>unit/2){
            cColumn++;
        }
        cx=cColumn*unit;
        cy=cLine*unit;
        Point point=new Point(cx,cy);
        if(!list.contains(point)){
            list.add(point);
        }else{
            return;
        }

    }
}
