package com.xingshijie.common.network.Api;

import com.xingshijie.common.network.RetrofitUtil;
import com.xingshijie.common.network.model.Turing;

import retrofit2.Call;

/**
 * Created by Word Xing  on 2016/5/11.
 */
public class ApiServiceImp {

    private static final ApiService apiService = RetrofitUtil.defaultRetrofit.create(ApiService.class);

    public static Call<Turing> turing(String info){
        return apiService.turing("879a6cb3afb84dbf4fc84a1df2ab7319", info, "eb2edb736", "917a61932ebac3e62a55ff16133b5ed8");
    }
}
