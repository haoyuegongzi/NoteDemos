package com.haoyue.notedemos.window;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：Created by Administrator on 2018/4/18.
 * 邮箱：chen126jie@163.com
 * TODO：二阶（三个点：两个数据点，一个控制点。这里简单的理解为折线起始点和终点，以及一个中间点）Bezier曲线绘制折线图
 */

public class BezierChart extends View {
    private Paint linePaint;
    private Paint pointPaint;

    private Path path;
    private PointF start, end, control;

    private int lineColor = 0x11a0e3;
    private float[] pointCoordinates;
    // = new float[]{100, 220, 266, 300, 400, 180, 25, 100, 120, 300, 500, 520}
    private int width, height;

    public BezierChart(Context context) {
        super(context);
        initVar();
    }

    public BezierChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVar();
    }

    public void initVar(){
        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);

        linePaint = new Paint();
        linePaint.setColor(0xffffff);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.FILL);

        pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setStrokeWidth(15);
        pointPaint.setColor(0xffffff);

        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initPoint();
    }

    private void initPoint(){
        start.x = 100;
        start.y = pointCoordinates[0];

        control.x = 100 + width/11;
        control.y = pointCoordinates[1];

        end.x = 100+ (width/11)*2;
        end.y = pointCoordinates[2];
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        /**将坐标原点移到（20， height-20）处*/

        canvas.translate(width/2, height/2);
//        canvas.translate(20, height - 2);
        /**翻转坐标：变成我们常用的 Y轴向上，X轴向右*/
        canvas.scale(1, -1);

        path.moveTo(pointCoordinates[0], 10);
        //{100, 220, 266, 300, 400, 180, 25, 100, 120, 300, 500, 520};
        for (int i = 0; i < pointCoordinates.length; i++) {
            Log.i("TAG", "onDraw: " + pointCoordinates[i] + "  →→→→i ==" + i);

            if ((i+2) <= pointCoordinates.length-1){
                Log.i("log", "onDraw: " + pointCoordinates[i+2] + "  →→→→i ==" + i);

                start.y = pointCoordinates[i];
                start.x = 10 + (width / (pointCoordinates.length-1)) * i;

                control.y = pointCoordinates[i+1];
                control.x = 10 + (width / (pointCoordinates.length-1)) * (i + 1);

                end.y = pointCoordinates[i+2];
                end.x = 10 + (width / (pointCoordinates.length-1)) * (i + 2);

                /**绘制各个点*/
                canvas.drawPoint(start.x, start.y, pointPaint);
                canvas.drawPoint(control.x, control.y, pointPaint);
                canvas.drawPoint(end.x, end.y, pointPaint);

                //参数中(control.x,control.y)是控制点坐标，(end.x,end.y)是终点坐标
                //整条线的起始点是通过Path.moveTo(start.x,start.y)来指定的
//                path.cubicTo(start.x, start.y, control.x, control.y, end.x, end.y);
                path.moveTo(start.x,start.y);
                path.quadTo(control.x, control.y, end.x, end.y);
                canvas.drawPath(path, linePaint);
            }
        }
        Log.i("ITAG", "onDraw: after canvas.drawPath()");
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        //TODO:记得在调用的地方刷新View:postInvalidate();
    }

    public void setPointCoordinates(float[] pointCoordinates) {
        this.pointCoordinates = pointCoordinates;
        //TODO:记得在调用的地方刷新View:postInvalidate();
    }

}
