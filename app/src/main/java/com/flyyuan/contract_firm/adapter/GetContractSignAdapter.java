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
import com.flyyuan.contract_firm.bean.ContractSignBean;

import java.util.List;

/**
 * Created by Yuan on 2017/7/13.
 * class comment:
 */

public class GetContractSignAdapter  extends RecyclerView.Adapter<GetContractSignAdapter.ViewHolder>{

    Context mContext;
    private List<ContractSignBean> mContractSign;


    public GetContractSignAdapter(List<ContractSignBean> contractSignBeen , Context context){
        mContext = context;
        mContractSign = contractSignBeen;
    }

    @Override
    public GetContractSignAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_get_company,parent,false);
        final GetContractSignAdapter.ViewHolder holder = new GetContractSignAdapter.ViewHolder(view);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                ContractSignBean contractSignBean = mContractSign.get(position);
                //点击之后获取数据，同时把数据放入SP
                String contractSignBeanId = contractSignBean.getId();
                String contractSignCreateDate = contractSignBean.getCreateDate();
                String contractSignUpdateDate = contractSignBean.getUpdateDate();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("data",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mContext
                        .getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                editor.putString("contractSignBeanId",contractSignBeanId );
                editor.putString("contractSignCreateDate",contractSignCreateDate );
                editor.putString("contractSignUpdateDate",contractSignUpdateDate);
                editor.commit();
                editor.apply();
                Log.d("Id-----------------", sharedPreferences.getString("contractSignBeanId","")
                        +"--------"+sharedPreferences.getString("contractSignCreateDate","")
                        +"--------"+sharedPreferences.getString("contractSignUpdateDate","")
                );
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GetContractSignAdapter.ViewHolder holder, int position) {
        ContractSignBean contractSignBean = mContractSign.get(position);
        holder.textView.setText(contractSignBean.getName());
    }

    @Override
    public int getItemCount() {return mContractSign.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.get_company_item);
        }
    }
}
