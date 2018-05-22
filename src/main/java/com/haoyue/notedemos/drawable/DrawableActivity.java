package com.haoyue.notedemos.drawable;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DrawableUtils;
import android.util.Log;
import android.widget.ImageView;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableActivity extends BaseActivity {

    Drawable mDrawable01, mDrawable02, mDrawable03, mDrawable04;

    DrawableUtils mDrawableUtils;
//    @BindView(R.id.tvDrawable01)
//    TextView mTvDrawable01;
//    @BindView(R.id.tvDrawable02)
//    TextView mTvDrawable02;
//    @BindView(R.id.tvDrawable03)
//    TextView mTvDrawable03;
//    @BindView(R.id.tvDrawable04)
//    TextView mTvDrawable04;
    @BindView(R.id.ivDrawable01)
    ImageView mIvDrawable01;
    @BindView(R.id.ivDrawable02)
    ImageView mIvDrawable02;
    @BindView(R.id.ivDrawable03)
    ImageView mIvDrawable03;
    @BindView(R.id.ivDrawable04)
    ImageView mIvDrawable04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);
        mDrawable01 = getDrawable(R.drawable.bitmap_drawable1);
        mDrawable02 = getDrawable(R.drawable.bitmap_drawable2);
        mDrawable03 = getDrawable(R.drawable.bitmap_drawable3);
        mDrawable04 = getDrawable(R.drawable.bitmap_drawable4);

//        mIvDrawable01.setImageDrawable(mDrawable01);
//        mIvDrawable02.setImageDrawable(mDrawable02);
//        mIvDrawable03.setImageDrawable(mDrawable03);
//        mIvDrawable04.setImageDrawable(mDrawable04);

        mIvDrawable01.setBackground(mDrawable01);
        mIvDrawable02.setBackground(mDrawable02);
        mIvDrawable03.setBackground(mDrawable03);
        mIvDrawable04.setBackground(mDrawable04);

        mIvDrawable04.postDelayed(new Runnable() {
            @Override
            public void run() {
                int wid = mIvDrawable04.getWidth();
                int hei = mIvDrawable04.getHeight();
                Log.i("TAG", "run: wid---->>" + wid + "hei---->>" + hei);
            }
        }, 500);



//        mTvDrawable01.setBackground(mDrawable01);
//        mTvDrawable02.setBackground(mDrawable02);
//        mTvDrawable03.setBackground(mDrawable03);
//        mTvDrawable04.setBackground(mDrawable04);

    }

}
