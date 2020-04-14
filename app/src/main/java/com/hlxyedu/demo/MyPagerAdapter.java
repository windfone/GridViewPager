package com.hlxyedu.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;


public class MyPagerAdapter extends PagerAdapter {
    private Context context;
    private List<View> gridList;

    public MyPagerAdapter(Context context) {
        this.context = context;
    }

    public void setGridList(List<View> gridList) {
        this.gridList = gridList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return gridList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(gridList.get(position));
        return gridList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        super.destroyItem(container, position, object);
    }
}
