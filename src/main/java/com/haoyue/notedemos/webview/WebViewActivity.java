package com.haoyue.notedemos.webview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.tvLoadWeb)
    TextView mTvLoadWeb;
    @BindView(R.id.ivLoadLocal)
    ImageView mIvLoadLocal;
    @BindView(R.id.ivLoadLocal2)
    ImageView mIvLoadLocal2;
    @BindView(R.id.tvToJsCallJava)
    TextView mTvToJsCallJava;


    /**http://www.w3school.com.cn/
     * http://www.ifeng.com
     */
    String URL = "http://www.ifeng.com";
    boolean loadView = true;
    int permissionCode = 0xffca;
    String perentPath01 = Environment.getExternalStorageDirectory().getPath();
    String childPath01 = "/Pictures/Screenshots/00000.jpg";
    String childPath02 = "/17076.jpg";
    String filePath01 = perentPath01 + childPath01;
    String filePath02 = perentPath01 + childPath02;
    boolean permissionFlag;
    WebSettings setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        permissionFlag = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        URL = "file:///android_asset/webjs.html";
        setting = mWeb.getSettings();
        WebViewUtils.webViewSetting(setting, true, false, getApplicationContext());
        WebViewUtils.setViewClient(mWeb);
        WebViewUtils.setChromeClien(mWeb, this);
        loadHtmlAndJs();
    }

    @OnClick({R.id.tvLoadWeb, R.id.tvToJsCallJava})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tvLoadWeb:
                //        mWeb.loadUrl("javascript:javaCallJavascriptNoParam()");
                mWeb.loadUrl("javascript:javaCallJavascript('Android中的Java通过WebView调用了javaScript的有参构造方法')");
//        loadView = !loadView;
//        if (loadView) {
//            setting.setLoadWithOverviewMode(true);
//            setting.setUseWideViewPort(true);
//        /*********************加载网络h5*********************/
//            mWeb.loadUrl(URL);
//        /*********************加载App内部保存的h5*********************/
//            String localUrl = "file:///android_asset/index.html";
//            mWeb.loadUrl(localUrl);
//        /*********************加载Sdcard的html*********************/
//            checkPermission();
//            String base = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures";
//            String sdcardUrl = "file:///" + base + "/webjs.html";
//            mWeb.loadUrl(sdcardUrl);
//        /******************************************/
//            mIvLoadLocal.setVisibility(View.GONE);
//            mIvLoadLocal2.setVisibility(View.GONE);
//        } else {
//            mWeb.setVisibility(View.GONE);
//            checkPermission();
//            mIvLoadLocal.setVisibility(View.VISIBLE);
//            mIvLoadLocal2.setVisibility(View.VISIBLE);
//        }
                break;
            case R.id.tvToJsCallJava:
                startActivity(new Intent(WebViewActivity.this, JsCallJavaActivity.class));
                break;
            default:
                break;
        }

    }

    private void checkPermission() {
        if (permissionFlag) {
            mWeb.loadUrl(URL);
//            Bitmap localBitmap = WebViewUtils.getLocalBitmap(filePath02);
//            mIvLoadLocal2.setImageBitmap(localBitmap);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permissionCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mWeb.loadUrl(URL);
//                Bitmap localBitmap = getLocalBitmap(filePath02);
//                mIvLoadLocal2.setImageBitmap(localBitmap);
            } else {
                Toast.makeText(WebViewActivity.this, "您拒绝了我们的存储卡读取权限的请求", Toast.LENGTH_LONG).show();
            }
        }
    }



    public void loadHtmlAndJs() {
        checkPermission();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        //设置编码格式
        setting.setDefaultTextEncodingName("utf-8");
//        mWeb.loadUrl(URL);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWeb.canGoBack()) {
            mWeb.goBack();
            WebViewUtils.clearCache(mWeb);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
