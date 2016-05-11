package com.xingshijie.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static butterknife.ButterKnife.findById;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_butter_knife)
    Button mBtnButterKnife;
    @BindView(R.id.btn_retrofit)
    Button mBtnRetrofit;

    Button mBtnGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBtnGlide = findById(this, R.id.btn_glide);
    }

    @OnClick({R.id.btn_butter_knife, R.id.btn_retrofit, R.id.btn_glide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_butter_knife:
                break;
            case R.id.btn_retrofit:
                break;
            case R.id.btn_glide:
                break;
        }
    }
}
