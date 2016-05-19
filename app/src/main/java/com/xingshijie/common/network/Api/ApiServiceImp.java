package com.xingshijie.common.network.Api;

import com.xingshijie.common.network.BaseCallBackWrapper;
import com.xingshijie.common.network.BaseCallback;
import com.xingshijie.common.network.DefaultHttpClient;
import com.xingshijie.common.network.coverter.FastJsonConverterFactory;
import com.xingshijie.common.network.model.QrCode;
import com.xingshijie.common.network.model.Result;
import com.xingshijie.common.network.model.Turing;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

import static com.xingshijie.template.utils.LogUtils.LOGD;
import static com.xingshijie.template.utils.LogUtils.makeLogTag;

/**
 * Created by Word Xing  on 2016/5/11.
 */
public class ApiServiceImp {

    private static final String TAG = makeLogTag(ApiServiceImp.class);
    private static ApiService apiService;

    static {
        //第一种方法,修改request
        OkHttpClient client = DefaultHttpClient.INSTANCE.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("apikey", "917a61932ebac3e62a55ff16133b5ed8")
                        .build();
                LOGD(TAG, "--> url: " + request.url());
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.baidu.com/")
                .addConverterFactory(new FastJsonConverterFactory())
                .callFactory(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static Call<Turing> turing(String info){
        return apiService.turing("879a6cb3afb84dbf4fc84a1df2ab7319", info, "eb2edb736", "917a61932ebac3e62a55ff16133b5ed8");
    }

    public static Call<QrCode> qrCode(){
        return apiService.qrCode(10,"woshixingshijie");
    }

    public static Call<Result<Turing>> demo(String str, BaseCallback<Turing> cb){
        if(str == null){
            cb.onFailure();
        }
        Call<Result<Turing>> call = apiService.demo();
        call.enqueue(new BaseCallBackWrapper<Turing>(cb){
            @Override
            public void onSuccess(Turing turing) {
                turing.setUrl("");
                super.onSuccess(turing);
            }
        });
        return call;
    }
}
