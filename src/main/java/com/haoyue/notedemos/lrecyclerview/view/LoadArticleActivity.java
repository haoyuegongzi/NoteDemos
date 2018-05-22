package com.haoyue.notedemos.lrecyclerview.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.haoyue.auxiliary.WebViewUtils;
import com.haoyue.auxiliaryutil.StringUtils;
import com.haoyue.notedemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chen1
 */
public class LoadArticleActivity extends AppCompatActivity {

    WebSettings settings;

    @BindView(R.id.webLoadArticleContent)
    WebView webLoadArticleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_article);
        ButterKnife.bind(this);

        settings = webLoadArticleContent.getSettings();
        WebViewUtils.webViewSetting(settings, true, false, LoadArticleActivity.this);
        String link = getIntent().getStringExtra("link");
        if (Build.VERSION.SDK_INT >= 19) {
            webLoadArticleContent.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if (!StringUtils.isTrimEmpty(link)) {
            webLoadArticleContent.loadUrl(link);
            WebViewUtils.setViewClient(webLoadArticleContent);
            WebViewUtils.setChromeClient(webLoadArticleContent, LoadArticleActivity.this);
        }
    }
}
