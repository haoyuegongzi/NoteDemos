package com.haoyue.auxiliary;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @version 1.0
 * @created 2012-3-21
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
        activityStack = new Stack<Activity>();
    }

    /**
     * 单例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 移除Activity
     * @param cls 保留的类
     * @param currentActivity 当前的Activity
     */
    public void finishOtherActiviy(Class<?> cls, Activity currentActivity) {
        for (Activity activity : activityStack) {
            if (activity != currentActivity) {
                if (!activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                activityStack.remove(cls);
            }
        }
    }

    /**
     * 移除非指定的class
     * @param cls 指定的class
     */
    public void moveOneActivity(Class<?> cls) {
        int size = activityStack.size()-1;
        for (int i = size; i > 0; i--) {
            try {
                Activity act = activityStack.get(i);
                if (null != act && !act.getClass().equals(cls)) {
                    act.finish();
                    activityStack.remove(act);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (null != activity) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 获取当前堆栈中Activity的个数
     */
    public int getActivityStackSize() {
        if (!activityStack.empty()) {
            return activityStack.size();
        } else {
            return 0;
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context, Class<?> tClass) {
        try {
            context.stopService(new Intent(context, tClass));
            this.finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            // 退出程序
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测Activity是否能被启动
     * @param intent 跳转意图 context 上下文对象
     * @return true - 能被启动 false - 不能被启动
     */
    public boolean checkActivityIsExits(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = intent.resolveActivity(packageManager);
        if (componentName == null) {
            return false;
        } else {
            return true;
        }
    }

     /**
     * 检测APP权限
     * @param context
     * @param permission 权限名称
     * @retuan boolean true 授权，false未授权
     */
     public boolean checkPremission(Context context , String permission){
         PackageManager pm = context.getPackageManager();
         return (PackageManager.PERMISSION_GRANTED ==pm.checkPermission(permission, context.getPackageName()));
     }
}
