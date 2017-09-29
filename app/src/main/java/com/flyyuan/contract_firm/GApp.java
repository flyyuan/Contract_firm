package com.flyyuan.contract_firm;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

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
        StyleManager s = new StyleManager();

//在这里调用方法设置s的属性
//code here...
        s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);

        LoadingDialog.initStyle(s);
    }

    public static GApp getInstance(){
        return instance;
    }
}