package com.xingshijie.common;

import android.os.Build;

import com.xingshijie.template.App;
import com.xingshijie.template.BuildConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 把最后一次发生的异常保存在外部文件里
 * Created by Word Xing  on 2016/6/3.
 */
public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        extractLogToFile(ex);
    }

    private String extractLogToFile(Throwable ex) {
        String model = Build.MODEL;
        if (!model.startsWith(Build.MANUFACTURER))
            model = Build.MANUFACTURER + " " + model;

        // Make file name - file must be saved to external storage or it wont be readable by
        // the email app.
        String fileName = new Date().toString().replace(" ", "-") + ".txt";

        // Extract to file.
        InputStreamReader reader;
        FileWriter writer;
        try {
            File file = new File(App.getApp().getExternalCacheDir(), "exception.txt");
            // For Android 4.0 and earlier, you will get all app's log output, so filter it to
            // mostly limit it to your app's output.  In later versions, the filtering isn't needed.
            String cmd = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) ?
                    "logcat -d -v time MyApp:v dalvikvm:v System.err:v *:s" :
                    "logcat -d -v time";

            // get input stream
            Process process = Runtime.getRuntime().exec(cmd);
            reader = new InputStreamReader(process.getInputStream());

            // write output stream
            writer = new FileWriter(file);
            writer.write("Android version: " + Build.VERSION.SDK_INT + "\n");
            writer.write("Device: " + model + "\n");
            writer.write("App version: " + BuildConfig.VERSION_CODE + "\n");
            ex.printStackTrace(new PrintWriter(writer));

            char[] buffer = new char[10000];
            do {
                int n = reader.read(buffer, 0, buffer.length);
                if (n == -1)
                    break;
                writer.write(buffer, 0, n);
            } while (true);

            reader.close();
            writer.close();
        } catch (IOException e) {
            return null;
        }

        return fileName;
    }
}
