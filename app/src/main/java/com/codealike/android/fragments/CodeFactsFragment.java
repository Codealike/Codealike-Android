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

import com.codealike.android.BarChartElement;
import com.codealike.android.BarChartView;
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

    private int[] technologyColors = new int[] {
        Color.parseColor("#045FB4"),
        Color.parseColor("#FA5882"),
        Color.parseColor("#00CC99"),
        Color.parseColor("#D0A9F5"),
        Color.parseColor("#F7BE81")
    };

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

        List<BarChartElement> barChartElements = new ArrayList<BarChartElement>();
        for(int i = 0; i < 5; i++) {
            Technology technology = technologies.get(i);
            int color = this.technologyColors[i];
            barChartElements.add(new BarChartElement(technology.Name, (int)technology.Percentage, color));
        }

        RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.layoutCodeFacts);
        Context context = this.getActivity().getApplicationContext();

        BarChartView barChartView = new BarChartView(context, barChartElements);
        barChartView.setLayoutBelow(R.id.textViewTitle);
        barChartView.setWillNotDraw(false);

        layout.addView(barChartView);

        return v;
    }

    public static CodeFactsFragment newInstance(String text) {

        CodeFactsFragment f = new CodeFactsFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
