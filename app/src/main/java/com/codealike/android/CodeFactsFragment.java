package com.codealike.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CodeFactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        TextView tv = (TextView) v.findViewById(R.id.secondFragment);
        tv.setText(getArguments().getString("msg"));

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
