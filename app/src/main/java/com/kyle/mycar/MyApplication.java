package com.kyle.mycar;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * 捕获全局异常
 * Created by Zhang on 2017/4/19.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                //捕获到异常后的操作
                Logger.e(throwable.toString());

                System.exit(0);
            }
        });

    }
}