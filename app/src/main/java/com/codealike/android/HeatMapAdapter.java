package com.codealike.android;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.HashMap;

public class HeatMapAdapter extends BaseAdapter {

    private Context context;
    private final Integer[] onFireDays;
    private final int size;
    private final String[] colors;
    private final String[] months;


    public HeatMapAdapter(Context context, int size, Integer[] onFireDays)
    {
        this.context = context;
        this.onFireDays = onFireDays;
        this.size = size;
        this.colors = new String[]
        {
                "#EEEEEE",
                "#87C161",
                "#82BD5E",
                "#7EB95A",
                "#79B457",
                "#74B054",
                "#70AC50",
                "#6BA74D",
                "#66A34A",
                "#629F46",
                "#5D9A43",
                "#58963F",
                "#54923C",
                "#4F8D39",
                "#4A8935",
                "#468532",
                "#41802F",
                "#3C7C2B",
                "#387828",
                "#337325",
                "#2E6F21",
                "#2A6B1E",
                "#25661A",
                "#206217",
                "#1B5E14",
                "#175910",
                "#12550D",
                "#0D510A",
                "#094C06",
                "#044803",
                "#004400",
                "#004400"
        };

        this.months = new String[] { "J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"};
    }

    @Override
    public int getCount() {
        return onFireDays.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View heatMapItem;

        heatMapItem = inflater.inflate(R.layout.heat_map_item, null);
        LinearLayout layout = (LinearLayout)heatMapItem.findViewById(R.id.itemLayout);
        layout.setLayoutParams(new LinearLayout.LayoutParams(this.size, this.size));
        Button button = (Button)heatMapItem.findViewById(R.id.button);

        int daysOnFire = this.onFireDays[position];
        String color = this.colors[daysOnFire];
        button.setBackgroundColor(Color.parseColor(color));
        button.setText(months[position]);

        return  heatMapItem;
    }
}
