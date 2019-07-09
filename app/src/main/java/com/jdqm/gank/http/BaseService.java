package com.jdqm.gank.http;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jdqm on 2019-07-08.
 */
public class BaseService<T> {
    protected static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    protected static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    protected static final String CONTENT_TYPE_STRING = "text/plain; charset=utf-8";

    protected T mBaseServices;

    public BaseService() {
        mBaseServices = (T) RetrofitManager.getInstance().create(getServiceClass());
    }

    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    @SuppressWarnings("unchecked")
    protected <R> Class<R> getServiceClass() {
        return ((Class<R>) ((ParameterizedType) (getClass()
                .getGenericSuperclass())).getActualTypeArguments()[0]);
    }
}
