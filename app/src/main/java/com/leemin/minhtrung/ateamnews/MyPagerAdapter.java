package com.leemin.minhtrung.ateamnews;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 5/6/2017.
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context ctx;
    public MyPagerAdapter(Context ctx){
        this.ctx = ctx;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView tView = new TextView(ctx);
        position++;
        tView.setText("Page number: " + position);
        tView.setTextColor(Color.RED);
        tView.setTextSize(20);
        container.addView(tView);
        return tView;
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
