package com.codealike.android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DaysOnFireFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_third, container, false);

        TextView tv = (TextView) v.findViewById(R.id.thirdFragment);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static DaysOnFireFragment newInstance(String text) {

        DaysOnFireFragment f = new DaysOnFireFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
