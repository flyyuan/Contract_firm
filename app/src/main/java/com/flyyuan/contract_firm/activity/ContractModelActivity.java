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
import com.flyyuan.contract_firm.bean.ContractModelBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import java.util.ArrayList;
import java.util.List;

public class ContractModelActivity extends AppCompatActivity {

    private List<ContractModelBean> modelList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_model);
//        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
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
               });
                
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.contractmodel_toolbar);
        toolbar.setTitle("合同模板");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initRecyclerView(String jsonData){
        recyclerView = (RecyclerView) findViewById(R.id.model_list);
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
        ContractModelAdapter adapter = new ContractModelAdapter(modelList,ContractModelActivity.this);
        recyclerView.setAdapter(adapter);

    }

    //解析JSON数组
//    private void parseJSONWithGSON(String jsonData){
//        JsonParser parser = new JsonParser();
//        JsonArray jsonArray = parser.parse(jsonData).getAsJsonArray();
//        Gson gson = new Gson();
//       ArrayList<ContractModelBean> modelBeanList = new ArrayList<>();
//        for (JsonElement model : jsonArray){
//            ContractModelBean modelBean = gson.fromJson(model,ContractModelBean.class);
//            modelBeanList.add(modelBean);
//
//        }
//        modelList = modelBeanList;
//    }

}
