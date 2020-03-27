package com.mk.coronapi.api;

public interface CoronaApiCallback<B> {
    void onResponse(B result, ApiResponse response);


    void onError(Throwable t);
}
