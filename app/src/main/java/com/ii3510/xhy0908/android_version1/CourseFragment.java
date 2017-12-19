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
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xhy0908 on 2017/12/10.
 */

public class CourseFragment extends Fragment {
    private final String TYPES_OF_RESOURCE_EXTRA = "TYPES_OF_RESOURCE";
    private final String RESPONSE_EXTRA = "RESPONSE";
    private final String SELECTED_RESPONSE_EXTRA = "SELECTED_RESPONSE";
    private String resourceString;
    public CourseFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("CourseFragment");
        View view = inflater.inflate(R.layout.fragment_course2, container, false);
        ListView listView = (ListView) view.findViewById(R.id.home_list_view);

        // getResources() can be accessed in every Activity, and allows the developer to retrieve the data stored in the /res folder
        String[] strings = getResources().getStringArray(R.array.resources);
        List<String> resourcesList = Arrays.asList(strings);

        // To populate a ListView, we need to use the ArrayAdapter class which contains all the elements to be displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, resourcesList);
        listView.setAdapter(adapter);

        // We need to implement the onItemClickListener method to manage the "item clicked" event.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedText = (String) adapterView.getItemAtPosition(i);

                resourceString=selectedText;


                // To go to one Activity to another, we use the Intent class
                //Intent intent = new Intent(getContext(), CourseRequestActivity.class);
                // It is possible to pass information from an Activity to another by adding Extras to the intents
                //intent.putExtra(TYPES_OF_RESOURCE_EXTRA, selectedText);
                // The startActivity method is the method that actually starts another Activity
                //startActivity(intent);

                String url= "http://172.16.236.86:3000/course?major=" +  resourceString;
                //String url="http://10.188.48.80:3000/;
                doQuery(url);


            }
        });


        return view;
    }
    private void doQuery(String url ) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // Method stub:
        // JsonArrayRequest(int method, String url, JSONArray jsonRequest, Listener<JSONArray> listener, ErrorListener errorListener)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Intent intent = new Intent(getContext(), CourseResponseActivity.class);
                intent.putExtra(TYPES_OF_RESOURCE_EXTRA, resourceString);
                intent.putExtra(RESPONSE_EXTRA, response.toString());
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
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

