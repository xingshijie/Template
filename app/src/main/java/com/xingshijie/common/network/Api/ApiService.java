package com.xingshijie.common.network.Api;

import com.xingshijie.common.network.model.QrCode;
import com.xingshijie.common.network.model.Result;
import com.xingshijie.common.network.model.Turing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Word Xing  on 2016/5/10.
 */

interface ApiService {

    @GET("turing/turing/turing")
    Call<Turing> turing(@Query("key") String key, @Query("info") String string, @Query("userid") String userId, @Header("apikey") String apiKey);

    @GET("3023/qr/qrcode")
    Call<QrCode> qrCode(@Query("size") int size, @Query("qr") String qr);

    @GET("")
    Call<Result<Turing>> demo();
}
