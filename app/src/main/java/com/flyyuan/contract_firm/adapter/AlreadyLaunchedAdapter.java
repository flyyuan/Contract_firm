package com.flyyuan.contract_firm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyyuan.contract_firm.R;
import com.flyyuan.contract_firm.bean.AlreadyLaunchedBean;

import java.util.List;


/**
 * Created by Yuan on 2017/9/27.
 * class comment:
 */

public class AlreadyLaunchedAdapter extends RecyclerView.Adapter<AlreadyLaunchedAdapter.ViewHolder> {
private List<AlreadyLaunchedBean> alreadyLaunchedBeanList;
private final Context context;

public AlreadyLaunchedAdapter(List<AlreadyLaunchedBean> alreadyLaunchedList, Context ActivityContext) {
        alreadyLaunchedBeanList = alreadyLaunchedList;
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

    public AlreadyLaunchedAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_item, parent, false);
        final AlreadyLaunchedAdapter.ViewHolder holder = new AlreadyLaunchedAdapter.ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(AlreadyLaunchedAdapter.ViewHolder holder, int position) {
        AlreadyLaunchedBean alreadyLaunchedBean = alreadyLaunchedBeanList.get(position);
        holder.aName.setText("合同发起人：" + alreadyLaunchedBean.getPartyAuserName());
        holder.bName.setText("合同已签署人：" + alreadyLaunchedBean.getPartyBUserName());
        holder.createData.setText("创建时间：" + alreadyLaunchedBean.getCreateDate());
        holder.updataData.setText("更新时间：" + alreadyLaunchedBean.getUpdateDate());
    }


    @Override
    public int getItemCount() {
        return alreadyLaunchedBeanList.size();
    }
}
