package com.xingshijie.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xingshijie.common.image.ImageLoader;

/**
 * Created by Word Xing  on 2016/5/16.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected ImageLoader mImageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageLoader = new ImageLoader(this);
    }
}
