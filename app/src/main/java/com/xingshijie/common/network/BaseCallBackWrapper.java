package com.xingshijie.common.network;

import com.xingshijie.common.network.model.Result;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Proxying implementation of BaseCallBack that simply delegates all of its calls to
 * another callback.  Can be subclassed to modify behavior without changing
 * the original CallBack.
 */
public class BaseCallBackWrapper<T> extends BaseCallback<T> {

    private BaseCallback<T> delegate;

    public BaseCallBackWrapper(BaseCallback<T> cb) {
        delegate = cb;
}

    @Override
    public void onSuccess(T t) {
        delegate.onSuccess(t);
    }

    @Override
    public void onFailure() {
        delegate.onFailure();
    }

    @Override
    protected void errorHandler(Call<Result<T>> call, Throwable t, Response<Result<T>> response) {
        delegate.errorHandler(call, t, response);
    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {
        delegate.onFailure();
    }

    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        delegate.onResponse(call, response);
    }
}
