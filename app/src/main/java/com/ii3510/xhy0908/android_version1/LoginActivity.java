package com.ii3510.xhy0908.android_version1;

/**
 * Created by xhy0908 on 2017/11/27.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import android.content.Context;
import com.android.volley.VolleyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.ii3510.xhy0908.android_version1.models.*;
import com.ii3510.xhy0908.android_version1.URLs;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }

        });

        //if user presses on not registered
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }


    private void userLogin() {
        //first getting the values
        final String inputUsername = editTextUsername.getText().toString();
        final String inputPassword = editTextPassword.getText().toString();
        String URL_USER=URLs.URL_LOGIN+"?fstname=" + inputUsername;

        //validating inputs
        if (TextUtils.isEmpty(inputUsername)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(inputPassword)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jArr = new JsonArrayRequest("http://172.16.236.86:3000/user?fstname=haiyin", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

if(!response.equals(null)) {
    // Parsing json

    try {
        JSONObject obj = response.getJSONObject(1);
        String firstName = obj.getString("fstname");
        String password = obj.getString("password");
        if(inputPassword.equals(password)){
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }


    } catch (JSONException e) {
        e.printStackTrace();
    }
}else{
    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
}
                }



        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });
    }
}
