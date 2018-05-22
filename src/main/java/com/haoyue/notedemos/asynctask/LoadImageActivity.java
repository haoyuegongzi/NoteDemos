package com.haoyue.notedemos.asynctask;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chen1
 *         TODO: Android常见的三种图片加载库
 */
public class LoadImageActivity extends BaseActivity {

    Typeface face;
    @BindView(R.id.tvTextType)
    TextView mTvTextType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.bind(this);
        face = Typeface.createFromAsset(getAssets(), "华康少女字体.ttf");

        mTvTextType.setText("测试Android字体");
        mTvTextType.setTypeface(face);
    }


}
