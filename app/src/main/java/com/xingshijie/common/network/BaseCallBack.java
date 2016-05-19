package com.xingshijie.common.network;

import com.xingshijie.common.network.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Word Xing  on 2016/5/18.
 */
public abstract class BaseCallback<T> implements Callback<Result<T>> {

    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        Result<T> result = response.body();
        if(result != null && result.getRet() == Result.RESULT_SUCCESS){
            onSuccess(result.getData());
        } else {
            errorHandler(call, null, response);
            onFailure();
        }
    }

    protected void errorHandler(Call<Result<T>> call, Throwable t, Response<Result<T>> response){
        //TODO show toast
    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {
        errorHandler(call, t, null);
        onFailure();
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure();

}
