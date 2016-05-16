package com.xingshijie.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xingshijie.common.network.Api.ApiServiceImp;
import com.xingshijie.common.network.model.QrCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static butterknife.ButterKnife.findById;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_butter_knife)
    Button mBtnButterKnife;
    @BindView(R.id.btn_retrofit)
    Button mBtnRetrofit;

    Button mBtnGlide;
    @BindView(R.id.iv_qr_code)
    ImageView mIvQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBtnGlide = findById(this, R.id.btn_glide);

        ApiServiceImp.qrCode().enqueue(new Callback<QrCode>() {
            @Override
            public void onResponse(Call<QrCode> call, Response<QrCode> response) {
                Glide.with(MainActivity.this).load(response.body().getUrl()).into(mIvQrCode);
            }

            @Override
            public void onFailure(Call<QrCode> call, Throwable t) {

            }
        });
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
