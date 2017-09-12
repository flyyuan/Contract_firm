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
import com.flyyuan.contract_firm.bean.CompanyBean;

import java.util.List;

/**
 * Created by Yuan on 2017/7/13.
 * class comment:
 */

public class GetCompanyAdapter extends RecyclerView.Adapter<GetCompanyAdapter.ViewHolder>{

    Context mContext;
    private List<CompanyBean> mCompany;


    public GetCompanyAdapter(List<CompanyBean> Company , Context context){
        mContext = context;
        mCompany = Company;
    }

    @Override
    public GetCompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_get_company,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                CompanyBean companyBean = mCompany.get(position);
                String companyID = companyBean.getId();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("data",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mContext
                        .getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                editor.putString("companyID",companyID);
                editor.apply();
                sharedPreferences.getString("companyID","");
                Log.d("Id-----------------", companyID);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GetCompanyAdapter.ViewHolder holder, int position) {
        CompanyBean companyBean = mCompany.get(position);
        holder.textView.setText(companyBean.getName().toString());
    }

    @Override
    public int getItemCount() {return mCompany.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.get_company_item);
        }
    }
}
