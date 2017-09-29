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
import com.flyyuan.contract_firm.bean.ContractModelBean;
import com.flyyuan.contract_firm.bean.PendingBean;

import java.util.List;

/**
 * Created by Yuan on 2017/9/21.
 * class comment:
 */

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {
    private List<PendingBean> pendingBeanList;
    private final Context context;

    public PendingAdapter(List<PendingBean> pendingList, Context ActivityContext) {
        pendingBeanList = pendingList;
        context = ActivityContext;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView aName;
        TextView bName;
        TextView createData;
        TextView updataData;


        public ViewHolder(View itemView) {
            super(itemView);
            aName = (TextView) itemView.findViewById(R.id.Aname);
            bName = (TextView) itemView.findViewById(R.id.Bname);
            createData = (TextView) itemView.findViewById(R.id.createData);
            updataData = (TextView) itemView.findViewById(R.id.updataData);
//            modelPDFWeb = (WebView) itemView.findViewById(R.id.model_PDF_web);
        }
    }

    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PendingBean pendingBean = pendingBeanList.get(position);
        holder.aName.setText("合同发起人：" + pendingBean.getPartyAuserName());
        holder.bName.setText("合同待签署人：" + pendingBean.getPartyBUserName());
        holder.createData.setText("创建时间：" + pendingBean.getCreateDate());
        holder.updataData.setText("更新时间：" + pendingBean.getUpdateDate());
    }


    @Override
    public int getItemCount() {
        return pendingBeanList.size();
    }
}
