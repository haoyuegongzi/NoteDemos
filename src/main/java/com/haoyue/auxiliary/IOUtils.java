package com.haoyue.auxiliary;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 作者：chen1 on 2018/2/1 15
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class IOUtils {
    static String TAG = "TAG";
//    String filePath = "D:" + File.separator + "序列化对象.txt";
    /**
     * TODO:对象反序列化（将内容从文档读出）
     * @param filePath
     */
    public static Object operationFileRead(String filePath){
        ObjectInputStream output;
        try{
            output = new ObjectInputStream(new FileInputStream(new File(filePath)));
            Object obj = output.readObject();
            return obj;
        }catch (IOException e){
            Log.i(TAG, "operationIO: IOException == " + e);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "operationIO: ClassNotFoundException == " + e);
        }
        return null;
    }

    /**
     * TODO:对象序列化对（将内容存入文档）
     * @param filePath
     */
    public static void operationFileSave(String filePath, Object obj){
        ObjectOutputStream input;
        try{
            input = new ObjectOutputStream(new FileOutputStream(new File(filePath), true));
            input.writeObject(obj);
            input.close();
        }catch (IOException e){
            Log.i(TAG, "operationFileSave: IOException == " +e);
        }
    }

    /**
     * TODO:打印流（将内容存入文件）
     * @param filePath:文件保存路径
     * @param content：保存的内容
     * @param bool:是否覆盖以前的内容
     */
    public static void operationFilePrint(String filePath, String content, boolean bool){
        PrintStream outa;
        try{
            outa = new PrintStream(new FileOutputStream(new File(filePath), bool));
            outa.println(content);
            outa.close();  // 关闭
        }catch (IOException e){
            Log.i(TAG, "operationFileSave: IOException == " +e);
        }
    }

    /**
     * TODO:字符流输入（将内容从文档读出）
     *      字符流用于字符、字符数组或字符串，包括汉字。
     * @param filePath:文件路径
     */
    public static void charStreamFileRead(String filePath){
        Reader input1;
        try{
            //字符流用于字符、字符数组或字符串，包括汉字。
            input1 = new FileReader(new File(filePath));
            /***开辟char数组空间，读取内容***/
            char j[] = new char[1024];
            // 读取
            int m = input1.read(j);
            input1.close();
            System.out.println(new String(j, 0, m));
        }catch (IOException e){
            Log.i(TAG, "operationFileSave: IOException == " +e);
        }
    }

    /**
     * TODO:字符流输出（将内容存入文档）
     * @param filePath:文件路径
     * @param content：要保存的内容
     * @param b：是否覆盖以前的内容
     */
    public static void charStreamFileSave(String filePath, String content, boolean b){
        try{
            Writer out1 = new FileWriter(new File(filePath),b);
            out1.write(content);// 输出内容
            out1.close(); // 关闭
        }catch (IOException e){
            Log.i(TAG, "operationFileSave: IOException == " +e);
        }
    }

    /**
     * 字节流输入（将内容从文档读出），多用于音频文件、图片、歌曲等多媒体
     * @param filePath
     */
    public static void byteStreamFileRead(String filePath){
        try{
            InputStream inputa = new FileInputStream(new File(filePath));
            byte[] k = new byte[1024];
            int a = inputa.read(k);
            inputa.close();
            System.out.println(new String(k, 0, a));
        }catch (IOException e){
            Log.i(TAG, "operationFileSave: IOException == " +e);
        }
    }

    /**
     * TODO:字节流输出（将内容存入文档）
     * @param filePath:文件路径
     * @param bool：是否覆盖前一次的内容
     * @param Content：写入的内容
     */
    public static void byteStreamFileSave(String filePath, boolean bool, String Content){
        try{
            OutputStream out=new FileOutputStream(new File(filePath),bool);
            byte b[] = Content.getBytes(); // 将字符串变为字节数组
            out.write(b);// 输出内容（写入或存入文件）
            out.close(); // 关闭
        }catch (IOException e){
            Log.i(TAG, "operationFileSave: IOException == " +e);
        }
    }

    /**
     * TODO:将raw中文件复制到内存卡中
     * @param context
     * @param rawResourceId:R.raw.***;R.assets.***
     */
    private void fileSave(Context context, int rawResourceId) {
        String BASE = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        File f = new File(BASE + "/cx/cx.txt");// 创建文件
        if (!f.exists()) {
            //
            try{
                File dir = new File(BASE);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                if (!f.exists()){
                    InputStream is = context.getResources().openRawResource(rawResourceId);
                    FileOutputStream fos = new FileOutputStream(f);
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    while ((count = is.read(buffer)) > 0){
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void openFile(Context context, int rawResourceId, String DB_NAME) {
        final int BUFFER_SIZE = 102400;
        File dir = context.getFilesDir();
        if (!dir.exists()) {
            try {
                dir.mkdirs();// 按照指定的路径创建文件夹
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File file = new File(dir + "/" + DB_NAME);
        //判断文件是否存在，若不存在则执行导入
        if (!file.exists()) {
            try {
                // 在指定的文件夹中创建文件
                file.createNewFile();
                //欲导入的数据库
                InputStream is = context.getResources().openRawResource(rawResourceId);
                FileOutputStream fos = new FileOutputStream(dir + File.separator + DB_NAME);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }
}
