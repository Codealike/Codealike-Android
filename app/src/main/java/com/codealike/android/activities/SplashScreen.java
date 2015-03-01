package com.codealike.android.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class SplashScreen extends Activity {

    private final String Codealike_Api_Token = "codealike_api_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        checkStoredCredentials();
    }

    public void login(View view) {

        EditText tokenText = ((EditText)findViewById((R.id.token)));

        String token = tokenText.getText().toString();

        if (token == null || token.isEmpty() || !token.matches("[^\\s]+\\/[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            tokenText.setError("Invalid token format");
            return;
        }

        String[] tokenParts = token.split("/");

        if(tokenParts.length != 2)
        {
            tokenText.setError("Invalid token format");
            return;
        }

        String userName = tokenParts[0];
        String tokenId = tokenParts[1];

        //Show progress bar.
        ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);

        fetchFactsData(userName, tokenId);
    }

    private void checkStoredCredentials()
    {
        try {

            FileInputStream fis = openFileInput(Codealike_Api_Token);
            byte[] bytes = new byte[100];
            int readBytes = fis.read(bytes);
            fis.close();
            byte[] tokenBytes = Arrays.copyOf(bytes, readBytes);
            String[] tokenParts = new String(tokenBytes).split("/");

            String userName = tokenParts[0];
            String tokenId = tokenParts[1];

            fetchFactsData(userName, tokenId);

        } catch (FileNotFoundException e) //There are no stored credentials.
        {
            //Hide progress bar.
            ((ProgressBar)findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);

            //Show login fields.
            findViewById(R.id.loginForm).setVisibility(View.VISIBLE);

        } catch (IOException e) {
            //TODO: handle error properly.
            e.printStackTrace();
        }
    }

    private void fetchFactsData(final String userName, final String token)
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

                if(((CheckBox)findViewById(R.id.rememberMe)).isChecked())
                {
                    //Store credentials.
                    try {
                        storeCredentials(userName, token);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

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

    private void storeCredentials(final String userName, final String token) throws IOException {
        FileOutputStream fos = openFileOutput(Codealike_Api_Token, Context.MODE_PRIVATE);
        byte[] tokenBytes = (userName + "/" + token).getBytes();
        fos.write(tokenBytes);
        fos.close();
    }
}
