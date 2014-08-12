package com.codealike.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.codealike.android.CodealikeApplication;
import com.codealike.android.R;

import com.codealike.android.model.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean loggedIn = false;
        if(loggedIn) {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_login);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {

        String userName = ((EditText)findViewById(R.id.username)).getText().toString();
        //String token = ((EditText)findViewById(R.id.token)).getText().toString();
        String token = "196f999e-e860-4687-9122-1bbfa4802ae0";

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

                Intent dashboardIntent = new Intent(LoginActivity.this, DashboardActivity.class);
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
