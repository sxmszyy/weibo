package com.example.hp.weibo;

import android.app.Application;

import cn.smssdk.SMSSDK;

/**
 * 保存我的Appkey和App Secret
 */
public class MyApplication extends Application {

    @Override
    public void onCreate( ) {
        super.onCreate();
        ///初始化SMSSDK
        SMSSDK.initSDK(this,"294b95c9458fc","032b9637aaf6dc9230b7443a7ffc88f1");
    }
}