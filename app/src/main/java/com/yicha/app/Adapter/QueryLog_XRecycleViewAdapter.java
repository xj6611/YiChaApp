package com.yicha.app.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.Entity.QueryLog_Entity;
import com.yicha.app.FlowBox.Product;
import com.yicha.app.FlowBox.ProductAdapter;
import com.yicha.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryLog_XRecycleViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    protected List<QueryLog_Entity> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener;
    private RecyclerView recyclerView;
    private StringBuffer stringBuffer;
    public QueryLog_XRecycleViewAdapter(Context paramContext) {
        list = new ArrayList<>();
        this.context = paramContext;
        layoutInflater = LayoutInflater.from(paramContext);
    }

    public void addData(List<QueryLog_Entity> paramList) {
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
        RecyclerView.ViewHolder viewHolder = null;
        View itemView2 = layoutInflater.inflate(R.layout.xrv_querylog_layout, parent, false);
        itemView2.setOnClickListener(this);
        viewHolder = new MerchantParticularsImageViewHolder(itemView2);
        return viewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int paramInt) {
        MerchantParticularsImageViewHolder merchantParticularsImageViewHolder;
        merchantParticularsImageViewHolder = (MerchantParticularsImageViewHolder) holder;
        stringBuffer = new StringBuffer();
        if (list.get(paramInt).getParamList() != null){
            for (int i = 0; i < list.get(paramInt).getParamList().size(); i++) {
                stringBuffer.append((list.get(paramInt).getParamList().get(i)).getName() + "/" + (list.get(paramInt).getParamList().get(i)).getValue());
            }
        }
        merchantParticularsImageViewHolder.canshu1.setText(stringBuffer.toString());
        merchantParticularsImageViewHolder.canshu2.setText((list.get(paramInt)).getNewlevel() + "成新/" + (list.get(paramInt)).getBuytime());
        merchantParticularsImageViewHolder.time.setText((list.get(paramInt)).getAddtime());
        merchantParticularsImageViewHolder.money.setText((list.get(paramInt)).getPrice() + "元");
        Picasso.with(context).load(list.get(paramInt).getIcon()).into(merchantParticularsImageViewHolder.image);
    }

    public void onClick(View paramView) {
        if ((onRecyclerviewItemClickListener != null) && (recyclerView != null)) {
            int i = recyclerView.getChildAdapterPosition(paramView);
            onRecyclerviewItemClickListener.onRecyclerviewItemClick(recyclerView, paramView, i);
        }
    }


    public void setData(List<QueryLog_Entity> paramList) {
        list.clear();
        addData(paramList);
    }

    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener paramOnRecyclerviewItemClickListener) {
        onRecyclerviewItemClickListener = paramOnRecyclerviewItemClickListener;
    }

    class MerchantParticularsImageViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.image)
        ImageView image;
//        @BindView(R.id.name)
        TextView name;
//        @BindView(R.id.canshu1)
        TextView canshu1;
//        @BindView(R.id.canshu2)
        TextView canshu2;
//        @BindView(R.id.money)
        TextView money;
//        @BindView(R.id.time)
        TextView time;
        public MerchantParticularsImageViewHolder(View paramView) {
            super(paramView);
//            ButterKnife.bind(paramView);
            name = ((TextView) paramView.findViewById(R.id.name));
            canshu1 = ((TextView) paramView.findViewById(R.id.canshu1));
            canshu2 = ((TextView) paramView.findViewById(R.id.canshu2));
            money = ((TextView) paramView.findViewById(R.id.money));
            time = ((TextView) paramView.findViewById(R.id.time));
            image = ((ImageView) paramView.findViewById(R.id.image));
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onItemClick(int paramInt, HashMap<String, String> paramHashMap);

        void onRecyclerviewItemClick(RecyclerView paramRecyclerView, View paramView, int paramInt);
    }
}
