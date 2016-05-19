package com.xingshijie.common.base;

import android.app.Activity;
import android.app.Application;

import java.lang.ref.WeakReference;
import java.util.Stack;

import static com.xingshijie.template.utils.LogUtils.makeLogTag;

/**
 * Created by Word Xing  on 2016/5/17.
 */
public class BaseApplication extends Application{

    protected static BaseApplication mInstance;
    private static final String TAG = makeLogTag(BaseApplication.class);

    private Stack<WeakReference<Activity>> mActivityStack = new Stack<WeakReference<Activity>>();


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        LeakCanary.install(this);

    }
    /**
     * app每次启动都会初始化一次这里的信息，如果这里的信息没有初始化好，就会使用上次保存的信息
     * 优化：apk里可以保存这样的一份文件，第一次启动时从里面读出来
     */
    private void initOnce(){

    }
}
