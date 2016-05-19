package com.xingshijie.common.network;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.xingshijie.template.utils.LogUtils.makeLogTag;

/**
 * Created by Word Xing  on 2016/5/18.
 */
public class DefaultHttpClient {

    private static final String TAG = makeLogTag(DefaultHttpClient.class);
    public static final OkHttpClient INSTANCE = getDefaultHttpClient();

    private static OkHttpClient getDefaultHttpClient(){
        //第二种方法
        OkHttpClient client2 = new OkHttpClient(){
            @Override
            public okhttp3.Call newCall(Request request) {
                Request request1 = request.newBuilder().build();
                return super.newCall(request);
            }
        };

        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
//                        .removeHeader("User-Agent")
//                        .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                        .build();
                return chain.proceed(request);
            }
        }).addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                Headers headers = response.headers();
                headers.get("");

                return response;
            }
        }).build();
    }
}
