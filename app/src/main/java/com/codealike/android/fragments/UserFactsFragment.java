package com.codealike.android.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codealike.android.R;

public class UserFactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_facts, container, false);

        TextView tv = (TextView) v.findViewById(R.id.firstFragment);
        tv.setText(getArguments().getString("msg"));

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
