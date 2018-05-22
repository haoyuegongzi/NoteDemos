package com.haoyue.notedemos.bitmap;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by chen1 on 2017/11/8.
 * TO DO:
 */

public class MyListView extends ListView {
    int width = 0;
    int height = 0;

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(width, height);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(width, heightMeasureSpec);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSpec, height);
        }
    }
}
