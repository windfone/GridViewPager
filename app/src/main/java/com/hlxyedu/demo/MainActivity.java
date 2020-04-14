package com.hlxyedu.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private LinearLayout point_view;
    private ViewPager viewpager;
    private MyPagerAdapter myPagerAdapter;

    private int totalPage;//总的页数
    private int mPageSize = 8;//每页显示的最大数量
    private int mCurrentIndex = 0;// 当前选中的是第几页

    private List<String> datas = Arrays.asList("哈哈", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    private List<Boolean> checkList = Arrays.asList(false, false, false, false, false, false, false, false, false, false);

    private GridView gridView;
    private List<View> gridList;

    private MyContentAdapter myContentAdapter;
    private List<MyContentAdapter> myContentAdapterLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        point_view = findViewById(R.id.point_view);
        viewpager = findViewById(R.id.viewpager);


        totalPage = (int) Math.ceil(datas.size() * 1.0 / mPageSize);
        myContentAdapterLists = new ArrayList<>();
        gridList = new ArrayList<>();

        for (int i = 0; i < totalPage; i++) {
            gridView = (GridView) getLayoutInflater().inflate(R.layout.gridview, viewpager, false);
            myContentAdapter = new MyContentAdapter(MainActivity.this, i, mPageSize);
            myContentAdapter.setData(datas, checkList);
            gridView.setAdapter(myContentAdapter);
            myContentAdapterLists.add(myContentAdapter);
            gridList.add(gridView);
        }

        myPagerAdapter = new MyPagerAdapter(MainActivity.this);
        myPagerAdapter.setGridList(gridList);
        viewpager.setAdapter(myPagerAdapter);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(myContentAdapter.getSelectData().toString());
            }
        });



        getData();
        point_view.getChildAt(0).setEnabled(true);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                point_view.getChildAt(mCurrentIndex).setEnabled(false);
                point_view.getChildAt(position).setEnabled(true);
                mCurrentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getData() {
        View view;
        for (int i = 0; i < totalPage; i++) {
            //创建底部指示器(小圆点)
            view = new View(MainActivity.this);
            view.setBackgroundResource(R.drawable.background);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            //设置间隔
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            //添加到LinearLayout
            point_view.addView(view, layoutParams);
        }
    }

}
