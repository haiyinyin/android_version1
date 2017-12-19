package com.ii3510.xhy0908.android_version1;

/**
 * Created by xhy0908 on 2017/11/27.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.ii3510.xhy0908.android_version1.models.User;
import com.ii3510.xhy0908.android_version1.SharedPrefManager;
import android.content.SharedPreferences.Editor;


import android.app.Activity;


public class LoginActivity extends Activity {

    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("LoginActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              userLogin();
                //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                LoginActivity.this.finish();
            }

        });

        //if user presses on not registered
      /*  findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

*/
    }

    private void userLogin() {

        final String inputUsername = editTextUsername.getText().toString();
        final String inputPassword = editTextPassword.getText().toString();



        String URL_USER = URLs.URL_LOGIN + "?fstname=" + inputUsername;

        System.out.printf(URL_USER);
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


        final String SHARED_PREF_NAME = "MyPref";
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE );
        final Editor editor = sharedPreferences.edit();

        final String Name = "nameKey";




        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL_USER,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{

                            // Get current json object
                            JSONObject student = response.getJSONObject(0);

                            // Get the current student (json object) data
                            String firstName = student.getString("fstname");
                            String password=student.getString("password");

                            if(password.equals(inputPassword)){

                                editor.putString(Name, firstName);
                                editor.commit();

                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                            }else{startActivity(new Intent(getApplicationContext(), LoginActivity.class));}


                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        System.out.println(error.toString());
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);


    }
}