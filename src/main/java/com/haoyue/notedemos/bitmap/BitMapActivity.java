package com.haoyue.notedemos.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.haoyue.auxiliary.BitmapUtil.decodeSampledBitmapFromResource;

public class BitMapActivity extends BaseActivity {

    @BindView(R.id.ivBitMap)
    ImageView mIvBitMap;
    @BindView(R.id.ivBitMapOption)
    ImageView mIvBitMapOption;
    @BindView(R.id.ivBackGround)
    ImageView mIvBackGround;
    @BindView(R.id.ivBackDrawable)
    ImageView mIvBackDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_map);

        Log.i(TAG, "BitMapActivity onCreate: ");
        ButterKnife.bind(this);
        catchOOM();
    }

    private void catchOOM(){
        try{
            //ProgressBar bt;
            mIvBitMap.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.mipmap.mincaiyun01, 500, 800));
            mIvBitMapOption.setImageDrawable(getDrawable(R.mipmap.mincaiyun01));
            //setBackground***方法图片会变形失真。
            mIvBackGround.setBackgroundResource(R.mipmap.mincaiyun01);
            mIvBackDrawable.setBackground(getDrawable(R.mipmap.mincaiyun01));
        }catch (Exception e){
            Log.i(TAG, "catchOOM: " + e);
        }
    }

    /**********requireWidth/requireHeight:需求（指定）的宽高，即前面的500和800**********/
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int requireWidth, int requireHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, requireWidth, requireHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int requireWidth, int requireHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > requireHeight || width > requireWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > requireHeight && (halfWidth / inSampleSize) > requireWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            int widthFocus = mIvBitMap.getMeasuredWidth();
            int heighFocus = mIvBitMap.getMeasuredHeight();
//            Log.i("TAG", "onWindowFocusChanged:mIvBitMap——>>>widthFocus===" + widthFocus + "———>>>heighFocus===" + heighFocus);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "BitMapActivity onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "BitMapActivity onResume: ");
        LinkedList linkedList;
        ArrayList arrayList;
        ConcurrentHashMap map;
        mIvBitMap.postDelayed(new Runnable() {
            @Override
            public void run() {
                int widthPost = mIvBitMap.getMeasuredWidth();
                int heightPost = mIvBitMap.getMeasuredHeight();
//                Log.i("TAG", "postDelayed: mIvBitMap——>>>widthPost===" + widthPost + "———>>>heightPost===" + heightPost);
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "BitMapActivity onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "BitMapActivity onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "BitMapActivity onDestroy: ");
    }
}
