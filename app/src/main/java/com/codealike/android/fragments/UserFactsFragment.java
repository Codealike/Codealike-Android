package com.codealike.android.fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.codealike.android.BarChartElement;
import com.codealike.android.BarChartView;
import com.codealike.android.CodealikeApplication;
import com.codealike.android.R;
import com.codealike.android.model.UserData;
import com.codealike.android.NumberProgressBar;

import java.util.ArrayList;
import java.util.List;

public class UserFactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_facts, container, false);

        FragmentActivity activity = getActivity();
        final UserData userData = ((CodealikeApplication)activity.getApplication()).getUserData();

        List<BarChartElement> barChartElements = new ArrayList<BarChartElement>() {{
            add(new BarChartElement("Coding", (int)userData.ActivityPercentage.Coding, Color.parseColor("#8CC63F")));
            add(new BarChartElement("Debugging", (int)userData.ActivityPercentage.Debugging, Color.parseColor("#F7A200")));
            add(new BarChartElement("Building", (int)userData.ActivityPercentage.Building, Color.parseColor("#E01BAB")));
        }};

        RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.layoutUserFacts);
        Context context = this.getActivity().getApplicationContext();

        BarChartView barChartView = new BarChartView(context, barChartElements);
        barChartView.setLayoutBelow(R.id.textViewTitle);
        barChartView.setWillNotDraw(false);

        layout.addView(barChartView);

        return v;
    }

    public static UserFactsFragment newInstance(String text) {

        UserFactsFragment f = new UserFactsFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
