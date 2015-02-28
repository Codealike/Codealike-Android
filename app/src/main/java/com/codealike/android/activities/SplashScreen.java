package com.codealike.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.codealike.android.CodealikeApplication;
import com.codealike.android.R;
import com.codealike.android.model.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

import java.util.UUID;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        CodealikeApplication app = ((CodealikeApplication)this.getApplication());

        checkStoredCredentials(app.getUserName(), app.getToken());
    }

    public void login(View view) {

        EditText tokenText = ((EditText)findViewById((R.id.token)));

        String token = tokenText.getText().toString();

        if (token == null || token.isEmpty() || !token.matches("[^\\s]+\\/[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            tokenText.setError("Invalid token format");
            return;
        }

        //TODO: validate if the token has more than one "/"
        String[] tokenParts = token.split("/");
        String userName = tokenParts[0];
        String tokenId = tokenParts[1];

        //Show progress bar.
        ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);

        fetchFactsData(userName, tokenId);
    }

    private void checkStoredCredentials(String userName, String token)
    {
        if(userName != null && !userName.isEmpty() && token != null && !token.isEmpty())
        {
            fetchFactsData(userName, token);
        }
        else
        {
            //Hide progress bar.
            ((ProgressBar)findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);

            //Show login fields.
            findViewById(R.id.loginForm).setVisibility(View.VISIBLE);
        }
    }

    private void fetchFactsData(String userName, String token)
    {
        String factsUrl = "https://codealike.com/api/v2/facts/" + userName;
        AsyncHttpClient client = new AsyncHttpClient();
        Header[] headers = {
                new BasicHeader("X-Api-Identity", userName),
                new BasicHeader("X-Api-Token", token)
        };

        client.get(this.getApplicationContext(), factsUrl, headers, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                UserData userData = gson.fromJson(response.toString(), UserData.class);
                CodealikeApplication app = (CodealikeApplication)getApplication();
                app.setUserData(userData);

                Intent dashboardIntent = new Intent(SplashScreen.this, DashboardActivity.class);
                startActivity(dashboardIntent);
                //finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //TODO: handle failure.
            }
        });
    }
}
