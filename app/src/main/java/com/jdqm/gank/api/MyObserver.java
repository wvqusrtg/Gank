package com.jdqm.gank.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jdqm on 2019-07-08.
 */
public abstract class MyObserver<T> implements Observer<T>{

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
