package com.ii3510.xhy0908.android_version1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ii3510.xhy0908.android_version1.factory.ResponseFactory;
import com.ii3510.xhy0908.android_version1.models.Comments;
import com.ii3510.xhy0908.android_version1.models.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by xhy0908 on 2017/12/11.
 */

public class CommentsFragment extends Fragment {

    private final String TYPES_OF_RESOURCE_EXTRA = "TYPES_OF_RESOURCE";
    private final String RESPONSE_EXTRA = "RESPONSE";
    private final String SELECTED_RESPONSE_EXTRA = "SELECTED_RESPONSE";
    private final String url= "http://172.16.236.86:3000/comments";
    private final String resourceString="comments";
    public CommentsFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("CommentsFragment");
        View view = inflater.inflate(R.layout.fragment_comments, container, false);

        //look comments
        Button checkButton = (Button) view.findViewById(R.id.check_buttom);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doQuery(url);
            }
        });


        // add comments
        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WriteCommentsActivity.class);
                startActivity(intent);
            }
        });

        Button myButton = (Button) view.findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WriteCommentsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void doQuery(String url ) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Method stub:
        // JsonArrayRequest(int method, String url, JSONArray jsonRequest, Listener<JSONArray> listener, ErrorListener errorListener)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Intent intent = new Intent(getContext(), CheckCommentsResponseActivity.class);
                intent.putExtra(TYPES_OF_RESOURCE_EXTRA, resourceString);
                intent.putExtra(RESPONSE_EXTRA, response.toString());
                startActivity(intent);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
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

