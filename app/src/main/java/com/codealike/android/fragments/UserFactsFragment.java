package com.codealike.android.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.codealike.android.CodealikeApplication;
import com.codealike.android.R;
import com.codealike.android.model.UserData;
import com.x5.template.Chunk;
import com.x5.template.Theme;
import com.x5.template.providers.AndroidTemplates;

import java.io.IOException;
import java.io.InputStream;

public class UserFactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_facts, container, false);
        FragmentActivity activity = getActivity();

        UserData userData = ((CodealikeApplication)activity.getApplication()).getUserData();

        WebView webView = (WebView)v.findViewById(R.id.userFactsWebView);

        AndroidTemplates loader = new AndroidTemplates(activity.getBaseContext());
        Theme theme = new Theme(loader);
        Chunk chunk = theme.makeChunk("PieChartTemplate#root");
        chunk.set("coding", userData.ActivityPercentage.Coding);
        chunk.set("building", userData.ActivityPercentage.Building);
        chunk.set("debugging", userData.ActivityPercentage.Debugging);
        chunk.set("width", webView.getWidth());
        chunk.set("height", webView.getHeight());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocusFromTouch();
        webView.loadDataWithBaseURL( "file:///android_asset/", chunk.toString(), "text/html", "utf-8", null );

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
