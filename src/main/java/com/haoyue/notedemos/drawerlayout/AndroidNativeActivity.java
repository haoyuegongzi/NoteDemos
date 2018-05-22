package com.haoyue.notedemos.drawerlayout;

import android.graphics.Color;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static android.os.Looper.myLooper;

/**
 * @author chen1
 */
public class AndroidNativeActivity extends BaseActivity {
    List<HandlerBean> listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_android_native);
        listBean = new ArrayList<>();

        learnHandler();
    }

    private void learnHandler(){
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Message msg = new Message();
//                            msg.what = 6;
//                            msg.arg1 = 4;
//
//                            Bundle bundle = new Bundle();
//                            listBean.add(new HandlerBean("chongqing","18502860576", Color.CYAN));
//                            bundle.putParcelableArrayList("listBean", (ArrayList<? extends Parcelable>) listBean);
//
//                            bundle.putString("handlerBean","handlerBeanhandlerBeanhandlerBean");
//                            msg.setData(bundle);
//                            handler.sendMessage(msg);
//                        }
//                    }).start();
                    Message msg = new Message();
                    msg.what = 3;
                    msg.arg1 = 2;

                    Bundle bundle = new Bundle();
                    listBean.add(new HandlerBean("sichuan","18502860576", Color.BLACK));
                    bundle.putParcelableArrayList("listBean", (ArrayList<? extends Parcelable>) listBean);

                    bundle.putString("handlerBean","handlerBean");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }).start();
        }catch (Exception e){
            Log.i(TAG, "onCreate: Exception == " + e);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "handleMessage: what == " + msg.what +"\n arg1 == " + msg.arg1);
            try{
                Bundle bundle = msg.getData();
                Log.i(TAG, "handleMessage: bundle is empty：" + bundle.isEmpty());
                Log.i(TAG, "handleMessage: handlerBean == " + bundle.getString("handlerBean"));

                List<HandlerBean> list = bundle.getParcelableArrayList("listBean");
                Log.i(TAG, "handleMessage: listBean == " + list.get(0).toString());
            }catch (Exception e){
                Log.i(TAG, "handleMessage: Exception == " + e);
            }
        }
    };
}
