package com.example.buttoon.utils;

public interface MainSinglerInterface {
    public <T> void get(String url,MainSingleCallBack<T> callBack);
}
