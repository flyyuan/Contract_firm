package com.flyyuan.contract_firm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.flyyuan.contract_firm.R;
import com.flyyuan.contract_firm.adapter.ContractModelAdapter;
import com.flyyuan.contract_firm.adapter.NewContracAdapter;
import com.flyyuan.contract_firm.bean.ContractModelBean;
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

public class NewContractActivity extends AppCompatActivity {

    private List<ContractModelBean> modelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LoadingDialog ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contract);
        initToolbar();
        getModelJSon();
    }

    private void getModelJSon() {
        OkGo.<String>get(LaborURL.model_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("model----->", response.body());
                        initRecyclerView(response.body());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        ld.loadFailed();
                        super.onError(response);
                    }

                });

    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.new_contract_toolbar);
        toolbar.setTitle("选择合同模板");
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
    private void initRecyclerView(String jsonData){
        recyclerView = (RecyclerView) findViewById(R.id.new_model_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //解析合同模板JSON
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(jsonData).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<ContractModelBean> modelBeanList = new ArrayList<>();
        for (JsonElement model : jsonArray){
            ContractModelBean modelBean = gson.fromJson(model,ContractModelBean.class);
            modelBeanList.add(modelBean);

        }
        //将数据传入RecyclerView adapter
        modelList = modelBeanList;
        NewContracAdapter adapter = new NewContracAdapter(modelList,NewContractActivity.this);
        recyclerView.setAdapter(adapter);
        ld.loadSuccess();
    }
    }
