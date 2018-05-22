package com.haoyue.notedemos.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.haoyue.notedemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JsCallJavaActivity extends AppCompatActivity {
    @BindView(R.id.tvJsCallJava)
    TextView mTvJsCallJava;
    @BindView(R.id.wvJsCallJava)
    WebView mWvJsCallJava;

    WebSettings setting;
    String URL = "file:///android_asset/webjs.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java);
        ButterKnife.bind(this);
        this.getSupportActionBar().hide();
        setting = mWvJsCallJava.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        //设置编码格式
        setting.setDefaultTextEncodingName("utf-8");


        WebViewUtils.webViewSetting(setting, true, false, getApplicationContext());
        WebViewUtils.setViewClient(mWvJsCallJava);
        WebViewUtils.setChromeClien(mWvJsCallJava, JsCallJavaActivity.this);

        mWvJsCallJava.addJavascriptInterface(new Javascript(), "js_call_java");
        mWvJsCallJava.loadUrl(URL);
    }

    @OnClick(R.id.tvJsCallJava)
    public void onViewClicked() {

    }

    final class Javascript{
        public Javascript(){

        }

        @JavascriptInterface
        public void javaCallJsMethod1() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWvJsCallJava.loadUrl("javascript:javaCallJsNoArgs()");
                }
            });
        }

        @JavascriptInterface
        public void javaCallJsMethod2() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWvJsCallJava.loadUrl("javascript:javaCallJsExistArgs('我是皓月')");
                }
            });
        }
    }
}
