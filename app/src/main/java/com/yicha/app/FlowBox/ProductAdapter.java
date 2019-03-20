package com.yicha.app.FlowBox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yicha.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 谢军 on 19/1/4.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private static final String TAG = ProductAdapter.class.getSimpleName();
    private List<Product.Classify> classifies;
    private Context context;
    private RecyclerView recyclerView;
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener;
    private LayoutInflater layoutInflater;
    private Boolean aBoolean = false;
    public ProductAdapter(Context context) {
        this.classifies = new ArrayList<>();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    /**
     *      刷新数据
     * @param data
     */
    public   void updata(List<Product.Classify> data){
        this.classifies=data;
        aBoolean = true;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param datas
     */
    public void addData(List<Product.Classify> datas){
        this.classifies.addAll(datas);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View itemView2 = layoutInflater.inflate(R.layout.product_item, parent, false);
        viewHolder = new ProductHolder(itemView2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProductHolder productHolder = (ProductHolder) holder;
        Product.Classify classify = classifies.get(position);
        final FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
//        productHolder.title.setText(classify.title);
        if (aBoolean==false){
            productHolder.des.addItemDecoration(new SpaceItemDecoration(dp2px(10)));
        }
        productHolder.des.setLayoutManager(flowLayoutManager);
        productHolder.des.setAdapter(new FlowAdapter(classify.des));
    }

    public String getTitle(int position) {
        return classifies.get(position).title;
    }

    @Override
    public int getItemCount() {
        return classifies.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private RecyclerView des;

        public ProductHolder(View itemView) {
            super(itemView);
            des = (RecyclerView) itemView.findViewById(R.id.des);
        }
    }

    class FlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Product.Classify.Des> list;
        private Product.Classify.Des selectDes;

        public FlowAdapter(List<Product.Classify.Des> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(context, R.layout.flow_item, null));
        }

        @SuppressLint("NewApi")
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            TextView textView = ((MyHolder) holder).text;

            final Product.Classify.Des des = list.get(position);
            if (des.isSelect) {
                textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_select_back));
//                textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_back));
            } else {
                textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_back));
            }
            textView.setText(des.des);
            holder.itemView.setOnClickListener(v -> {
                onRecyclerviewItemClickListener.onSearchRecordsItemClick(recyclerView, v, position,des.des);
                if (des != selectDes) {
                    if (selectDes != null) {
                        selectDes.isSelect = false;
                    }
                }
                des.isSelect = true;
                selectDes = des;
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            private TextView text;

            public MyHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.flow_text);
            }
        }
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
    /**
     * 取出recyclerView
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    //TODO 设置item单击监听
    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener onRecyclerviewItemClickListener) {
        this.onRecyclerviewItemClickListener = onRecyclerviewItemClickListener;
    }


    //TODO RecycleView的item单击事件需要实现的方法
    public interface OnRecyclerviewItemClickListener {

        void onSearchRecordsItemClick(RecyclerView parent, View view, int position, String name);
    }
}
