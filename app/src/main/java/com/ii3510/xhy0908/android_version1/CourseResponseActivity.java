package com.ii3510.xhy0908.android_version1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.ii3510.xhy0908.android_version1.factory.ResponseFactory;
import com.ii3510.xhy0908.android_version1.models.Response;

/**
 * Created by xhy0908 on 2017/12/12.
 */

public class CourseResponseActivity extends AppCompatActivity {
    private final String TYPES_OF_RESOURCE_EXTRA = "TYPES_OF_RESOURCE";
    private final String RESPONSE_EXTRA = "RESPONSE";
    private final String SELECTED_RESPONSE_EXTRA = "SELECTED_RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_response);
        try {
            displayContentInListView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves the HTTP response sent with the Intent, creates the corresponding Response objects, and displays them inside a ListView.
     * @throws JSONException Exception for when the response String cannot be converted to a JSONArray
     */
    private void displayContentInListView() throws JSONException {
        Intent intent = getIntent();
        String resourceType = intent.getStringExtra(TYPES_OF_RESOURCE_EXTRA);
        String responseString = intent.getStringExtra(RESPONSE_EXTRA);

        final ArrayList<Response> responseList = getResponses(responseString, resourceType);
        List<String> responseNames = getResponseName(responseList);

        ListView listView = (ListView) findViewById(R.id.response_list_view);
        ArrayAdapter<String> responseAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, responseNames);
        listView.setAdapter(responseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Response selectedResponse = responseList.get(i);
                Intent nextIntent = new Intent(getApplicationContext(), CourseDetailActivity.class);
                nextIntent.putExtra(SELECTED_RESPONSE_EXTRA, selectedResponse);
                startActivity(nextIntent);
            }
        });
    }
    /**
     * This method creates a list of Response objects from the HTTP response.
     * @param responseString The HTTP response as a plain String
     * @param resourceType The type of resource queried
     * @return an ArrayList of Response objects corresponding to the items fetched by the HTTP request
     * @throws JSONException Exception for when the response String cannot be converted to a JSONArray
     */
    private ArrayList<Response> getResponses(String responseString, String resourceType) throws JSONException {
        JSONArray jsonArray = new JSONArray(responseString);
        ArrayList<Response> responseList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Response response = createResponse(resourceType);
            response.initialiseWithJson(jsonObject);
            responseList.add(response);
        }
        return responseList;
    }
    /**
     * This method creates an empty Response of a particular type.
     * @param resourceType The type of Response we wish to instantiate
     * @return a new instance of a Response
     */
    private Response createResponse(String resourceType) {
        ResponseFactory responseFactory = new ResponseFactory();
        return responseFactory.create(resourceType);
    }

    /**
     * This methods takes in a list of Response objects and creates the corresponding list of String containing the names of each Response.
     * @param responseList The list of Response objects
     * @return a list of Strings containing the names of each Response
     */
    private List<String> getResponseName(List<Response> responseList) {
        List<String> names = new ArrayList<>();
        for (Response response : responseList) {
            names.add(response.getName());
        }
        return names;
    }
}

