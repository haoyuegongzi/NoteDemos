package com.haoyue.notedemos.screenshots;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;
import com.haoyue.notedemos.app_base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UtilsActivity extends BaseActivity {

    @BindView(R.id.btLast)
    Button mBtLast;
    @BindView(R.id.btNext)
    Button mBtNext;
    @BindView(R.id.ivsetImage)
    ImageView mIvsetImage;

    int index = 0;
    List<ShotBean> mBitmapList = new ArrayList<>();
    @BindView(R.id.tvImageName)
    TextView mTvImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils);
        ButterKnife.bind(this);
        mBitmapList = BaseApplication.mBitmapList;

        if (mBitmapList != null || mBitmapList.size() >= 0) {
            mIvsetImage.setImageDrawable(new BitmapDrawable(mBitmapList.get(0).mBitmap));
            mTvImageName.setText(mBitmapList.get(0).name);
        }
    }


    @OnClick({R.id.btLast, R.id.btNext})
    public void onViewClicked(View view) {
        if (mBitmapList == null || mBitmapList.size() <= 0) {
            Toast.makeText(this, "The Data is null", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()) {
            case R.id.btLast:
                index--;
                if (index < 0) {
                    index = mBitmapList.size() - 1;
                }
                break;
            case R.id.btNext:
                index++;
                if (index > mBitmapList.size() - 1) {
                    index = 0;
                }
                break;
            default:
                break;
        }
        if (mBitmapList.get(index).mBitmap != null) {
            mIvsetImage.setImageDrawable(new BitmapDrawable(mBitmapList.get(index).mBitmap));
        }
        mTvImageName.setText(mBitmapList.get(index).name);
    }
}
