package com.flyyuan.contract_firm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Yuan on 2017/9/10.
 * class comment:
 */

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new mehdi.sakout.aboutpage.AboutPage(this)
                .isRTL(false)
                .setDescription("天软科技出品")
                .addGroup("Connect with us")
                .addEmail("740004544@qq.com")
                .addWebsite("https://github.com/flyyuan")
                .create();

        setContentView(aboutPage);
    }
}