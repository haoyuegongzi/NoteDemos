package com.haoyue.notedemos.app_base;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.haoyue.auxiliary.ImproveCrashHandler;
import com.haoyue.notedemos.crash.CrashHandler;
import com.haoyue.notedemos.screenshots.ShotBean;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * @author chen1
 * @date 2017/11/22
 * TO DO:
 */

public class BaseApplication extends Application {

    public static List<ShotBean> mBitmapList = new ArrayList<>();
    static BaseApplication instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // initialize必须放在attachBaseContext最前面，初始化代码直接写在Application类里面，切勿封装到其他类。
        // 不建议在Application.onCreate()中初始化，因为如果带有ContentProvider，就会使得Sophix初始化时机太迟从而引发问题。
        initSopFix();
    }

    /**
     * 如何使用CrashHandler：
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中;
        // 该方法主要用于查询服务器是否有新的可用补丁.SDK内部限制连续两次queryAndLoadNewPatch()方法调用不能短于3s, 否则会报code:19错误码
        SophixManager.getInstance().queryAndLoadNewPatch();

        instance = this;
        ImproveCrashHandler crashHandler = ImproveCrashHandler.getInstance();
        crashHandler.initVariable(this);

    }

    private void initSopFix(){
        String appVersion;
        try{
            appVersion = this.getPackageManager().getPackageArchiveInfo(this.getPackageName(), 0).versionName;
        }catch (Exception e){
            appVersion = "1.0.0";
            e.printStackTrace();
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            System.out.print("补丁加载回调通知: 当你看到这条信息的时候，表示补丁加载成功");
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            String msg = "补丁加载回调通知: 当你看到这条信息的时候，表明新补丁生效需要重启. 开发者可提示用户或者强制重启";
                            System.out.print(msg);
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            String msg = "补丁加载回调通知: 当你看到这条信息的时候，表示补丁加载失败，详情查看PatchStatus类说明";
                            System.out.print(msg);
                        }
                    }
                }).initialize();

    }

    public static BaseApplication getInstance(){
        return instance;
    }

    private void checkPermission(Activity activity, int permissionCode){
        boolean permissionFlag = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        if(!permissionFlag){
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionCode);
        }
    }

    public static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("TAG","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
