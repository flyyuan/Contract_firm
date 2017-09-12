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

import com.flyyuan.contract_firm.adapter.GetContractSignAdapter;
import com.flyyuan.contract_firm.adapter.MyDividerItemDecoration;
import com.flyyuan.contract_firm.bean.ContractSignBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GetContractSignActivity extends AppCompatActivity {

    LaborURL laborURL;
    private String sessionid;
    RecyclerView getContractSign;
    GetContractSignAdapter getContractSignAdapter;
    private List<ContractSignBean> mContractSignBean = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_contract_sign);
        getContractSign = (RecyclerView) findViewById(R.id.get_contractSign_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getContractSign.setLayoutManager(layoutManager);
        getContractSign.addItemDecoration(new MyDividerItemDecoration(GetContractSignActivity.this,LinearLayoutManager.VERTICAL));
        //在SP中获取sessionid
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(GetContractSignActivity.this);
        sessionid = prefs.getString("sessionid","");
        getContractSign();

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_contractSign);
        toolbar.setTitle("合同模板");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getContractSign() {
        OkGo.get(laborURL.get_contractSign_URL +  ";JSESSIONID=" +sessionid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("ContractSign:", s);
                        parseJSONWithGSON(s);
                    }
                });
    }

    private void parseJSONWithGSON(String s){
        Gson gson = new Gson();
        mContractSignBean = gson.fromJson(s,new TypeToken<List<ContractSignBean>>()
        {}.getType());
        getContractSignAdapter = new GetContractSignAdapter(mContractSignBean,GetContractSignActivity.this);
        getContractSign.setAdapter(getContractSignAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
