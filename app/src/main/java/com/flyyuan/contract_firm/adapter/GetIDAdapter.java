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
import com.flyyuan.contract_firm.bean.IDBean;

import java.util.List;

/**
 * Created by Yuan on 2017/7/13.
 * class comment:
 */

public class GetIDAdapter extends RecyclerView.Adapter<GetIDAdapter.ViewHolder> {
    Context mContext;
    private List<IDBean> mIDBean;


    public GetIDAdapter(List<IDBean> IDBean , Context context){
        mContext = context;
        mIDBean = IDBean;
    }

    @Override
    public GetIDAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_get_company,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                IDBean idBean = mIDBean.get(position);
                String id = idBean.getId();
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("data",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mContext
                        .getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                editor.putString("ID",id);
                editor.apply();
                id = sharedPreferences.getString("ID","");
                Log.d("Id-----------------",id );
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GetIDAdapter.ViewHolder holder, int position) {
        IDBean idBean = mIDBean.get(position);
        holder.textView.setText(idBean.getName().toString());
    }

    @Override
    public int getItemCount() {return mIDBean.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.get_company_item);
        }
    }
}
