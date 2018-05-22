package com.haoyue.notedemos.floatball;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class FloatBallActivity extends BaseActivity {

    @BindView(R.id.btStart)
    Button mBtStart;
    @BindView(R.id.btStop)
    Button mBtStop;

    boolean isHasAlertPermission;
//    int windowLimitCode = 0x00bb;
    boolean limitsState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_ball);
        ButterKnife.bind(this);
    }

    private void checkPermissions(){
        Log.d("TabLayoutActivity->","SDK_INT->"+ Build.VERSION.SDK_INT);
        if(Build.VERSION.SDK_INT< 23){
            return ;
        }
        /**
            检查是否有悬浮窗的权限
         */
        if (!Settings.canDrawOverlays(this)) {
            isHasAlertPermission = false;
            String action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION;
            Intent intent = new Intent(action, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 101);

        }else {
            startService();
        }
    }

    @OnClick({R.id.btStart, R.id.btStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btStart:
                checkPermissions();
                break;
            case R.id.btStop:
//                FloatViewHelper.getInstance(this).hide();
                if(limitsState){
                    Intent mFloatServive = new Intent(FloatBallActivity.this, FloatServive.class);
                    stopService(mFloatServive);
                }
                break;
            default:
                break;
        }
    }

    private void startService(){
//        FloatViewHelper.getInstance(this).show();
        Toast.makeText(FloatBallActivity.this, "拥有SYSTEM_ALERT_WINDOW权限", Toast.LENGTH_SHORT).show();
        Intent mStartFloatServive = new Intent(FloatBallActivity.this, FloatServive.class);
        startService(mStartFloatServive);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 101:
                if(Build.VERSION.SDK_INT >22){
                    if (!Settings.canDrawOverlays(this)){
                        isHasAlertPermission = false;
                    }
                    else {
                        isHasAlertPermission =true;
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
