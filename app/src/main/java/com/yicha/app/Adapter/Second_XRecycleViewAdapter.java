package com.yicha.app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.yicha.app.Entity.HoemPageEntity;
import com.yicha.app.Entity.getMobleListEntity;
import com.yicha.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：首页课程 适配器
 * Created by 谢军 on 2018/11/7.
 */

public class Second_XRecycleViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private  List<getMobleListEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener;
    private int selectedPosition = -1;
    public Second_XRecycleViewAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
        this.layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder  viewHolder=null;
                View itemView2=layoutInflater.inflate(R.layout.xrv_item_simple,parent,false);
                itemView2.setOnClickListener(this);
                viewHolder=new MerchantParticularsImageViewHolder(itemView2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MerchantParticularsImageViewHolder merchantParticularsImageViewHolder ;
        merchantParticularsImageViewHolder= (MerchantParticularsImageViewHolder) holder;
        merchantParticularsImageViewHolder.date.setText(list.get(position).getModelName());
        merchantParticularsImageViewHolder.XuHao.setText((position+1)+"");
//        Picasso.with(context).load(list.get(position).getFace()).error(R.mipmap.touciang).placeholder(R.mipmap.touciang).into(merchantParticularsImageViewHolder.CirleImageView_HeadPortrait);
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void addData(List<getMobleListEntity> datas) {
        this.list.addAll(datas);
        notifyDataSetChanged();
        Log.i("http","走了吗？wewrwr");
    }

    public void setData(List<getMobleListEntity> datas) {
        this.list.clear();
        addData(datas);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * 取出recyclerView
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView=recyclerView;
    }

    @Override
    public void onClick(View v) {
        if (this.onRecyclerviewItemClickListener != null && this.recyclerView != null) {
            int position = this.recyclerView.getChildAdapterPosition(v);
            this.onRecyclerviewItemClickListener.onRecyclerviewItemClick(recyclerView, v, position, list.get(position-1).getId(),list.get(position-1).getModelName());
        }
    }

    //TODO 设置item单击监听
    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener onRecyclerviewItemClickListener) {
        this.onRecyclerviewItemClickListener = onRecyclerviewItemClickListener;
    }


    //TODO RecycleView的item单击事件需要实现的方法
    public interface OnRecyclerviewItemClickListener {

        void onRecyclerviewItemClick(RecyclerView parent, View view, int position,int id,String name);
    }


    //TODO 自定义帮助类初始化第一套布局
    class MerchantParticularsImageViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        TextView XuHao;


        public MerchantParticularsImageViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.text);
            XuHao = (TextView) itemView.findViewById(R.id.XuHao);
        }
    }
}
