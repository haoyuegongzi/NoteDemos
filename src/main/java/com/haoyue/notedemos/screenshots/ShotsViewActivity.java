package com.haoyue.notedemos.screenshots;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.app_base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShotsViewActivity extends BaseActivity {

    @BindView(R.id.tvToUtilsActivity)
    TextView mTvToUtilsActivity;
    @BindView(R.id.llShot)
    LinearLayout mLlShot;
    @BindView(R.id.svShot)
    ScrollView mSvShot;
    @BindView(R.id.svRootShot)
    ScrollView mSvRootShot;
    @BindView(R.id.ivYellowShot)
    ImageView mIvYellowShot;

    List<ShotBean>mBitmapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_view);
        ButterKnife.bind(this);
        shotView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void shotView(){
        //scrollview的整体截屏
        Bitmap rootShotBitmap = ShotUtils.getWholeScrollViewToBitmap(mSvRootShot);
        //屏幕可见区域的截图(某个View/ViewGroup)
        Bitmap llShotBitmap = ShotUtils.getNormalViewScreenshot(mLlShot);
        Bitmap imageBitmap = ShotUtils.getNormalViewScreenshot(mIvYellowShot);
        //整个屏幕截图Shot
        Bitmap screenBitmap = ShotUtils.shotScreen(this);
        //整个屏幕截图intercepte
        Bitmap intercepteBitMap = ShotUtils.intercepteScreen(this);


        mBitmapList.add(new ShotBean(rootShotBitmap,"根目录"));
        mBitmapList.add(new ShotBean(llShotBitmap,"前两张图片的线性布局"));
        mBitmapList.add(new ShotBean(imageBitmap,"穿旗袍的ImageView"));
        mBitmapList.add(new ShotBean(screenBitmap,"截屏Shot"));
        mBitmapList.add(new ShotBean(intercepteBitMap,"截屏intercepte"));

        BaseApplication.mBitmapList = mBitmapList;
    }

    @OnClick(R.id.tvToUtilsActivity)
    public void onViewClicked() {
        startActivity(new Intent(ShotsViewActivity.this, UtilsActivity.class));
    }
}
