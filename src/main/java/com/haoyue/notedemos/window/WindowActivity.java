package com.haoyue.notedemos.window;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.ButterKnife;

public class WindowActivity extends BaseActivity {
    private int REQUEST_PERMISSION_SYSTEM_ALERT_WINDOW_CODE = 1;
    LinearLayout frameLayout;
    BezierChart bezierChart;
    private float[] pointCoordinates = new float[]{100, 220, 266, 300, 400, 180, 25, 100, 120, 300, 500, 520};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        ButterKnife.bind(this);
//        jugmentSDK();

        Button btAddView = findViewById(R.id.btAddView);
        frameLayout = findViewById(R.id.frameLayout);
        bezierChart = new BezierChart(this);
        bezierChart.setPointCoordinates(pointCoordinates);
        btAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.addView(bezierChart);
                bezierChart.setLineColor(0x11a0e3);
                bezierChart.postInvalidate();
            }
        });

    }
















    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //用户许可权限
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            performWindowManager();
        } else {//用户否决权限
            Toast.makeText(WindowActivity.this, "你没有操作权限", Toast.LENGTH_SHORT).show();
        }
    }

    private void performWindowManager() {
        Button floatingButton = new Button(this);
        floatingButton.setAllCaps(false);
        floatingButton.setText("测试Windows");

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        // flag 设置 Window 属性
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // type 设置 Window 类别（层级）
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        layoutParams.gravity = Gravity.CENTER;
        WindowManager windowManager = getWindowManager();
        windowManager.addView(floatingButton, layoutParams);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }

    private void jugmentSDK() {
        //SYSTEM_ALERT_WINDOW
        if (Build.VERSION.SDK_INT >= 23) {
            int windowPer = ContextCompat.checkSelfPermission(WindowActivity.this, Manifest.permission.CALL_PHONE);
            Boolean flag = windowPer == PackageManager.PERMISSION_GRANTED;
            if (flag) {
                //拥有权限
                performWindowManager();
            } else {
                //没有权限，获取权限
                Log.i("TAG", "onCreate: ————>>>没有权限，获取权限");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_SYSTEM_ALERT_WINDOW_CODE);
            }
        }
    }
}
