package com.yicha.app.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yicha.app.Fragment.HomePageContentFragment;
import com.yicha.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYY on 2018/11/18.
 */

public class FirstAdapter  extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> mFirstItemList;
    //点击位置
    private int selectedPosition = -1;
    private int defItem;

    //设置点击位置给Adapter
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public FirstAdapter(Context context,List<String> mFirstItemList) {
        this.mFirstItemList = mFirstItemList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mFirstItemList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_simple, null);
            viewHolder.date = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //判断当前位置是否为点击位置
        if (selectedPosition == position) {
            //设置选中状态背景色
            viewHolder.date.setBackgroundColor(Color.parseColor("#f0f0f0"));
            viewHolder.date.setTextColor(Color.parseColor("#333333"));
        } else {
            viewHolder.date.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.date.setText(mFirstItemList.get(position));
        return convertView;
    }
      class ViewHolder {
        TextView date;
    }
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }
}