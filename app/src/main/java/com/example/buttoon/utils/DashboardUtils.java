package com.example.buttoon.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardUtils implements MainSinglerInterface{

    private static DashboardUtils dashboardUtils;
    private final ApiService apiService;

    public static DashboardUtils getDashboardUtils() {
        if (dashboardUtils==null){
            synchronized (DashboardUtils.class){
                if (dashboardUtils==null){
                    dashboardUtils=new DashboardUtils();
                }
            }
        }
        return dashboardUtils;
    }

    public DashboardUtils() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(URLcontract.DashUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        apiService = build.create(ApiService.class);
    }

    @Override
    public <T> void get(String url, MainSingleCallBack<T> callBack) {
        apiService.getdata(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type actualTypeArgument = actualTypeArguments[0];
                            Gson gson = new Gson();
                            T o = gson.fromJson(string, actualTypeArgument);
                            callBack.onScuess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFeil("网络异常"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
