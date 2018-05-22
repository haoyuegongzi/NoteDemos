package com.haoyue.notedemos.screenshots;

import android.graphics.Bitmap;

/**
 * Created by chen1 on 2018/1/3.
 * TO DO:
 */

public class ShotBean {
    Bitmap mBitmap;
    String name;

    public ShotBean(Bitmap bitmap, String name) {
        mBitmap = bitmap;
        this.name = name;
    }
}
