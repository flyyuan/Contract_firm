package com.flyyuan.contract_firm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.flyyuan.contract_firm.adapter.GetCompanyAdapter;
import com.flyyuan.contract_firm.adapter.MyDividerItemDecoration;
import com.flyyuan.contract_firm.bean.CompanyBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GetCompanyActivity extends AppCompatActivity {
    LaborURL laborURL;
    private String sessionid;
    private RecyclerView getCompanyRy;
    private GetCompanyAdapter getCompanyAdapter;
    private List<CompanyBean> mCompanyBeen = new ArrayList<>();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_company);
        getCompanyRy = (RecyclerView) findViewById(R.id.get_company_ry);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getCompanyRy.setLayoutManager(layoutManager);
        getCompanyRy.addItemDecoration(new MyDividerItemDecoration(GetCompanyActivity.this,LinearLayoutManager.VERTICAL));

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_company);
        toolbar.setTitle("选择公司");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //在SP中获取sessionid
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(GetCompanyActivity.this);
        sessionid = prefs.getString("sessionid","");
        getCompany();
    }

    private void getCompany() {
        OkGo.get(laborURL.get_company_URL +  ";JSESSIONID=" +sessionid)
                .params("type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("Company:",s);
                        parseJSONWithGSON(s);
                    }
                });
    }

    private void parseJSONWithGSON(String s){
        Gson gson = new Gson();
        mCompanyBeen = gson.fromJson(s,new TypeToken<List<CompanyBean>>()
        {}.getType());
        getCompanyAdapter = new GetCompanyAdapter(mCompanyBeen,GetCompanyActivity.this);
        getCompanyRy.setAdapter(getCompanyAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}
