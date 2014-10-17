package com.codealike.android.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codealike.android.CodealikeApplication;
import com.codealike.android.R;
import com.codealike.android.model.Technology;
import com.codealike.android.model.UserData;
import com.codealike.android.NumberProgressBar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CodeFactsFragment extends Fragment {

    final int TextViewId = 1000;
    final int ProgressId = 2000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_code_facts, container, false);
        FragmentActivity activity = getActivity();

        UserData userData = ((CodealikeApplication)activity.getApplication()).getUserData();

        List<Technology> technologies = new ArrayList<Technology>();
        Technology technologyOther = new Technology();
        technologyOther.Name = "Other";
        for(int i = 0; i < userData.ByTechnologies.size(); i++)
        {
            Technology technology = userData.ByTechnologies.get(i);
            if(technology.Name.equals("Other"))
            {
                technologyOther.Percentage += technology.Percentage;
            }
            else {
                technologies.add(technology);
            }
        }

        technologyOther.Percentage = new BigDecimal(technologyOther.Percentage).setScale(2, RoundingMode.HALF_UP).doubleValue();
        technologies.add(technologyOther);

        Collections.sort(technologies, new Comparator<Technology>() {
            @Override
            public int compare(Technology t1, Technology t2) {
                return t1.Percentage > t2.Percentage ? -1 : (t1.Percentage < t2.Percentage ? 1 : 0);
            }
        });


        RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.layoutCodeFacts);
        Context context = this.getActivity().getApplicationContext();


        for(int i = 0; i < 5; i++)
        {
            TextView textView = getTextView(i, technologies, context, R.id.textViewTitle);
            layout.addView(textView);

            NumberProgressBar progressBar = getNumberProgressBar(i, technologies, context);
            layout.addView(progressBar);
        }

        return v;
    }

    public static CodeFactsFragment newInstance(String text) {

        CodeFactsFragment f = new CodeFactsFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    private TextView getTextView(int index, List<Technology> technologies, Context context, int belowId)
    {
        Technology technology = technologies.get(index);

        //Create text heading
        TextView textView = new TextView(context);
        textView.setId(TextViewId + index);
        textView.setText(technology.Name);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 25, 10, 5);

        if(index == 0) {
            params.addRule(RelativeLayout.BELOW, belowId);
        }
        else {
            params.addRule(RelativeLayout.BELOW, ProgressId + (index - 1));
        }

        //params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //params.addRule(RelativeLayout.ALIGN_PARENT_START);
        textView.setLayoutParams(params);

        textView.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        textView.setTextColor(getResources().getColor(R.color.subtitle));

        return textView;
    }

    private NumberProgressBar getNumberProgressBar(int index, List<Technology> technologies, Context context)
    {
        Technology technology = technologies.get(index);

        NumberProgressBar progressBar = new NumberProgressBar(context, null, R.style.NumberProgressBar_Coding);
        progressBar.setId(ProgressId + index);
        progressBar.setProgress((int)technology.Percentage);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
        params.setMargins(10, 25, 10, 5);
        params.addRule(RelativeLayout.BELOW, TextViewId + index);

        //params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //params.addRule(RelativeLayout.ALIGN_PARENT_START);
        progressBar.setLayoutParams(params);

        progressBar.setUnreachedBarColor(Color.WHITE);
        progressBar.setReachedBarColor(Color.RED);
        progressBar.setReachedBarHeight(50);
        progressBar.setUnreachedBarHeight(0);
        progressBar.setProgressTextColor(Color.RED);

        return progressBar;
    }
}
