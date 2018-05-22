package com.haoyue.auxiliary;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by a on 2017/7/20.
 * 收集crash日志
 */

public class ImproveCrashHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    public static ImproveCrashHandler INSTANCE;

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINA);
    private ImproveCrashHandler(){

    }

//    private static class Holder{
//        private static final ImproveCrashHandler  INSTANCE=new ImproveCrashHandler();
//    }

    public static ImproveCrashHandler getInstance(){
        if (INSTANCE == null) {
            synchronized (ImproveCrashHandler.class){
                if (INSTANCE == null) {
                    INSTANCE=new ImproveCrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    public void initVariable(Context context){
        mContext=context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder ( );
        //异常写入stringBuilder
        Writer writer = new StringWriter( );
        PrintWriter printWriter = new PrintWriter ( writer );
        throwable.printStackTrace ( printWriter );
        //异常原因写入stringBuilder
        Throwable cause = throwable.getCause ( );
        if ( cause != null ) {
            cause.printStackTrace ( printWriter );
        }
        printWriter.close ( );
        String result = writer.toString ( );
        stringBuilder.append ( result +"\n");

        Log.i(getClass().getSimpleName(), "uncaughtException: result ===" + result);

        postService(result);

        save(stringBuilder.toString());
    }

    /**
     * 上传到服务器
     */
    private void postService(String log){
       // TODO: 2017/7/20  
    }

    /**
     * 保存本地
     */
    private void save(String log){
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "LOG" + "-" + time + "-" + timestamp
                    + ".txt";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                Log.e("tags",Environment.getExternalStorageDirectory().getAbsolutePath());
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator + "CrashLog";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(path + File.separator + fileName);
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(log.getBytes());
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            Log.e("", "an error occured while writing file...", e);
        }
    }

}
