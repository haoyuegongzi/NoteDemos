package com.haoyue.notedemos.zhihu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.haoyue.notedemos.R;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * 作者：chen1 on 2018/3/14 14
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class MyGlideEngine implements ImageEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                //占位图
                .placeholder(R.mipmap.spring03)
                //图片加载异常时
                .error(R.mipmap.ic_launcher_round)
                //是否使用缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //指定图片加载的大小
                .override(resize, resize)
                .centerCrop();
            Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadAnimatedGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                //占位图
                .placeholder(R.mipmap.spring03)
                //图片加载异常时
                .error(R.mipmap.ic_launcher_round)
                //是否使用缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //指定图片加载的大小
                .override(resize, resize)
                .placeholder(placeholder);

        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                //占位图
                .placeholder(R.mipmap.spring03)
                //图片加载异常时
                .error(R.mipmap.ic_launcher_round)
                //是否使用缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //指定图片加载的大小
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);

            Glide.with(context)
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadAnimatedGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                //占位图
                .placeholder(R.mipmap.spring03)
                //图片加载异常时
                .error(R.mipmap.ic_launcher_round)
                //是否使用缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //指定图片加载的大小
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);

            Glide.with(context)
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
