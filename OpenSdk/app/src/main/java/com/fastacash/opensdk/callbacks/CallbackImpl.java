package com.fastacash.opensdk.callbacks;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nikhil on 10/20/2015.
 */
public interface CallbackImpl {

    public abstract void success(Object data);

    public abstract void failure(String errorMessage);
}
