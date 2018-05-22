package com.haoyue.notedemos.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.haoyue.auxiliary.AdaptiveImageView;
import com.haoyue.notedemos.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haoyue.auxiliaryutil.LogUtils.D;
import static com.haoyue.auxiliaryutil.LogUtils.I;

/**
 * @author chen1
 */
public class DialogActivity extends AppCompatActivity {
    static final String TAG = "TAG";
    String[] sex = {"Man", "Woman", "太监"};
    String[] action = {"阿里", "腾讯", "百度", "京东", "华为", "小米", "永辉"};
    boolean[] falg = {true, false, true, false, true, true, true};
    String check = "huahua";
    StringBuffer sb = new StringBuffer();
    ProgressDialog progressDialog;
    ScheduledExecutorService executorService;
    TimerTask task;
    Timer timer;
    int progress = 0;

    @BindView(R.id.imageView)
    AdaptiveImageView imageView;
    @BindView(R.id.btIntiDialog)
    Button btIntiDialog;
    @BindView(R.id.btDialogSetItem)
    Button btDialogSetItem;
    @BindView(R.id.btDialogSingleChoiceItems)
    Button btDialogSingleChoiceItems;
    @BindView(R.id.btDialogSetMultiChoiceItems)
    Button btDialogSetMultiChoiceItems;
    @BindView(R.id.btProgressDialogCycler)
    Button btProgressDialogCycler;
    @BindView(R.id.btProgressDialogHorizontal)
    Button btProgressDialogHorizontal;
    @BindView(R.id.btDataPickerDialog)
    Button btDataPickerDialog;
    @BindView(R.id.btTimePickerDialog)
    Button btTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        Log.i(TAG, "onCreate: ");
        ButterKnife.bind(this);
    }

    private void intiDialog() {
        AlertDialog mAlertDialog = new AlertDialog.Builder(DialogActivity.this)
                .setTitle("检测Acti的生命周期")
                .setIcon(R.mipmap.ic_launcher_round)
                .setMessage("测试弹出Dialog时，按home键Activity的生命周期")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: Click Sure");
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: Click Cancel");
                    }
                }).setNeutralButton("Jump", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: Click Jump");
                    }
                }).create();

        mAlertDialog.setCanceledOnTouchOutside(true);
        mAlertDialog.show();
    }

    private void dialogSetItem() {
        AlertDialog dialog = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setItems(sex,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: dialogSetItem ==" + sex[which]);
                    }
                }).create();
        dialog.setIcon(R.mipmap.microphone);
        dialog.show();

    }

    private void dialogSetSingleChoiceItems() {
        //0表示默认选择第0个
        AlertDialog dialog = new AlertDialog.Builder(this).setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                check = sex[which];
                Log.i(TAG, "onClick: dialogSetSingleChoiceItems ==" + check);
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: setPositiveButton ==" + check);
            }
        }).create();
        dialog.setIcon(R.mipmap.microphone);
        dialog.show();
    }

    private void dialogSetMultiChoiceItems() {
        AlertDialog dialog = new AlertDialog.Builder(this).setMultiChoiceItems(action, falg, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                falg[which] = isChecked;
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < falg.length; i++) {
                    if (falg[i]) {
                        sb = sb.append(action[i]).append("**");
                    }
                }
                Log.i(TAG, "onClick: dialogSetMultiChoiceItems + \n" + sb.toString());
            }
        }).create();
        dialog.setIcon(R.mipmap.microphone);
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @OnClick({R.id.btIntiDialog, R.id.btDialogSetItem, R.id.btDialogSingleChoiceItems,
            R.id.btDialogSetMultiChoiceItems, R.id.btProgressDialogCycler, R.id.btProgressDialogHorizontal,
            R.id.btDataPickerDialog, R.id.btTimePickerDialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btIntiDialog:
                intiDialog();
                break;
            case R.id.btDialogSetItem:
                dialogSetItem();
                break;
            case R.id.btDialogSingleChoiceItems:
                dialogSetSingleChoiceItems();
                break;
            case R.id.btDialogSetMultiChoiceItems:
                dialogSetMultiChoiceItems();
                break;
            case R.id.btProgressDialogCycler:
                setProgressDialogCycler();
                executorService = new ScheduledThreadPoolExecutor(2);
                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 5, TimeUnit.SECONDS);
                break;
            case R.id.btProgressDialogHorizontal:
                setProgressDialogHorizontal();
                timer = new Timer();
                timer.scheduleAtFixedRate(task, 500, 500);
                break;
            case R.id.btDataPickerDialog:
                setDataPickerDialog();
                break;
            case R.id.btTimePickerDialog:
                setTimePickerDialog();
                break;
            default:
                break;
        }
    }

    private void setProgressDialogCycler() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.microphone);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setTitle("圆形进度条式对话框");
        // dimiss监听:销毁对话框，回调setOnDismissListener。
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.i(TAG, "onDismiss: setOnDismissListener");
            }
        });
        //cancel监听:表示隐藏对话框，对话框并不会被销毁。
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.i(TAG, "onCancel: setOnCancelListener");
            }
        });
        /******实际运行中可以发现，系统是先调用onCancel方法，然后再调用onDismiss方法，这也从侧面验证了上面的结论*****/
        progressDialog.setMessage("new一个圆形进度条");
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    private void setProgressDialogHorizontal() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.microphone);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setTitle("水平进度条式对话框");
        // dimiss监听:销毁对话框，回调setOnDismissListener。
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.i(TAG, "onDismiss: setOnDismissListener");
            }
        });
        //cancel监听:表示隐藏对话框，对话框并不会被销毁。
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.i(TAG, "onCancel: setOnCancelListener");
            }
        });
        progressDialog.setMax(100);
        progressDialog.show();
        task = new TimerTask() {
            @Override
            public void run() {
                if (progress <= 100) {
                    progressDialog.incrementProgressBy(1);
                    progress++;
                } else {
                    progressDialog.dismiss();
                }
            }
        };
    }

    private void setDataPickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DAY = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.i(TAG, "onDateSet → 当前选择的时间是：" + year + "" + (month+1) + "" + dayOfMonth);
            }
        }, YEAR, MONTH, DAY);
        dateDialog.show();
    }

    private void setTimePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String newminute = minute + "";
                if(minute < 10){
                    newminute = "0" + newminute;
                }
                Log.i(TAG, "onDateSet → 当前选择的时间是：" + hourOfDay + ":" + newminute);
            }
        }, hour, minute, true);
        timeDialog.show();
    }
}
