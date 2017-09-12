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

import com.flyyuan.contract_firm.adapter.GetIDAdapter;
import com.flyyuan.contract_firm.adapter.MyDividerItemDecoration;
import com.flyyuan.contract_firm.bean.IDBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GetIdActivity extends AppCompatActivity {
    private String sessionid;
    private LaborURL laborURL;
    private RecyclerView getID;
    private GetIDAdapter getIDAdapter;
    private List<IDBean> mIDBean = new ArrayList<>();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_id);
        getID = (RecyclerView) findViewById(R.id.Get_ID_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getID.setLayoutManager(layoutManager);
        getID.addItemDecoration(new MyDividerItemDecoration(GetIdActivity.this,LinearLayoutManager.VERTICAL));

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle("选择员工");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //在SP中获取sessionid
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(GetIdActivity.this);
        sessionid = prefs.getString("sessionid","");
        getID();
    }

    private void getID() {
        OkGo.get(laborURL.get_id_URL+  ";JSESSIONID=" +sessionid)
                .params("type","3")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("ID:", s);
                        parseJSONWithGSON(s);
                    }
                });
    }

    private void parseJSONWithGSON(String s){
        Gson gson = new Gson();
        mIDBean = gson.fromJson(s,new TypeToken<List<IDBean>>()
        {}.getType());
        getIDAdapter = new GetIDAdapter(mIDBean,GetIdActivity.this);
        getID.setAdapter(getIDAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}
