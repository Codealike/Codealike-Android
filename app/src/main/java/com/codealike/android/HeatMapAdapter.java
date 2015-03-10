package com.codealike.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

public class HeatMapAdapter extends BaseAdapter {

    private Context context;
    private final Integer[] onFireDays;
    private final int size;

    public HeatMapAdapter(Context context, int size, Integer[] onFireDays)
    {
        this.context = context;
        this.onFireDays = onFireDays;
        this.size = size;
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

        //GridView grid = (GridView)parent;
        //int size = grid.getRequestedColumnWidth();

        View heatMapItem;

        if(convertView == null)
        {
            heatMapItem = inflater.inflate(R.layout.heat_map_item, null);
            LinearLayout layout = (LinearLayout)heatMapItem.findViewById(R.id.itemLayout);
            layout.setLayoutParams(new LinearLayout.LayoutParams(this.size, this.size));
            //Button button = (Button)heatMapItem.findViewById(R.id.button);
            //button.setWidth(size);
            //button.setHeight(size);
            //button.refreshDrawableState();

            //button.setText("");
        }
        else
        {
            heatMapItem = (View) convertView;
        }

        return  heatMapItem;
    }
}
