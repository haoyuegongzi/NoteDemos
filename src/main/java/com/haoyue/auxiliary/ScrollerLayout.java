package com.haoyue.auxiliary;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by chen1 on 2017/10/17.
 * TO DO:
 */

public class ScrollerLayout extends ViewGroup {
    public static final String TAG = ScrollerLayout.class.getSimpleName();

    private Scroller mScroller;
    private int mTouchSlop;

    private float mXDown;
    private float mXMove;
    private float mXLastMove;
    private int leftBorder;
    private int rightBorder;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                childAt.layout(i * childAt.getMeasuredWidth(), 0, (i + 1) * childAt.getMeasuredWidth(), childAt.getMeasuredHeight());

                childAt.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                Log.e(TAG, "consume ACTION_DOWN");
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.e(TAG, "consume ACTION_MOVE");
                                break;
                            default:
                                break;
                        }
                        //onTouchEvent方法将不会被调用,OnTouchListener消耗掉了这个事件
                        return true;
                    }
                });
            }

            // 初始化左右边界值
            //相对于父视图的左边缘 0
            leftBorder = getChildAt(0).getLeft();
            //相对于父视图的右边缘 3240 = 1080*3
            rightBorder = getChildAt(childCount - 1).getRight();

            Log.e(TAG, "leftBorder : " + leftBorder);
            Log.e(TAG, "rightBorder : " + rightBorder);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;

                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();

                int scrolledX = (int) (mXLastMove - mXMove);

                Log.e(TAG, "getScrollX() : " + getScrollX());

                //为了防止用户拖出边界这里还专门做了边界保护，当拖出边界时就调用scrollTo()方法来回到边界位置。
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    //getWidth()是获取视图的宽度
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }

                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();

                Log.e(TAG, "ACTION_UP getScrollX() : " + getScrollX());
                Log.e(TAG, "ACTION_UP getScrollX() : " + getScrollX());

                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);

                //如果dx>0,则整个视图往左移
                //如果dx<0,则整个视图往右移

                //对getScrollX()的理解：将整个父视图的左上角定为(0,0)，那么子view.getScrollX会获取到屏幕左边缘减去父视图的左边缘为0的距离

                //targetIndex * getWidth() - getScrollX(); 得到的一个偏差值，这个偏差值是正数还是负数已经决定了view的左移右移方向
                //同时偏差值的计算中，始终是 n*1080 - getScrollX()，而getScrollX()就是父视图的左边缘隐藏的距离。

                invalidate();
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

}
