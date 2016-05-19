package com.xingshijie.common.base;

import android.app.Fragment;
import android.os.Bundle;

import com.xingshijie.common.image.ImageLoader;

/**
 * Created by Word Xing  on 2016/5/19.
 */
public abstract class BaseFragment extends Fragment {

    private ImageLoader mImageLoader;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageLoader = new ImageLoader(this);
    }
}
