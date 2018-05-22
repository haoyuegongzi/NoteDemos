package com.haoyue.notedemos.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.io.FileInputStream;
import java.util.Map;

/**
 * Created by chen1 on 2018/1/12.
 * TO DO:WebViewUtils 工具类
 */

public class WebViewUtils {
    public static void webViewSetting(WebSettings setting, boolean setTure, boolean setFalse, Context context) {
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否允许数据库存储，需要读写权限,默认false**/
        /**查看setDatabasePath API 如何正确设置数据库存储。
         * 该设置拥有全局特性，同一进程所有WebView实例共用同一配置。注意：保证在同一进程的任一WebView
         * 加载页面之前修改该属性，因为在这之后设置WebView可能会忽略该配置 **/
        setting.setDatabaseEnabled(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否设置缓存,必需设置有效的缓存路径才能生效，默认值 false**/
        setting.setAppCacheEnabled(true);
        setting.setAppCachePath(context.getCacheDir().getAbsolutePath());
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否允许定位，默认true。
         * 注意：为了保证定位可以使用，要保证以下几点：
         * 1、Application需要有android.Manifest.permission#ACCESS_COARSE_LOCATION定位权限
         * 2、Application需要实现WebChromeClient#onGeolocationPermissionsShowPrompt的监听回调，
         *    接收Js定位请求访问地理位置的通知
         * 3、需要一下权限：
         *     <uses-permission android:name="android.permission.INTERNET"/>
         *     <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
         *     <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
         **/
        setting.setGeolocationEnabled(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否保存表单数据，默认false**/
        setting.setSaveFormData(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否启动概述模式浏览界面，当页面宽度超过WebView显示宽度时，缩小页面适应WebView。默认false
         * 需要和setUseWideViewPort()配合使用才生效**/
        setting.setLoadWithOverviewMode(setTure);
        /**是否支持ViewPort的meta tag属性，如果页面有ViewPort meta tag 指定的宽度，
         * 则使用meta tag指定的值，否则默认使用宽屏的视图窗口(横竖屏时的宽度)**/
        setting.setUseWideViewPort(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**通知WebView是否需要设置一个节点获取焦点,当WebView#requestFocus(int,android.graphics.Rect)
         * 被调用的时候，默认true **/
        setting.setNeedInitialFocus(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**布局算法**/
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**设置是否(支持)允许执行JS方法**/
        setting.setJavaScriptEnabled(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否支持多窗口，如果设置为true ，WebChromeClient的onCreateWindow方法必须被主程序实现，默认false**/
        setting.setSupportMultipleWindows(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否允许JS自动打开窗口。默认false **/
        setting.setJavaScriptCanOpenWindowsAutomatically(setFalse);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否允许获取WebView的内容URL,可以让WebView访问ContentPrivider存储的内容.默认true**/
        setting.setAllowContentAccess(setTure);
        /**是否允许访问WebView内部文件，默认true, 不知道什么意思**/
        setting.setAllowFileAccess(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否允许通过file url加载的Javascript读取本地文件，默认值 false**/
        setting.setAllowFileAccessFromFileURLs(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false**/
        setting.setAllowUniversalAccessFromFileURLs(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////资源加载
        /**是否自动加载图片，包括从网络获取的图片、app内置的图片以及从Sdcard获取的图片**/
        setting.setLoadsImagesAutomatically(setTure);
        /**是否自动加载网络图片**/
        setting.setBlockNetworkImage(setTure);
        /**是否加载网络资源**/
        setting.setBlockNetworkLoads(setTure);
        /////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////缩放(zoom)
        /**是否支持缩放，配合setBuiltInZoomControls使用，默认true **/
        setting.setSupportZoom(setTure);
        /**是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false**/
        setting.setBuiltInZoomControls(setFalse);
        /**是是否显示内置缩放控件(有点像进度条，左边是“-”，右边是“+”)**/
        setting.setDisplayZoomControls(setFalse);
        /////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////默认文本编码，默认值 "UTF-8"
        /**设置页面的编码格式，默认UTF-8 **/
        setting.setDefaultTextEncodingName("utf-8");
        /////////////////////////////////////////////////////////////////////////////////////////
        /**设置默认字体大小，默认16，取值区间[1-72]，超过范围，使用其上限值。
         * 另外WebView各种字体的大小的设置有一系列的方法：setXxxxFontSize(int size)**/
        setting.setDefaultFontSize(16);
        /**默认等宽字体尺寸，默认值16**/
        setting.setDefaultFixedFontSize(16);
        /**最小文字尺寸，默认值 8**/
        setting.setMinimumFontSize(8);
        /**最小文字逻辑尺寸，默认值 8**/
        setting.setMinimumLogicalFontSize(8);
        /**文字缩放百分比，默认值 100**/
        setting.setTextZoom(100);
        /////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////设置字体
        /**是否支持多窗口如果设置为true，WebChromeClient的onCreateWindow方法必须被主程序实现，默认false
         * 另外WebView各种字体的设置有一系列的方法：setXxxxFontFamily(String font)**/
        setting.setStandardFontFamily("sans-serif");
        /**衬线字体，默认值 "serif"**/
        setting.setSerifFontFamily("serif");
        /**无衬线字体，默认值 "sans-serif"**/
        setting.setSansSerifFontFamily("sans-serif");
        /**等宽字体，默认值 "monospace"**/
        setting.setFixedFontFamily("monospace");
        /**手写体(草书)，默认值 "cursive"**/
        setting.setCursiveFontFamily("cursive");
        /**幻想体，默认值 "fantasy"**/
        setting.setFantasyFontFamily("fantasy");
        /////////////////////////////////////////////////////////////////////////////////////////
        /**存储(storage)，启用HTML5 DOM storage API，默认值 false*/
        setting.setDomStorageEnabled(true);
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否需要用户手势来播放Media，默认true**/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setting.setMediaPlaybackRequiresUserGesture(setTure);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        /**设置加载不安全资源的WebView加载行为。KITKAT版本以及以下默认为MIXED_CONTENT_ALWAYS_ALLOW
         * 方式，LOLLIPOP默认MIXED_CONTENT_NEVER_ALLOW。强烈建议：使用MIXED_CONTENT_NEVER_ALLOW **/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        /**是否在离开屏幕时光栅化(会增加内存消耗)，默认值 false**/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setting.setOffscreenPreRaster(false);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        /**根据cache-control决定是否从网络上取数据
         * LOAD_DEFAULT 默认加载方式
         * LOAD_CACHE_ELSE_NETWORK 按网络情况使用缓存
         * LOAD_NO_CACHE 不使用缓存
         * LOAD_CACHE_ONLY 只使用缓存 **/
        if (isNetworkAvailable(context)) {
            //有网络，从网络获取。
            setting.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            // 没网，离线加载缓存(即使已经过期)
            setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

    public static void setViewClient(WebView mWeb) {
        /*****作用：让HTML网页显示在显示在WebView中而不是用浏览器打开**/
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //在页面加载结束时调用。我们可以关闭loading 条，切换程序动作。
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使得打开网页时不调用系统浏览器， 而是在本WebView中显示
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //加载页面的服务器出现错误时（如404）调用。
                switch (errorCode) {
                    //HttpStatus.SC_NOT_FOUND
                    case 404:
                        break;
                    default:
                        break;
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //专用于https请求
                super.onReceivedSslError(view, handler, error);
            }
        });
    }


    static WebChromeClient.CustomViewCallback mCallback;
    static View vCustom;

    public static void setChromeClien(final WebView mWeb, final AppCompatActivity avtivity) {
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //网页的加载进度:newProgress即是加载进度百分比:0<=newProgress<=100;
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //要加载的网页的标题，比如http://www.baidu.com的标题：百度；http://www.ifeng.com的标题：凤凰网。
                super.onReceivedTitle(view, title);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //允许弹出javascript的警告框，message就是警告框的内容。
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                //允许弹出javascript的确认框，message就是确认信息。
                new AlertDialog.Builder(avtivity)
                        .setTitle("信息确认")
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
                // 返回布尔值：判断点击时确认还是取消
                // true表示点击了确认；false表示点击了取消；
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //允许弹出javascript输入框
                EditText et = new EditText(avtivity);
                AlertDialog.Builder dialog = new AlertDialog.Builder(avtivity);
                dialog.setTitle("输入信息").setView(et)
                        .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //返回输入框中的值，
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //返回null。
                            }
                        }).setCancelable(false).show();
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            // 指定源的网页内容在没有设置权限状态下尝试使用地理位置API。
            // 当H5调用地理位置API时，会先通过WebChromeClient.onGeolocationPermissionsShowPrompt申请授权
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                boolean allow = true;   // 是否允许origin使用定位API
                boolean retain = false; // 内核是否记住这次制授权
                callback.invoke(origin, true, false);
            }

            // 之前调用 onGeolocationPermissionsShowPrompt() 申请的授权被取消时，隐藏相关的UI。
            @Override
            public void onGeolocationPermissionsHidePrompt() {

            }
            //onShowCustom And onHideCustomView：View全屏(Fullscreen)
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                setFullscreen(mWeb, avtivity, true);
                vCustom = view;
                mCallback = callback;
                if (vCustom != null) {
                    ViewGroup parent = (ViewGroup) mWeb.getParent();
                    parent.addView(vCustom);
                }
            }
            //onShowCustom And onHideCustomView：View全屏(Fullscreen)
            @Override
            public void onHideCustomView() {
                setFullscreen(mWeb, avtivity, false);
                if (vCustom != null) {
                    ViewGroup parent = (ViewGroup) mWeb.getParent();
                    parent.removeView(vCustom);
                    vCustom = null;
                }
                if (mCallback != null) {
                    mCallback.onCustomViewHidden();
                    mCallback = null;
                }
            }
        });
    }

    public static void clearCache(WebView mWeb) {
        //清除网页访问留下的缓存,这个方法是针对整个应用程序.
        mWeb.clearCache(true);
        //清除当前webview访问的历史所有记录
        mWeb.clearHistory();
        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        mWeb.clearFormData();
    }

    /*************************************WebView's Base Setting****************************************/
    /**
     * TODO:获取当前页面的URL
     * @param mWeb
     * @return
     */
    public String getUrl(WebView mWeb){
        return mWeb.getUrl();
    }

    /**
     * TODO：获取当前页面的原始URL(重定向后可能与当前url不同)
     * @param mWeb
     * @return
     */
    public String getOriginalUrl(WebView mWeb){
        return mWeb.getOriginalUrl();
    }

    /**
     * TODO:获取当前页面的标题
     * @param mWeb
     * @return
     */
    public String getTitle(WebView mWeb){
        return mWeb.getTitle();
    }

    /**
     * TODO:获取当前页面的favicon
     * @param mWeb
     * @return
     */
    public Bitmap getFavicon(WebView mWeb){
        return mWeb.getFavicon();
    }

    /**
     * TODO:获取当前页面的加载进度
     * @param mWeb
     * @return
     */
    public int getProgress(WebView mWeb){
        return mWeb.getProgress();
    }

    /**
     * TODO:通知WebView内核网络状态
     * @param mWeb
     * @param networkUp
     */
    public void setNetworkAvailable(WebView mWeb,boolean networkUp){
        mWeb.setNetworkAvailable(networkUp);
    }

    /**
     * TODO:设置初始缩放比例
     * @param mWeb
     * @param scaleInPercent
     */
    public void setInitialScale(WebView mWeb,int scaleInPercent){
        mWeb.setInitialScale(scaleInPercent);
    }

    /*************************************************WebView's Load Url****************************************/
    /**
     * TODO:加载URL指定的网页
     * @param mWeb
     * @param url
     */
    public static void loadUrl(WebView mWeb,String url){
        mWeb.loadUrl(url);
    }

    /**
     * TODO:加载携带http headers的URL指定的网页
     * @param mWeb
     * @param url
     * @param additionalHttpHeaders
     */
    public static void loadUrlWithHeaders(WebView mWeb,String url, Map<String, String> additionalHttpHeaders){
        mWeb.loadUrl(url, additionalHttpHeaders);
    }

    /**
     * TODO:加载使用POST请求的URL指定的网页
     * @param mWeb
     * @param url
     * @param postData
     */
    public static void loadUrlWithPost(WebView mWeb,String url, byte[] postData){
        mWeb.postUrl(url, postData);
    }

    /**
     * TODO:加载URL指定的网页
     * @param mWeb
     */
    public static void reload(WebView mWeb){
        mWeb.reload();
    }

    /**
     * TODO:使用baseUrl加载内容
     * @param mWeb
     * @param data
     * @param mimeType
     * @param encoding
     */
    public static void loadData(WebView mWeb,String data, String mimeType, String encoding){
        mWeb.loadData(data, mimeType, encoding);
    }

    /**
     * TODO:使用baseUrl加载内容
     * @param mWeb
     * @param baseUrl
     * @param data
     * @param mimeType
     * @param encoding
     * @param historyUrl
     */
    public static void loadDataWithBaseURL(WebView mWeb, String baseUrl, String data,
                                            String mimeType, String encoding, String historyUrl){
        mWeb.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    /*************************************************WebView's Set Javascript****************************************/
    /**
     * TODO:注入Javascript对象
     * @param mWeb
     * @param object
     * @param name
     */
    @SuppressLint("JavascriptInterface")
    public static void addJavascriptInterface(WebView mWeb, Object object, String name){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWeb.addJavascriptInterface(object, name);
        }else {

        }
    }

    /**
     * TODO:移除已注入的Javascript对象，下次加载或刷新页面时生效
     * @param mWeb
     * @param name
     */
    public static void removeJavascriptInterface(WebView mWeb, String name){
        mWeb.removeJavascriptInterface(name);
    }

    /**
     *对传入的JS表达式求值，通过resultCallback返回结果,此函数添加于API19，必须在UI线程中调用，回调也将在UI线程
     */
    public static void addJavascriptInterface(WebView mWeb, String name, ValueCallback<String> resultCallback){
        mWeb.evaluateJavascript(name, resultCallback);
    }

    /*************************************************WebView's Navigate****************************************/
    public static void webViewNavigate(WebView mWeb, int steps){
        // 前进/后退steps页，大于0表示前进小于0表示后退
        mWeb.goBackOrForward(steps);
    }

    /**
     * 后退一页
     * @param mWeb
     */
    public static void goBack(WebView mWeb){
        mWeb.goBack();
    }

    /**
     * 前进一页
     * @param mWeb
     */
    public static void goForward(WebView mWeb){
        mWeb.goForward();
    }

    /**
     * TODO:复制一份BackForwardList
     * @param mWeb
     * @return
     */
    public static WebBackForwardList copyBackForwardList(WebView mWeb){
        return mWeb.copyBackForwardList();
    }

    /**
     * TODO:是否可后退
     * @param mWeb
     * @return
     */
    public static boolean canGoBack(WebView mWeb){
        return mWeb.canGoBack();
    }

    /**
     * TODO:是否可前进
     * @param mWeb
     * @return
     */
    public static boolean canGoForward(WebView mWeb){
        return mWeb.canGoForward();
    }

    /**
     * TODO:是否可前进/后退steps页，大于0表示前进小于0表示后退
     * @param mWeb
     * @param steps
     * @return
     */
    public static boolean canGoForward(WebView mWeb, int steps){
        return mWeb.canGoBackOrForward(steps);
    }

    /*************************************************WebView's Surch Orl****************************************/
    /**
     * TODO:清除网页查找的高亮匹配字符串
     * @param mWeb
     */
    public void clearMatches(WebView mWeb){
        mWeb.clearMatches();
    }

    /**
     * TODO:查找下一个匹配的字符串
     * @param mWeb
     * @param forward
     */
    public void findNext (WebView mWeb, boolean forward){
        mWeb.findNext(forward);
    }

    /**
     * TODO:异步执行查找网页内包含的字符串并设置高亮，查找结果会回调.
     * @param mWeb
     * @param find
     */
    public void findAllAsync (WebView mWeb, String find){
        mWeb.findAllAsync(find);
    }

    /**
     * TODO:设置网页查找结果回调
     * @param mWeb
     * @param listener
     */
    public void setFindListener(WebView mWeb, WebView.FindListener listener){
        mWeb.setFindListener(listener);
    }

    /*************************************************WebView's 截屏/翻页/缩放****************************************/
    /**
     * TODO:保存网页(.html)到指定文件
     * @param mWeb
     * @param filename
     */
    public void saveWebArchive(WebView mWeb, String filename){
        mWeb.saveWebArchive(filename);
    }

    /**
     * TODO:保存网页(.html)到文件
     * @param mWeb
     * @param basename
     * @param autoname
     * @param callback
     */
    public void saveWebArchive(WebView mWeb, String basename, boolean autoname, ValueCallback<String> callback){
        mWeb.saveWebArchive(basename, autoname, callback);
    }

    /**
     * TODO:上翻一页，即向上滚动WebView高度的一半
     * @param mWeb
     * @param top
     */
    public void pageUp(WebView mWeb, boolean top){
        mWeb.pageUp(top);
    }

    /**
     * TODO:下翻一页，即向下滚动WebView高度的一半
     * @param mWeb
     * @param bottom
     */
    public void pageDown(WebView mWeb, boolean bottom){
        mWeb.pageDown(bottom);
    }

    /**
     * TODO:按指定比例缩放
     * @param mWeb
     * @param factor
     */
    public void zoomBy(WebView mWeb, float factor){
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            mWeb.zoomBy(factor);
        }
    }

    /**
     * TODO：放大
     * @param mWeb
     * @return
     */
    public boolean zoomIn(WebView mWeb){
        return mWeb.zoomIn();
    }

    /**
     * TODO:可否缩放
     * @param mWeb
     * @return
     */
    public boolean zoomOut(WebView mWeb){
        return mWeb.zoomOut();
    }
    public static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Bitmap getLocalBitmap(String URL) {
        try {
            FileInputStream input = new FileInputStream(URL);
            Log.i("TAG", "onViewClicked: input is Empty?:" + (input == null));
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            Log.i("TAG", "onViewClicked: 抛异常：" + e);
            return null;
        }
    }

    public static void setFullscreen(WebView mWeb, AppCompatActivity activity, boolean fullscreen) {
        if (fullscreen) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getSupportActionBar().hide();
            mWeb.setVisibility(View.GONE);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getSupportActionBar().show();
            mWeb.setVisibility(View.VISIBLE);
        }
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
