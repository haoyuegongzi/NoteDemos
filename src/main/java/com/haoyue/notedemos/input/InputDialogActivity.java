package com.haoyue.notedemos.input;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.haoyue.notedemos.R;
import com.haoyue.notedemos.app_base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputDialogActivity extends BaseActivity {

    @BindView(R.id.btInputDialog)
    Button mBtInputDialog;
    @BindView(R.id.btDialogActivity)
    Button mBtDialogActivity;

    @BindView(R.id.etListener)
    EditText mEtListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dialog);
        ButterKnife.bind(this);
        setListenerFotEditText(findViewById(R.id.activity_main_layout));
    }

    @OnClick({R.id.btInputDialog, R.id.btDialogActivity})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btInputDialog:
                initDialogLayout();
                break;
            case R.id.btDialogActivity:
                startActivity(new Intent(InputDialogActivity.this, DialogActivity.class));
                break;
            default:
                break;
        }
    }
//    View intpudDialog;

    private void initDialogLayout(){
        long longTime = System.currentTimeMillis();
        int mod = (int) (longTime % 2);
        switch (mod){
            case 0:
                EditText editText = new EditText(InputDialogActivity.this);
                editText.setBackground(null);
                editText.setHint("请输入姓名");
                new AlertDialog.Builder(InputDialogActivity.this).setTitle("请输入")
                        .setIcon(R.mipmap.ic_launcher).setView(editText)
                        .setPositiveButton("确定", null).setNegativeButton("取消", null).show();
                break;
            case 1:
//                LayoutInflater inflater = getLayoutInflater();
//                intpudDialog = inflater.inflate(R.layout.input_dialog, (ViewGroup) findViewById(R.id.etInput));
//                new AlertDialog.Builder(InputDialogActivity.this).setTitle("自定义输入")
//                        .setIcon(R.mipmap.ic_launcher).setView(intpudDialog)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                EditText edittext = intpudDialog.findViewById(R.id.etInput);
//                                String content = edittext.getText().toString();
//                                Toast.makeText(InputDialogActivity.this, content, Toast.LENGTH_SHORT).show();
//                            }
//                        }).setNegativeButton("取消", null).show();
                break;
            default:
                break;
        }
    }


    private void setListenerFotEditText(View view){
        SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(view);
        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                //键盘打开
                Toast.makeText(InputDialogActivity.this, mEtListener.getText().toString() + "打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSoftKeyboardClosed() {
                //键盘关闭
                Toast.makeText(InputDialogActivity.this, mEtListener.getText().toString() + "关闭", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
