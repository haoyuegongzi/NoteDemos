package com.haoyue.notedemos.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.haoyue.notedemos.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.tvEventBusTest)
    TextView tvEventBusTest;
    @BindView(R.id.btnJumpNext)
    Button btnJumpNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().post(new EvenBusMsg("皓月", "男", "成都", 0xffd2));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * ThreadMode线程通信:
     * threadMode = ThreadMode.POSTING:默认调用方式，在调用post方法的线程执行，避免了线程切换，性能开销最少:log(event.message);
     * threadMode = ThreadMode.MAIN: Called in Android UI's main thread.textField.setText(event.message);
     * threadMode = ThreadMode.BACKGROUND:如果调用post方法的线程不是主线程，则直接在该线程执行,
     *                                    如果是主线程，则切换到后台单例线程，多个方法公用同个后台线程，按顺序执行，避免耗时操作
     *                                    saveToDisk(event.message);
     * threadMode = ThreadMode.ASYNC:开辟新独立线程，用来执行耗时操作，例如网络访问, EventBus内部使用了线程池，
     *                               但是要尽量避免大量长时间运行的异步线程，限制并发线程数量.可以通过EventBusBuilder修改，
     *                               默认使用Executors.newCachedThreadPool().backend.send(event.message);

     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EvenBusMsg msg) {
        tvEventBusTest.setText(msg.toString());
    }

    @OnClick(R.id.btnJumpNext)
    public void onViewClicked() {
        startActivity(new Intent(EventBusActivity.this, TestEventBus1Activity.class));
    }
}
