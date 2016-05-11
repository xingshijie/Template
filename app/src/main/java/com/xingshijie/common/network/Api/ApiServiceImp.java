package com.xingshijie.common.network.Api;

import com.xingshijie.common.network.coverter.FastJsonConverterFactory;
import com.xingshijie.common.network.model.Turing;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Word Xing  on 2016/5/11.
 */
public class ApiServiceImp {

    private static ApiService apiService;

    static {
        //第一种方法,修改request
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("apikey", "917a61932ebac3e62a55ff16133b5ed8")
//                        .removeHeader("User-Agent")
//                        .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                        .build();
                return chain.proceed(request);
            }
        }).build();
        //第二种方法
        OkHttpClient client2 = new OkHttpClient(){
            @Override
            public okhttp3.Call newCall(Request request) {
                Request request1 = request.newBuilder().build();
                return super.newCall(request);
            }
        };

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

    public static Call<String> qrCode(){
        return apiService.qrCode(10,"woshixingshijie");
    }
}
