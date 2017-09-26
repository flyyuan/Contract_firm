package com.flyyuan.contract_firm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.flyyuan.contract_firm.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textViewIntroductions;

    //定义图标数组
    private int[] imageRes = {
            R.mipmap.contract,
            R.mipmap.example,
            R.mipmap.wait_contract,
            R.mipmap.already_get,
            R.mipmap.wait_look,
            R.mipmap.already_contract,
            R.mipmap.seal
    };

    //定义图标下方的名称数组
    private String[] name = {
            "合同模板",
            "新建合同",
            "待签约",
            "待审核",
            "已发起",
            "已签约",
            "图章",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("企业版");
        setSupportActionBar(toolbar);




        textViewIntroductions = (TextView) findViewById(R.id.introductions);
        textViewIntroductions.setText("        谷歌是一家位于美国的跨国科技企业，业务包括互联网搜索、云计算、广告技术等，同时开发并提供大量基于互联网的产品与服务.");

        //九宫格菜单
        GridView gridview = (GridView) findViewById(R.id.homemenu);
        int length = imageRes.length;

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.homemenu_item,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.img_homemenuitem, R.id.txt_homemenuitem});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,name[position],Toast.LENGTH_LONG).show();
                switch (position){
                    case 0: startActivity(new Intent(MainActivity.this,ContractModelActivity.class));
                        break;
                    case 1:startActivity(new Intent(MainActivity.this,NewContractActivity.class));
                        break;
                }
            }
        });
    }
    public void aboutPage(View view){
        Intent intent = new Intent(MainActivity.this,AboutPage.class);
        MainActivity.this.startActivity(intent);
    }
    public void HRtribune(View view){
        Intent intent = new Intent(MainActivity.this,HRtribuneActivity.class);
        MainActivity.this.startActivity(intent);
    }
    public void HRfound(View view){
        Intent intent = new Intent(MainActivity.this,HRFoundActivity.class);
        MainActivity.this.startActivity(intent);
    }
    public void HRclass(View view){
        Intent intent = new Intent(MainActivity.this,HRClassActivity.class);
        MainActivity.this.startActivity(intent);
    }
    public void exitAPP(View view){
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        cookieStore.removeAllCookie();
        finish();
    }
}
