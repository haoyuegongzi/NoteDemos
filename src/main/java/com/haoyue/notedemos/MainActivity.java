package com.haoyue.notedemos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.asynctask.LoadImageActivity;
import com.haoyue.notedemos.bitmap.BitMapActivity;
import com.haoyue.notedemos.crash.CrashHandlerActivity;
import com.haoyue.notedemos.dialogfragment.DialogFragmentActivity;
import com.haoyue.notedemos.drawable.DrawableActivity;
import com.haoyue.notedemos.drawable.ShapeActivity;
import com.haoyue.notedemos.drawerlayout.AndroidNativeActivity;
import com.haoyue.notedemos.drawerlayout.DrawerLayoutActivity;
import com.haoyue.notedemos.eventbus.EventBusActivity;
import com.haoyue.notedemos.floatball.FloatBallActivity;
import com.haoyue.notedemos.flowlayout.FlowLayoutActivity;
import com.haoyue.notedemos.input.InputDialogActivity;
import com.haoyue.notedemos.dialog.DialogActivity;
import com.haoyue.notedemos.loadgif.LoadGifActivity;
import com.haoyue.notedemos.lrecyclerview.view.LRecyclerViewActivity;
import com.haoyue.notedemos.materialdesign.MaterialDesignActivity;
import com.haoyue.notedemos.nestscrollview.NestedScrollViewActivity;
import com.haoyue.notedemos.recycler.RecyclerViewHeaderAndFooterActivity;
import com.haoyue.notedemos.retrofit.mvp.view.MvpAndRxSerieActivity;
import com.haoyue.notedemos.retrofit_learn.RetrofitActivity;
import com.haoyue.notedemos.screenshots.ShotsViewActivity;
import com.haoyue.notedemos.sql.GreenDaoActivity;
import com.haoyue.notedemos.statusbar.StatusBarActivity;
import com.haoyue.notedemos.tablayout.TabLayoutActivity;
import com.haoyue.notedemos.toutiao_ui.TouTiaoActivity;
import com.haoyue.notedemos.webview.WebViewActivity;
import com.haoyue.notedemos.window.WindowActivity;
import com.haoyue.notedemos.zhihu.ZhiHuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tvBitMap)
    TextView mTvBitMap;
    @BindView(R.id.tvDrawable)
    TextView mTvDrawable;
    @BindView(R.id.tvShape)
    TextView mTvShape;
    @BindView(R.id.tvWindow)
    TextView mTvWindow;
    @BindView(R.id.tvLoadImage)
    TextView mTvLoadImage;
    @BindView(R.id.tvFloatBall)
    TextView mTvFloatBall;
    @BindView(R.id.tvInputDialog)
    TextView mTvInputDialog;
    @BindView(R.id.tvGreenDao)
    TextView mTvGreenDao;
    @BindView(R.id.tvLoadGif)
    TextView mTvLoadGif;
    @BindView(R.id.tvShotsView)
    TextView mTvShotsView;
    @BindView(R.id.tvWebView)
    TextView mTvWebView;
    @BindView(R.id.tvRetrofit)
    TextView mTvRetrofit;
    @BindView(R.id.tvTouTiaoUI)
    TextView mTvTouTiaoUI;
    @BindView(R.id.tvDrawerLayout)
    TextView mTvDrawerLayout;
    @BindView(R.id.tvAndroidNative)
    TextView mTvAndroidNative;
    @BindView(R.id.nestedScrollView)
    TextView mNestedScrollView;
    @BindView(R.id.tvTabLayout)
    TextView mTvTabLayout;
    @BindView(R.id.tvInterview)
    TextView mTvInterview;
    @BindView(R.id.tvCrashHandler)
    TextView mTvCrashHandler;
    @BindView(R.id.tvMvpAndRxSerie)
    TextView mTvMvpAndRxSerie;
    @BindView(R.id.tvStatusBar)
    TextView mTvStatusBar;
    @BindView(R.id.tvEventBus)
    TextView mTvEventBus;
    @BindView(R.id.tvZhiHu)
    TextView mTvZhiHu;
    @BindView(R.id.tvMaterialDesign)
    TextView mTvMaterialDesign;
    @BindView(R.id.tvRefresh)
    TextView mTvRefresh;
    @BindView(R.id.tvDialogFragment)
    TextView mTvDialogFragment;
    @BindView(R.id.tvFlowLayout)
    TextView mTvFlowLayout;
    @BindView(R.id.tvRecyclerViewHeaderAndFooter)
    TextView mTvRecyclerViewHeaderAndFooter;
    @BindView(R.id.tvViewpagerGrid)
    TextView mTvViewpagerGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i(TAG, "MainActivity onCreate: ");
        checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 0xcf);
    }

    @OnClick({R.id.tvBitMap, R.id.tvDrawable, R.id.tvShape, R.id.tvWindow, R.id.tvLoadImage,
            R.id.tvFloatBall, R.id.tvInputDialog, R.id.tvGreenDao, R.id.tvLoadGif, R.id.tvShotsView,
            R.id.tvWebView, R.id.tvRetrofit, R.id.tvTouTiaoUI, R.id.tvDrawerLayout, R.id.tvAndroidNative,
            R.id.nestedScrollView, R.id.tvTabLayout, R.id.tvInterview, R.id.tvCrashHandler, R.id.tvMvpAndRxSerie,
            R.id.tvStatusBar, R.id.tvEventBus, R.id.tvZhiHu, R.id.tvMaterialDesign, R.id.tvRefresh, R.id.tvDialogFragment,
            R.id.tvFlowLayout, R.id.tvRecyclerViewHeaderAndFooter, R.id.tvViewpagerGrid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBitMap:
                startActivity(new Intent(MainActivity.this, BitMapActivity.class));
                break;
            case R.id.tvDrawable:
                startActivity(new Intent(MainActivity.this, DrawableActivity.class));
                break;
            case R.id.tvShape:
                startActivity(new Intent(MainActivity.this, ShapeActivity.class));
                break;
            case R.id.tvWindow:
                startActivity(new Intent(MainActivity.this, WindowActivity.class));
                break;
            case R.id.tvLoadImage:
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class));
                break;
            case R.id.tvFloatBall:
                startActivity(new Intent(MainActivity.this, FloatBallActivity.class));
                break;
            case R.id.tvInputDialog:
                startActivity(new Intent(MainActivity.this, InputDialogActivity.class));
                break;
            case R.id.tvGreenDao:
                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                break;
            case R.id.tvLoadGif:
                startActivity(new Intent(MainActivity.this, LoadGifActivity.class));
                break;
            case R.id.tvShotsView:
                startActivity(new Intent(MainActivity.this, ShotsViewActivity.class));
                break;
            case R.id.tvWebView:
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                break;
            case R.id.tvRetrofit:
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                break;
            case R.id.tvTouTiaoUI:
                startActivity(new Intent(MainActivity.this, TouTiaoActivity.class));
                break;
            case R.id.tvDrawerLayout:
                startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
                break;
            case R.id.tvAndroidNative:
                startActivity(new Intent(MainActivity.this, AndroidNativeActivity.class));
                break;
            case R.id.nestedScrollView:
                startActivity(new Intent(MainActivity.this, NestedScrollViewActivity.class));
                break;
            case R.id.tvTabLayout:
                startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
                break;
            case R.id.tvInterview:
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
                break;
            case R.id.tvCrashHandler:
                startActivity(new Intent(MainActivity.this, CrashHandlerActivity.class));
                break;
            case R.id.tvMvpAndRxSerie:
                startActivity(new Intent(MainActivity.this, MvpAndRxSerieActivity.class));
                break;
            case R.id.tvStatusBar:
                startActivity(new Intent(MainActivity.this, StatusBarActivity.class));
                break;
            case R.id.tvEventBus:
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
                break;
            case R.id.tvZhiHu:
                startActivity(new Intent(MainActivity.this, ZhiHuActivity.class));
                break;
            case R.id.tvMaterialDesign:
                startActivity(new Intent(MainActivity.this, MaterialDesignActivity.class));
                break;
            case R.id.tvRefresh:
                startActivity(new Intent(MainActivity.this, LRecyclerViewActivity.class));
                break;
            case R.id.tvDialogFragment:
                startActivity(new Intent(MainActivity.this, DialogFragmentActivity.class));
                break;
            case R.id.tvFlowLayout:
                startActivity(new Intent(MainActivity.this, FlowLayoutActivity.class));
                break;
            case R.id.tvRecyclerViewHeaderAndFooter:
                startActivity(new Intent(MainActivity.this, RecyclerViewHeaderAndFooterActivity.class));
                break;
            case R.id.tvViewpagerGrid:
//                startActivity(new Intent(MainActivity.this, TestViewpagerGridActivity.class));
                break;

            default:

                break;
        }
    }

    private void checkPermission(Activity activity, String permission, int permissionCode){
        boolean permissionFlag = ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
        if(!permissionFlag){
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == 0xcf && grantResults.length >= 1){
            int result = grantResults[0];
            boolean flag = result ==PackageManager.PERMISSION_GRANTED;
            if( !flag){
                return;
            }
            //TODO:
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i(TAG, "MainActivity onStart: ");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Log.i(TAG, "MainActivity onResume: ");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i(TAG, "MainActivity onPause: ");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i(TAG, "MainActivity onStop: ");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i(TAG, "MainActivity onDestroy: ");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.i(TAG, "MainActivity onRestart: ");
//    }
}
