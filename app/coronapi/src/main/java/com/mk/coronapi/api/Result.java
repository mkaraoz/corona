package com.mk.coronapi.api;

public class Result<R>
{
    public final int responseCode;
    public final R body;

    public Result(int responseCode, R body)
    {
        this.responseCode = responseCode;
        this.body = body;
    }
}
