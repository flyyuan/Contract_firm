package com.flyyuan.contract_firm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.flyyuan.contract_firm.R;
import com.flyyuan.contract_firm.adapter.AlreadyLaunchedAdapter;
import com.flyyuan.contract_firm.adapter.AlreadySignAdapter;
import com.flyyuan.contract_firm.bean.AlreadyLaunchedBean;
import com.flyyuan.contract_firm.bean.AlreadySignBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class AlreadySignActivity extends AppCompatActivity {

    private static final String TAG = "AlreadySign";
    RecyclerView recyclerView;
    List<AlreadySignBean> alreadySignBeanList;
    private LoadingDialog ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_sign);
        initUI();
        AlreadySignJSon();
    }

    private void initUI(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.already_sign_toolbar);
        toolbar.setTitle("已签约");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //加载圈
        ld = new LoadingDialog(this);
        ld.setLoadingText("加载中")
                .setSuccessText("加载成功")//显示加载成功时的文字
                .setFailedText("加载失败")
                .show();
    }

    private void AlreadySignJSon() {
        OkGo.<String>get(LaborURL.alreadySign_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("AlreadySign----->", response.body());
                        initRecyclerView(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        ld.loadFailed();
                        super.onError(response);
                    }
                });

    }

    private void initRecyclerView(String jsonData){
        recyclerView = (RecyclerView) findViewById(R.id.already_sign_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //解析合同模板JSON
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(jsonData).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<AlreadySignBean> alreadySignArrayList = new ArrayList<>();
        for (JsonElement already : jsonArray){
            AlreadySignBean alreadySignBean = gson.fromJson(already,AlreadySignBean.class);
            alreadySignArrayList.add(alreadySignBean);

        }
        Log.d(TAG, "initRecyclerView: "+alreadySignArrayList.get(0).getLdhtTemplate().getName());
        //将数据传入RecyclerView adapter
        alreadySignBeanList = alreadySignArrayList;
        AlreadySignAdapter adapter = new AlreadySignAdapter(alreadySignBeanList,AlreadySignActivity.this);
        recyclerView.setAdapter(adapter);
        ld.loadSuccess();

    }

}
