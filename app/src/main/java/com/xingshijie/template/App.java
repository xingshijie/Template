package com.xingshijie.template;

import android.os.Process;

import com.xingshijie.common.base.BaseApplication;
import com.xingshijie.template.utils.ProcessUtils;

/**
 * Created by Word Xing  on 2016/5/19.
 */
public class App extends BaseApplication{

    public static App getApp() {
        return (App) mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.APPLICATION_ID.equals(ProcessUtils.getProcessName(this, Process.myPid()))) {
            //TODO 表示当前app主进程
        } else {
            //TODO 表示其他的服务开启的进程
        }
    }


}
