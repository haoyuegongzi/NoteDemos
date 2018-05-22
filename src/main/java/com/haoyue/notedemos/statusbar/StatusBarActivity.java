package com.haoyue.notedemos.statusbar;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.haoyue.auxiliary.AdaptiveImageView;
import com.haoyue.notedemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;

/**
 * @author chen1
 */
public class StatusBarActivity extends AppCompatActivity {

    @BindView(R.id.aivBar)
    TextView aivBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);
        ButterKnife.bind(this);
        //AndroidManifest.xml:android:theme="@style/fullScreen"  android:theme="@style/status_toolbar_same_color"

//        需求一、

        //全屏，不保留状态栏文字的方案一
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //全屏，不保留状态栏文字的方案二
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //
        /**SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN:不是设置五状态栏的全屏，隐藏了标题栏，但是状态栏还在。等同于this.getSupportActionBar().hide();**/

        //全屏，不保留状态栏文字的方案三
//        <style name="fullScreen" parent="Theme.AppCompat.DayNight.NoActionBar">
//            <item name="android:windowFullscreen">true</item>
//        </style>
//        <activity
//            android:theme="@style/fullScreen"
//            android:name=".statusbar.StatusBarActivity"></activity>



        //需求二、全屏保留状态栏文字(状态栏透明)
//        Window window = getWindow();
//        //默认API 最低19
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
//            contentView.getChildAt(0).setFitsSystemWindows(false);
//        }

//        需求四、标题栏与状态栏颜色一致 xml中配置
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        statusBarHeight = statusBarHeight > 0 ? statusBarHeight : 150;

        ///或者：
//        Rect rectangle= new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
//        statusBarHeight = rectangle.top;

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.green));
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = (ViewGroup) findViewById(android.R.id.content);
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            statusBarView.setBackgroundColor(getResources().getColor(R.color.green));
            systemContent.getChildAt(0).setFitsSystemWindows(true);
            systemContent.addView(statusBarView, 0, lp);
        }
    }

    @OnClick(R.id.aivBar)
    public void onViewClicked() {
        startActivity(new Intent(StatusBarActivity.this, TitleBarActivity.class));
    }
}
