package com.haoyue.notedemos.loadgif;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chen1
 */
public class LoadGifActivity extends BaseActivity {

    @BindView(R.id.ivLoadGif)
    ImageView mIvLoadGif;
    @BindView(R.id.ivLoadGlide)
    ImageView mIvLoadGlide;

TextView tvThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_gif);
        tvThread = (TextView) findViewById(R.id.tvThread);
        ButterKnife.bind(this);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    Thread.sleep(100);
//                }catch (Exception e){
//
//                }
//                tvThread.setText("子线程加载");
//            }
//        }).start();

        String url = "http://image.hnol.net/c/2017-12/13/14/201712131427572961-5058976.jpg";
        RequestOptions options = new RequestOptions()
                //占位图
                .placeholder(R.mipmap.spring03)
                //图片加载异常时
                .error(R.mipmap.ic_launcher_round)
                //是否使用缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //指定图片加载的大小
                .override(720, 1080);

        Glide.with(LoadGifActivity.this).load(url).apply(options).into(mIvLoadGlide);
        Glide.with(LoadGifActivity.this).load(R.drawable.gif0).into(mIvLoadGif);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
