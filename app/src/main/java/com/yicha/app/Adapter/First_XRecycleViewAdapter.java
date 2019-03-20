package com.yicha.app.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yicha.app.Entity.MobleListEntity;
import com.yicha.app.Entity.getMobleListEntity;
import com.yicha.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：首页课程 适配器
 * Created by 谢军 on 2018/11/7.
 */

public class First_XRecycleViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private  List<MobleListEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener;
    private int selectedPosition = -1;
    //点击位置
    private int defItem;
    public First_XRecycleViewAdapter(Context context) {
        Log.i("asss","是不是更新了");
        list = new ArrayList<>();
        this.context = context;
        this.layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder  viewHolder=null;
                View itemView2=layoutInflater.inflate(R.layout.item_simple,parent,false);
                itemView2.setOnClickListener(this);
                viewHolder=new MerchantParticularsImageViewHolder(itemView2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MerchantParticularsImageViewHolder merchantParticularsImageViewHolder ;
        merchantParticularsImageViewHolder= (MerchantParticularsImageViewHolder) holder;
        merchantParticularsImageViewHolder.date.setText(list.get(position).getModel());
        //判断当前位置是否为点击位置
        if (selectedPosition == position) {
            //设置选中状态背景色
            merchantParticularsImageViewHolder.date.setBackgroundColor(Color.parseColor("#f0f0f0"));
            merchantParticularsImageViewHolder.date.setTextColor(Color.parseColor("#333333"));
        } else {
            merchantParticularsImageViewHolder.date.setBackgroundColor(Color.TRANSPARENT);
        }
//        Picasso.with(context).load(list.get(position).getFace()).error(R.mipmap.touciang).placeholder(R.mipmap.touciang).into(merchantParticularsImageViewHolder.CirleImageView_HeadPortrait);
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void addData(List<MobleListEntity> datas) {
        this.list.addAll(datas);
        notifyDataSetChanged();
        Log.i("http","走了吗？++"+list.size());
    }
    public void setData(List<MobleListEntity> datas) {
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
            this.onRecyclerviewItemClickListener.onRecyclerviewItemClick(recyclerView, v, (position-1), list.get(position-1).getModel());
        }
    }

    //TODO 设置item单击监听
    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener onRecyclerviewItemClickListener) {
        this.onRecyclerviewItemClickListener = onRecyclerviewItemClickListener;
    }


    //TODO RecycleView的item单击事件需要实现的方法
    public interface OnRecyclerviewItemClickListener {

        void onRecyclerviewItemClick(RecyclerView parent, View view, int position, String name);
    }


    //TODO 自定义帮助类初始化第一套布局
    class MerchantParticularsImageViewHolder extends RecyclerView.ViewHolder{

        TextView date;


        public MerchantParticularsImageViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
