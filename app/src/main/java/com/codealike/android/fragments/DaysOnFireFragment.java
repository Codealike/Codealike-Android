package com.codealike.android.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.codealike.android.CodealikeApplication;
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

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(activity,
                android.R.layout.simple_list_item_1, thisYearDaysOnFire);

        gridView.setAdapter(adapter);
        //gridView.getChildAt(0).setBackgroundColor(Color.BLUE);

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
