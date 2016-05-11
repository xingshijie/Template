package com.xingshijie.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xingshijie.common.network.coverter.GsonConverterFactory;
import com.xingshijie.template.BuildConfig;

import retrofit2.Retrofit;

/**
 * Created by Word Xing  on 2016/5/10.
 */
public class RetrofitUtil {

    public static final Retrofit defaultRetrofit = getRetrofit("http://apis.baidu.com/");

    private static Retrofit getRetrofit(String url){
        Gson gson;
        if(BuildConfig.DEBUG){
            gson = new GsonBuilder().setPrettyPrinting().create();
        } else {
            gson = new Gson();
        }
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
