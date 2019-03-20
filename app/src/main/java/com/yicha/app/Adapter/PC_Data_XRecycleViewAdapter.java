package com.yicha.app.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.FlowBox.Product;
import com.yicha.app.FlowBox.ProductAdapter;
import com.yicha.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PC_Data_XRecycleViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    protected List<Product.Classify> classifies;
    private Context context;
    private Product.Classify.Des des;
    private LayoutInflater layoutInflater;
    private List<Product.Classify.Des> lisedes;
    private List<PC_DatalisEntity.OptionsBean> list = new ArrayList();
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;
    private HashMap<String, String> stringStringHashMap = new HashMap();

    public PC_Data_XRecycleViewAdapter(Context paramContext) {
        context = paramContext;
        layoutInflater = LayoutInflater.from(paramContext);
    }

    public void addData(List<PC_DatalisEntity.OptionsBean> paramList) {
        list.addAll(paramList);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return list.size();
    }

    public int getItemViewType(int paramInt) {
        return paramInt;
    }

    public void onAttachedToRecyclerView(RecyclerView paramRecyclerView) {
        super.onAttachedToRecyclerView(paramRecyclerView);
        recyclerView = paramRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder  viewHolder=null;
        View itemView2=layoutInflater.inflate(R.layout.xrv_pcdata_item,parent,false);
        itemView2.setOnClickListener(this);
        viewHolder=new MerchantParticularsImageViewHolder(itemView2);
        return viewHolder;
    }
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int paramInt) {
        MerchantParticularsImageViewHolder merchantParticularsImageViewHolder ;
        merchantParticularsImageViewHolder= (MerchantParticularsImageViewHolder) holder;
        merchantParticularsImageViewHolder.title.setText((list.get(paramInt)).getName());
        lisedes = new ArrayList();
        int i = 0;
        while (i < (list.get(paramInt)).getValue().size()) {
            classifies = new ArrayList();
            des = new Product.Classify.Des((list.get(paramInt)).getValue().get(i));
            lisedes.add(des);
            i += 1;
        }
        Product.Classify localClassify = new Product.Classify((list.get(paramInt)).getName(), lisedes);
        classifies.add(localClassify);
        merchantParticularsImageViewHolder.recycleview.setLayoutManager(new LinearLayoutManager(context));
        productAdapter = new ProductAdapter(context);
        productAdapter.addData(classifies);
        merchantParticularsImageViewHolder.recycleview.setAdapter(productAdapter);
        productAdapter.setOnRecyclerviewItemClickListener((parent, view, position, name) -> {
            stringStringHashMap.put(list.get(paramInt).getParam(), name);
            onRecyclerviewItemClickListener.onItemClick(paramInt, stringStringHashMap);
        });
    }

    public void onClick(View paramView) {
        if ((onRecyclerviewItemClickListener != null) && (recyclerView != null)) {
            int i = recyclerView.getChildAdapterPosition(paramView);
            onRecyclerviewItemClickListener.onRecyclerviewItemClick(recyclerView, paramView, i);
        }
    }



    public void setData(List<PC_DatalisEntity.OptionsBean> paramList) {
        list.clear();
        addData(paramList);
    }

    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener paramOnRecyclerviewItemClickListener) {
        onRecyclerviewItemClickListener = paramOnRecyclerviewItemClickListener;
    }

    class MerchantParticularsImageViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recycleview;
        TextView title;

        public MerchantParticularsImageViewHolder(View paramView) {
            super(paramView);
            title = ((TextView) paramView.findViewById(R.id.title));
            recycleview = ((RecyclerView) paramView.findViewById(R.id.recycleView));
        }
    }

    public  interface OnRecyclerviewItemClickListener {
         void onItemClick(int paramInt, HashMap<String, String> paramHashMap);

         void onRecyclerviewItemClick(RecyclerView paramRecyclerView, View paramView, int paramInt);
    }
}
