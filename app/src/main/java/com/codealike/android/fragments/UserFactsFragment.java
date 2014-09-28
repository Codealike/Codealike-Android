package com.codealike.android.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.codealike.android.CodealikeApplication;
import com.codealike.android.R;
import com.codealike.android.model.UserData;
import com.daimajia.numberprogressbar.NumberProgressBar;

public class UserFactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_facts, container, false);
        FragmentActivity activity = getActivity();

        UserData userData = ((CodealikeApplication)activity.getApplication()).getUserData();

        NumberProgressBar codingProgressBar = (NumberProgressBar)v.findViewById(R.id.progressCoding);
        codingProgressBar.setProgress((int)userData.ActivityPercentage.Coding);

        NumberProgressBar debuggingProgressBar = (NumberProgressBar)v.findViewById(R.id.progressDebugging);
        debuggingProgressBar.setProgress((int)userData.ActivityPercentage.Debugging);

        NumberProgressBar buildingProgressBar = (NumberProgressBar)v.findViewById(R.id.progressBuilding);
        buildingProgressBar.setProgress((int)userData.ActivityPercentage.Building);

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
