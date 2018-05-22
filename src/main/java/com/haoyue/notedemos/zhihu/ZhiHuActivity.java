package com.haoyue.notedemos.zhihu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.haoyue.auxiliary.ToastUtils;
import com.haoyue.notedemos.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class ZhiHuActivity extends AppCompatActivity {

    int permissionCode = 0xf221;
    int REQUEST_CODE_CHOOSE = 0xbb21;
    int indexPostion = 0;
    boolean permissionStorage = false;
    List<Uri> photoUriList;
    RequestOptions options;


    @BindView(R.id.aivZhiHu)
    ImageView aivZhiHu;
    @BindView(R.id.btnLast)
    Button btnLast;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.tvloadUri)
    TextView tvloadUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);
        ButterKnife.bind(this);
        checkPermission();
        if (permissionStorage) {
            //TODO: 图片处理
            callZhiHuPeactrue();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void callZhiHuPeactrue() {
        Matisse.from(ZhiHuActivity.this)
                // 选择 图片 的类型
                .choose(MimeType.allOf())
                //用来显示一个从 1 开始的数字
                .countable(true)
                // 图片选择的最多数量
                .maxSelectable(9)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.particularly_large))
                //设置图像选择和预览活动所需的方向。
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                // 缩略图的比例,(0.0，1.0)的浮点值。
                .thumbnailScale(0.85F)
                // 使用的图片加载引擎
                .imageEngine(new MyGlideEngine())
                //启动 Matisse 的时候,调用Matisse中内置的两种主题
                .theme(R.style.Matisse_Zhihu | R.style.Matisse_Dracula)
                // 设置作为标记的请求码
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            photoUriList = Matisse.obtainResult(data);

            if (photoUriList.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < photoUriList.size(); i++) {
                    sb.append(photoUriList.get(i)).append("\n");
                }
                tvloadUri.setText(sb.toString());
            } else {
                tvloadUri.setText("photoUriList 是空的");
            }
        }
    }

    private void checkPermission() {
        int permissionCode = ActivityCompat.checkSelfPermission(ZhiHuActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCode != PackageManager.PERMISSION_GRANTED) {
            String[] permissionArray = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(ZhiHuActivity.this, permissionArray, permissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permissionCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionStorage = true;
            ToastUtils.showToast(ZhiHuActivity.this, "获得了权限", 3);
        }
    }

    @OnClick({R.id.btnLast, R.id.btnNext})
    public void onViewClicked(View view) {
        if (photoUriList == null || photoUriList.size() <= 0) {
            return;
        }
        switch (view.getId()) {
            case R.id.btnLast:
                if (indexPostion == 0) {
                    indexPostion = photoUriList.size();
                }
                indexPostion--;
                break;
            case R.id.btnNext:
                if (indexPostion == photoUriList.size()) {
                    indexPostion = 0;
                }
                indexPostion++;
                break;
            default:
                break;
        }
        Glide.with(ZhiHuActivity.this).load(photoUriList.get(indexPostion)).apply(options).into(aivZhiHu);
    }
}
