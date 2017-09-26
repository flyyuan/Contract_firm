package com.flyyuan.contract_firm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyyuan.contract_firm.R;
import com.flyyuan.contract_firm.activity.ContractInformationActivity;
import com.flyyuan.contract_firm.activity.ModelPDFActivity;
import com.flyyuan.contract_firm.bean.ContractModelBean;

import java.util.List;

/**
 * Created by Yuan on 2017/9/21.
 * class comment:
 */

public class NewContracAdapter extends RecyclerView.Adapter<NewContracAdapter.ViewHolder> {
    private List<ContractModelBean> modelBeanList ;
    private final Context context;

    public NewContracAdapter(List<ContractModelBean> modelList, Context ActivityContext) {
        modelBeanList = modelList;
        context = ActivityContext;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView modelName;
        TextView createData;
        TextView updataData;
//        WebView modelPDFWeb;
        public ViewHolder(View itemView) {
            super(itemView);
            modelName = (TextView) itemView.findViewById(R.id.modelname);
            createData = (TextView) itemView.findViewById(R.id.createData);
            updataData = (TextView) itemView.findViewById(R.id.updataData);
//            modelPDFWeb = (WebView) itemView.findViewById(R.id.model_PDF_web);
        }
    }

    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ContractModelBean model = modelBeanList.get(position);
                Intent intent = new Intent(parent.getContext(), ContractInformationActivity.class);
                intent.putExtra("ID",model.getId());
                intent.putExtra("name",model.getName());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContractModelBean contractModelBean = modelBeanList.get(position);
        holder.modelName.setText(contractModelBean.getName());
        holder.createData.setText("创建时间："+contractModelBean.getCreateDate());
        holder.updataData.setText("更新时间："+contractModelBean.getUpdateDate());
//        holder.modelPDFWeb.loadUrl("http://ow365.cn/?i=13726&furl=http://39.108.69.214:8080"+contractModelBean.getUrl());
     }


    @Override
    public int getItemCount() {
        return modelBeanList.size();
    }
}
