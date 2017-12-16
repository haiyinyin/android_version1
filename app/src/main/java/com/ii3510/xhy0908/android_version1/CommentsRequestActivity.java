package com.ii3510.xhy0908.android_version1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

/**
 * Created by jxy01 on 2017/12/13.
 */

public class CommentsRequestActivity extends AppCompatActivity {
    private final String URL_BASE = "http://172.16.236.86:3000";
    private final String TYPES_OF_RESOURCE_EXTRA = "TYPES_OF_RESOURCE";
    private final String RESPONSE_EXTRA = "RESPONSE";
    private String resourceString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_request);
        // When Extras are passed in Intents, we can retrieve the information back with the next two lines
        Intent intent = getIntent();
        resourceString = intent.getStringExtra(TYPES_OF_RESOURCE_EXTRA);
        resourceString = resourceString.toLowerCase();

        TextView resourceTextView = (TextView) findViewById(R.id.resource_text_view);
        resourceTextView.setText(resourceString);

        // With Buttons, the listener to implement is OnClickListener
        Button searchButton = (Button) findViewById(R.id.resource_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = URL_BASE + "/" + resourceString;
                doQuery(url);
            }
        });
    }

    private void doQuery(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Method stub:
        // JsonArrayRequest(int method, String url, JSONArray jsonRequest, Listener<JSONArray> listener, ErrorListener errorListener)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Intent intent = new Intent(getApplicationContext(), CourseResponseActivity.class);
                intent.putExtra(TYPES_OF_RESOURCE_EXTRA, resourceString);
                intent.putExtra(RESPONSE_EXTRA, response.toString());
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Error while fetching data")
                        .setMessage("Something wrong happened while trying to get data from the web service.\n" +
                                "See the following error message:" + error.getLocalizedMessage())
                        .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
