package com.ii3510.xhy0908.android_version1;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xhy0908 on 2017/12/10.
 */

public class CourseFragment extends Fragment {
    private final String TYPES_OF_RESOURCE_EXTRA = "TYPES_OF_RESOURCE";
    private final String RESPONSE_EXTRA = "RESPONSE";
    private final String SELECTED_RESPONSE_EXTRA = "SELECTED_RESPONSE";
    public CourseFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
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
                // To go to one Activity to another, we use the Intent class
                Intent intent = new Intent(getContext(), CourseRequestActivity.class);
                // It is possible to pass information from an Activity to another by adding Extras to the intents
                intent.putExtra(TYPES_OF_RESOURCE_EXTRA, selectedText);
                // The startActivity method is the method that actually starts another Activity
                startActivity(intent);
            }
        });
        Button searchButton = (Button) view.findViewById(R.id.resource_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WriteComments.class);
                // It is possible to pass information from an Activity to another by adding Extras to the intents


                startActivity(intent);
            }
        });


                return view;
            }


        }

