package com.hlxyedu.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;


public class MyContentAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private List<Boolean> checkList;
    private int mIndex;//页数下标，表示第几页，从0开始
    private int mPagerSize;//每页显示的最大数量
//    private List<String> selectData;


    public MyContentAdapter(Context context, int mIndex, int mPagerSize) {
        this.context = context;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
    }


    /*public void setData(List<String> data, List<String> selectData) {
        this.data = data;
        this.selectData =selectData;
        notifyDataSetChanged();
    }*/

    public void setData(List<String> data,List<Boolean> checks) {
        this.data = data;
        this.checkList = checks;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (data.size() - mIndex * mPagerSize);
    }

    @Override
    public Object getItem(int i) {
        return data.get(i + mIndex * mPagerSize);
    }

    @Override
    public long getItemId(int i) {
        return i + mIndex * mPagerSize;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_content, null);
            holder = new Holder();
            holder.tv = view.findViewById(R.id.tv);
            holder.checkBox = view.findViewById(R.id.check_box);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        final int pos = i + mIndex*mPagerSize;
        holder.tv.setText(data.get(pos));
        holder.checkBox.setChecked(checkList.get(pos));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkList.set(pos,b);
            }
        });
        return view;
    }

    class Holder {
        public TextView tv;
        public CheckBox checkBox;

    }

    public List<Boolean> getSelectData() {
        return checkList;
    }
}
