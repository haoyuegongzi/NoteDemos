package com.haoyue.notedemos.crash;

import android.os.Bundle;
import android.util.Log;
import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author chen1
 */
public class CrashHandlerActivity extends BaseActivity {
    String string = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_handler);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "onCreate: " + string.equals("huahua"));
    }
}
