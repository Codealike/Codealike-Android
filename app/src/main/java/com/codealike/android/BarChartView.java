package com.codealike.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codealike.android.model.Technology;

import java.util.ArrayList;
import java.util.List;

public class BarChartView extends RelativeLayout {

    final int TextViewId = 1000;
    final int ProgressId = 2000;

    private List<BarChartElement> elements;

    private int layoutBelow;

    public void setLayoutBelow(int layoutBelow)
    {
        this.layoutBelow = layoutBelow;
    }

    public int getLayoutBelow()
    {
        return this.layoutBelow;
    }

    public BarChartView(Context context) {
        super(context);
        this.elements = new ArrayList<BarChartElement>();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if(this.getLayoutBelow() > 0)
        {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, this.layoutBelow);
        }

        for(int i = 0; i < this.elements.size(); i++)
        {
            TextView textView = getTextView(i);
            this.addView(textView);

            NumberProgressBar progressBar = getNumberProgressBar(i);
            this.addView(progressBar);
        }
    }

    public BarChartView(Context context, List<BarChartElement> elements) {
        this(context);

        this.elements = elements;
    }

    private TextView getTextView(int index)
    {
        BarChartElement element = this.elements.get(index);

        //Create text heading
        TextView textView = new TextView(this.getContext());
        textView.setId(TextViewId + index);
        textView.setText(element.getName());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 25, 10, 5);

        if(index > 0) {
            params.addRule(RelativeLayout.BELOW, ProgressId + (index - 1));
        }

        textView.setLayoutParams(params);

        textView.setTextAppearance(this.getContext(), android.R.style.TextAppearance_Medium);
        textView.setTextColor(getResources().getColor(R.color.subtitle));

        return textView;
    }

    private NumberProgressBar getNumberProgressBar(int index)
    {
        BarChartElement element = this.elements.get(index);

        NumberProgressBar progressBar = new NumberProgressBar(this.getContext(), null, R.style.NumberProgressBar);
        progressBar.setId(ProgressId + index);
        progressBar.setProgress((int)element.getPercentage());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
        params.setMargins(10, 25, 10, 5);
        params.addRule(RelativeLayout.BELOW, TextViewId + index);

        progressBar.setLayoutParams(params);

        progressBar.setUnreachedBarColor(Color.WHITE);
        progressBar.setReachedBarColor(element.getColor());
        progressBar.setReachedBarHeight(50);
        progressBar.setUnreachedBarHeight(0);
        progressBar.setProgressTextColor(element.getColor());

        return progressBar;
    }
}
