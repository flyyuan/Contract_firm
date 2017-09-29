package com.flyyuan.contract_firm.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyyuan.contract_firm.R;
import com.flyyuan.contract_firm.R2;
import com.flyyuan.contract_firm.adapter.NewContracAdapter;
import com.flyyuan.contract_firm.bean.AUserContractInfoBean;
import com.flyyuan.contract_firm.bean.ContractModelBean;
import com.flyyuan.contract_firm.network.LaborURL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContractInformationActivity extends AppCompatActivity implements View.OnClickListener,OnDateSetListener {
    private static String userAid = null;
    private static final String TAG = "ContractInfomation";
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @BindView(R2.id.checkphonenumber)
    MaterialEditText cherckphonenumber;

    //资料输入框
    @BindView(R2.id.company) MaterialEditText company;
    @BindView(R2.id.ecclass) MaterialEditText ecclass;
    @BindView(R2.id.company_preson) MaterialEditText company_preson;
    @BindView(R2.id.company_add) MaterialEditText company_add;
    @BindView(R2.id.company_mailcode) MaterialEditText company_mailcode;
    @BindView(R2.id.contract_class) MaterialEditText contract_class;
    @BindView(R2.id.contract_description) MaterialEditText contract_description;
    @BindView(R2.id.job_description) MaterialEditText job_description;
    @BindView(R2.id.job_addition) MaterialEditText job_addition;
    @BindView(R2.id.working_time) MaterialEditText working_time;
    @BindView(R2.id.month_money) MaterialEditText month_money;
    @BindView(R2.id.add_work_money) MaterialEditText add_work_money;
    @BindView(R2.id.must_preson) MaterialEditText must_preson;
    @BindView(R2.id.must_preson_phone) MaterialEditText must_preson_phone;
    @BindView(R2.id.must_preson_add) MaterialEditText must_preson_add;
    @BindView(R2.id.something) MaterialEditText something;
    @BindView(R2.id.punish) MaterialEditText punish;
    @BindView(R2.id.punish_yeas) MaterialEditText punish_yeas;

    @BindView(R2.id.date_start) AppCompatButton dateStart;
    @BindView(R2.id.date_close) AppCompatButton dateClose;
    @BindView(R2.id.date_checkmoney) AppCompatButton checkmoney;
    @BindView(R2.id.title) TextView title;

    //使用TimePickerId区分是哪个TimePickerDialog
    int TimePickerId = 1;
    private String startDate;
    private String closeData;

    //获取输入信息
    private String companyName;
    private String ecclassName1;
    private String companyPresonName;
    private String companyAddName;
    private String companyMailCodeName;
    private String contractClassName;
    private String contractDescriptionName;
    private String jobAdditionName;
    private String jobDescriptionName;
    private String workingTimeName;
    private String monthMoney;
    private String addWorkMoney;
    private String mustPreson;
    private String mustPresonPhone;
    private String partybid;
    private String checkmoneyData;
    private String mustPresonAdd;
    private String somethingText;
    private String punishOne;
    private String punishYeas;
    private String modelId;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_contract_information);
        ButterKnife.bind(this);
        initToolbar();
        initUserInfo();
    }



    private void getContractinfo() {
        companyName = company.getText().toString();
        ecclassName1 = ecclass.getText().toString();
        companyPresonName = company_preson.getText().toString();
        companyAddName = company_add.getText().toString();
        companyMailCodeName = company_mailcode.getText().toString();
        contractClassName = contract_class.getText().toString();
        contractDescriptionName = contract_description.getText().toString();
        jobAdditionName = job_addition.getText().toString();
        jobDescriptionName = job_description.getText().toString();
        workingTimeName = working_time.getText().toString();
        monthMoney = month_money.getText().toString();
        addWorkMoney = add_work_money.getText().toString();
        mustPreson = must_preson.getText().toString();
        mustPresonPhone = must_preson_phone.getText().toString();
        mustPresonAdd = must_preson_add.getText().toString();
        somethingText = something.getText().toString();
        punishOne = punish.getText().toString();
        punishYeas = punish_yeas.getText().toString();
    }


    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.information_toolbar);
        toolbar.setTitle("填写合同信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //从选择模板中获取甲方信息
    private void initUserInfo(){
        Intent intent = getIntent();
        Log.d(TAG, "UserAid:"+intent.getStringExtra("ID"));
        userAid = intent.getStringExtra("ID");
        Log.d(TAG, "contractName"+intent.getStringExtra("name"));
            try{
            OkGo.<String>get(LaborURL.AuserInfo_URL)
                    .params("id", userAid)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.d(TAG, "contractInformation:----->"+response.body());
                            //异常1：contractInformation:----->{"code":"4","desc":"没有操作权限"}
                            //异常2: 404
                            parserJSON(response.body());
                        }
                    });
        }catch(Exception exception){
            Toast.makeText(this,"合同模板出现问题，请联系模板作者",Toast.LENGTH_LONG);
        }
    }

    //解析AUserId的json
    private void parserJSON(String jsonData){
        Gson gson = new Gson();
        AUserContractInfoBean aUserContractInfoBean = gson.fromJson(jsonData,AUserContractInfoBean.class);
        Log.d(TAG, "AUserId: "+aUserContractInfoBean.getResultData().getPartyaid());
        Log.d(TAG, "AUserName "+aUserContractInfoBean.getResultData().getPartyAuserName());
        Log.d(TAG, "ContractName: "+aUserContractInfoBean.getResultData().getLdhtTemplate().getName());
        title.setText("已选模板:"+aUserContractInfoBean.getResultData().getLdhtTemplate().getName());
        Log.d(TAG, "modelId: "+aUserContractInfoBean.getResultData().getLdhtTemplate().getId());
        modelId = aUserContractInfoBean.getResultData().getLdhtTemplate().getId();
    }

    public void checkphone(View view){
        phone = cherckphonenumber.getText().toString();
        OkGo.<String>post(LaborURL.checkPhone_URL)
                .params("partyBPhone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: "+response.body());
                        partybid = response.body();
                        if (partybid.length()>17) {
                            Log.d(TAG, partybid.substring(13, 45));
                            partybid = partybid.substring(13, 45);
                            Toast.makeText(getApplicationContext(),"该手机号码检验成功",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"该手机号码未注册",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //TimePicker
    public void startTimePicker(View view){
        timePickerDialog = new TimePickerDialog.Builder()
                .setTitleStringId("合同起始日期")
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(R.color.colorPrimary)
                .setCallBack(ContractInformationActivity.this)
                .build();
        timePickerDialog.show(getSupportFragmentManager(),"year_month_day");
        TimePickerId =1;

    }

    public void closeTimePicker(View view){
        timePickerDialog = new TimePickerDialog.Builder()
                .setTitleStringId("合同截止日期")
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(R.color.colorPrimary)
                .setCallBack(ContractInformationActivity.this)
                .build();
        timePickerDialog.show(getSupportFragmentManager(),"year_month_day");
        TimePickerId =2;
    }

    public void checkMoneyTimePicker(View view){
        timePickerDialog = new TimePickerDialog.Builder()
                .setTitleStringId("核算上月工资日")
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(R.color.colorPrimary)
                .setCallBack(ContractInformationActivity.this)
                .build();
        timePickerDialog.show(getSupportFragmentManager(),"year_month_day");
        TimePickerId =3;

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        if (TimePickerId == 1){
            String date = getDateToString(millseconds);
            Log.d(TAG, "startDate: "+date);
            startDate = date;
            dateStart.setText("合同起始日期："+date);
        }
        else if (TimePickerId == 2){
            String date = getDateToString(millseconds);
            Log.d(TAG, "closeDate: "+date);
            closeData = date;
            dateClose.setText("合同起始日期:"+date);
        }else if(TimePickerId ==3){
            String date = getDateToString(millseconds);
            Log.d(TAG, "checkmoneyDate: "+date);
            checkmoneyData = date;
            checkmoney.setText("核算上月工资日:"+date);
        }
        else {
            Log.d(TAG, "onDateSet:TimePicker错误");
        }
        
        }
    

    private String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onClick(View v) {

    }

    //保存合同
    public void postSaveContract(View view){
        getContractinfo();
        Log.e(TAG, "postSaveContract: "+companyName);
        OkGo.<String>post(LaborURL.postSaveContract_URL)
                .params("ldhtTemplate.id",modelId)
                .params("partyaid",userAid)
                .params("partybid",partybid)
                .params("contractSignStatus","0")
                .params("partyBPhone",phone)
                .params("partyAName",companyName)
                .params("partyAType",ecclassName1)
                .params("partyAuserName",companyPresonName)
                .params("partyACorrespondenceAddress",companyAddName)
                .params("partyAPostal",companyMailCodeName)
                .params("contractdeadlinetype",contractClassName)
                .params("deadlinestarttime",startDate)
                .params("deadlineendtime",closeData)
                .params("deadlineenddes",contractDescriptionName)
                .params("partybworkdepart",jobDescriptionName)
                .params("partybplace",jobAdditionName)
                .params("worktimetype",workingTimeName)
                .params("wegebymonth",monthMoney)
                .params("overTimePayByMoth",addWorkMoney)
                .params("payrollday",checkmoneyData)
                .params("lossByOne",punishOne)
                .params("lossByYear",punishYeas)
                .params("attachmentDes",somethingText)
                .params("emergencyContact",mustPreson)
                .params("contactAddress",mustPresonAdd)
                .params("partyAPhone",mustPresonPhone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: "+response.body());
                        Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(getApplicationContext(),"网络连接出现问题",Toast.LENGTH_LONG).show();
                    }
                });
    }

    //直接发起合同
    public void postNewContract(View view){
        getContractinfo();
        OkGo.<String>post(LaborURL.postSaveContract_URL)
                .params("ldhtTemplate.id",modelId)
                .params("partyaid",userAid)
                .params("partybid",partybid)
                .params("contractSignStatus","1")
                .params("partyBPhone",phone)
                .params("partyAName",companyName)
                .params("partyAType",ecclassName1)
                .params("partyAuserName",companyPresonName)
                .params("partyACorrespondenceAddress",companyAddName)
                .params("partyAPostal",companyMailCodeName)
                .params("contractdeadlinetype",contractClassName)
                .params("deadlinestarttime",startDate)
                .params("deadlineendtime",closeData)
                .params("deadlineenddes",contractDescriptionName)
                .params("partybworkdepart",jobDescriptionName)
                .params("partybplace",jobAdditionName)
                .params("worktimetype",workingTimeName)
                .params("wegebymonth",monthMoney)
                .params("overTimePayByMoth",addWorkMoney)
                .params("payrollday",checkmoneyData)
                .params("lossByOne",punishOne)
                .params("lossByYear",punishYeas)
                .params("attachmentDes",somethingText)
                .params("emergencyContact",mustPreson)
                .params("contactAddress",mustPresonAdd)
                .params("partyAPhone",mustPresonPhone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: "+response.body());
                        Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(getApplicationContext(),"网络连接出现问题",Toast.LENGTH_LONG).show();
                    }
                });
    }

}
