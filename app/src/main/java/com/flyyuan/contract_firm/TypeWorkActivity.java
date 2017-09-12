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

import com.flyyuan.contract_firm.adapter.GetTypeAdapter;
import com.flyyuan.contract_firm.adapter.MyDividerItemDecoration;
import com.flyyuan.contract_firm.bean.TypeWorkBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class TypeWorkActivity extends AppCompatActivity {

    private String sessionid;
    private LaborURL laborURL;
    private RecyclerView typeWorkRv;
    private GetTypeAdapter getTypeAdapter;
    private List<TypeWorkBean> mTypeWork = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_work);
        typeWorkRv = (RecyclerView) findViewById(R.id.type_Work_Rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        typeWorkRv.setLayoutManager(layoutManager);
        typeWorkRv.addItemDecoration(new MyDividerItemDecoration(TypeWorkActivity.this,LinearLayoutManager.VERTICAL));

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_typeWork);
        toolbar.setTitle("选择职务");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //在SP中获取sessionid
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(TypeWorkActivity.this);
        sessionid = prefs.getString("sessionid","");
        getTypeWork();
    }

    private void getTypeWork() {
        OkGo.get(laborURL.get_type_work_URL+  ";JSESSIONID=" +sessionid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("TypeWork:",s);
                        parseJSONWithGSON(s);
                    }
                });
    }

    private void parseJSONWithGSON(String s){
        Gson gson = new Gson();
        mTypeWork = gson.fromJson(s,new TypeToken<List<TypeWorkBean>>()
        {}.getType());
        getTypeAdapter  = new GetTypeAdapter(mTypeWork,TypeWorkActivity.this);
        typeWorkRv.setAdapter(getTypeAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
