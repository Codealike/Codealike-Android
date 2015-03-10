package com.codealike.android.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.codealike.android.CodealikeApplication;
import com.codealike.android.HeatMapAdapter;
import com.codealike.android.R;
import com.codealike.android.model.UserData;

import java.util.Calendar;
import java.util.Date;

public class DaysOnFireFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_days_on_fire, container, false);
        FragmentActivity activity = getActivity();

        UserData userData = ((CodealikeApplication)activity.getApplication()).getUserData();

        Integer[] thisYearDaysOnFire = getNumberOfDaysOnFireForThisYear(userData);

        GridView gridView = (GridView) v.findViewById(R.id.gridView);

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int size = ((dm.widthPixels / 2) / 4);

        GridView.MarginLayoutParams mlp = (GridView.MarginLayoutParams)gridView.getLayoutParams();
        mlp.setMargins(dm.widthPixels / 4, 20, dm.widthPixels / 4, 20);

        gridView.setAdapter(new HeatMapAdapter(activity, size, thisYearDaysOnFire));

        return v;
    }

    private Integer[] getNumberOfDaysOnFireForThisYear(UserData userData)
    {
        Integer[] result = new Integer[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < userData.DaysOnFire.size(); i++)
        {
            Date date = userData.DaysOnFire.get(i);
            calendar.setTime(date);
            //TODO: remove this hardcoded year filter.
            if(calendar.get(Calendar.YEAR) == 2014)
            {
                int month = calendar.get(Calendar.MONTH);
                int index = month - 1;
                result[month] += 1;
            }
        }

        return result;
    }

    public static DaysOnFireFragment newInstance(String text) {

        DaysOnFireFragment f = new DaysOnFireFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
