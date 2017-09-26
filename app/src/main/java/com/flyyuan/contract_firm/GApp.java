package com.flyyuan.contract_firm;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;

import okhttp3.OkHttpClient;

/**
 * Created by Yuan on 2017/9/20.
 * class comment:
 */

public class GApp extends Application {
    private static GApp instance;

    @Override
    public void onCreate(){
        super.onCreate();
        instance =this;
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        OkGo.getInstance().setOkHttpClient(builder.build());
    }

    public static GApp getInstance(){
        return instance;
    }
}