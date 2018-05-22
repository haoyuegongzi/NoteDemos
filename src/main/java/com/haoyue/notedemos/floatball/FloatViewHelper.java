package com.haoyue.notedemos.floatball;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.haoyue.notedemos.R;

/**
 * 悬浮框显示帮助类
 *
 * @author Lavon
 */
public class FloatViewHelper {
    private String TAG = "FloatViewHelper";
    private static FloatViewHelper mFVH;
    private WindowManager windowManager = null;
    private FloatView floatView = null;
    private Context mContext;
    private boolean mIsShow;
    private String phoneNo;

    private FloatViewHelper(Context context) {
        mContext = context;
        initFloatView();
    }

    public static FloatViewHelper getInstance(Context context) {
        if (mFVH == null) {
            synchronized (FloatViewHelper.class){
                if (mFVH == null) {
                    mFVH = new FloatViewHelper(context);
                }
            }
        }
        return mFVH;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    private void initFloatView() {

        floatView = new FloatView(mContext.getApplicationContext(), 0, 0);
        floatView.setOnClickListener(mDefaultClickListener);
        // 这里简单的用自带的icon来做演示
        floatView.setImageResource(R.mipmap.microphone);
        floatView.setFloatingMode(true, false, true, false);
        floatView.setmVBerthLength(500);

        // 获取WindowManager
        windowManager = (WindowManager) mContext.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
    }

    public void show() {
        // 显示悬浮窗口
        WindowManager.LayoutParams windowManagerParams2 = floatView
                .getWindowManagerParams();
        floatView.setVisibility(View.VISIBLE);
        windowManager.addView(floatView, windowManagerParams2);
        mIsShow = true;
    }

    public void hide() {
        if (isShow()) {
            // 隐藏悬浮窗口
            try {
                if (floatView != null) {
                    floatView.setVisibility(View.GONE);
                    windowManager.removeView(floatView);
                    mIsShow = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isShow() {
        return mIsShow;
    }

    public static void setContext(Context context) {

    }

    private View.OnClickListener mDefaultClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//			if (serviceDialog!=null) {
//				serviceDialog.dismiss();
//			}
//			serviceDialog = new ServiceDialog(mContext);
//			serviceDialog.toggle();
            Toast.makeText(mContext, "新的悬浮窗", Toast.LENGTH_SHORT).show();
        }
    };

}
