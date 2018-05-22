package com.haoyue.notedemos.asynctask;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by chen1 on 2017/11/15.
 * TO DO:
 */

public class BitmapSampleSize {
    public static Bitmap sampleBitmap(Resources res, int id, int rewWidth,int reqHei){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        options.inSampleSize = initSampleSize(options, rewWidth, reqHei);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, id, options);
    }

    public static int initSampleSize(BitmapFactory.Options options, int rewWidth, int reqHei){
        final int wid = options.outWidth;
        final int hei = options.outHeight;
        int iSampleSize = 1;
        if(wid > rewWidth || hei > reqHei){
            final int newWid = wid / 2;
            final int newHei = hei / 2;
            while ((newWid / iSampleSize) >= rewWidth && (newHei / iSampleSize) > reqHei){
                iSampleSize *= 2;
            }
        }
        return iSampleSize;
    }
}
