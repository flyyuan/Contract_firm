package com.flyyuan.contract_firm.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.flyyuan.contract_firm.R;
import com.flyyuan.contract_firm.bean.TypeWorkBean;

import java.util.List;

/**
 * Created by Yuan on 2017/7/13.
 * class comment:
 */

public class GetTypeAdapter extends RecyclerView.Adapter<GetTypeAdapter.ViewHolder>{


    Context mContext;
    private List<TypeWorkBean> mTypeWork;


    public GetTypeAdapter(List<TypeWorkBean> typeWorkBeen , Context context){
        mContext = context;
        mTypeWork = typeWorkBeen;
    }

    @Override
    public GetTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_get_company,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                TypeWorkBean typeWorkBean = mTypeWork.get(position);
                String typeWorkBeanId = typeWorkBean.getId();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("data",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mContext
                        .getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                editor.putString("typeWorkBeanId",typeWorkBeanId);
                editor.apply();
                typeWorkBeanId = sharedPreferences.getString("typeWorkBeanId","");
                Log.d("Id-----------------",typeWorkBeanId );
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GetTypeAdapter.ViewHolder holder, int position) {
        TypeWorkBean typeWorkBean = mTypeWork.get(position);
        holder.textView.setText(typeWorkBean.getName().toString());
    }

    @Override
    public int getItemCount() {return mTypeWork.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.get_company_item);
        }
    }

}
