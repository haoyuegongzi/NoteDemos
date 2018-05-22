package com.haoyue.notedemos.floatball;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.notedemos.R;

/**
 * Created by chen1 on 2017/11/20.
 * TO DO: 服务启动悬浮球。
 */

public class FloatServive extends Service {
    WindowManager.LayoutParams mLayoutParams;
    WindowManager mManager;
    LinearLayout mLayout;
//    TextView tvFloatBall;

    @Override
    public void onCreate() {
        super.onCreate();
        initFloatBall();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initFloatBall(){
        mLayoutParams = new WindowManager.LayoutParams();
        mManager = (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);
        mLayout = (LinearLayout) LayoutInflater.from(getApplication()).inflate(R.layout.flaot_ball, null);
        final TextView tvFloatBall = (TextView)mLayout.findViewById(R.id.tvFloatBall);

        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mLayoutParams.format = PixelFormat.RGBA_8888;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 100;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        mManager.addView(mLayout, mLayoutParams);
        mLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        tvFloatBall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        mLayoutParams.x = (int) (event.getRawX() - tvFloatBall.getMeasuredWidth() / 2);
                        mLayoutParams.y = (int) (event.getRawY() - tvFloatBall.getMeasuredHeight()/2 - 25);
                        mManager.updateViewLayout(mLayout, mLayoutParams);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        tvFloatBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatServive.this, "点击了悬浮球", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mLayout != null){
            mManager.removeView(mLayout);
        }
    }
}
