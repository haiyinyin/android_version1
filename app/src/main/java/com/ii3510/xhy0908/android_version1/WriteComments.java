package com.ii3510.xhy0908.android_version1;

import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.ii3510.xhy0908.android_version1.SendDataToServer;

/**
 * Created by jxy01 on 2017/12/13.
 */

public class WriteComments extends AppCompatActivity {
    EditText Comment;
    ProgressBar progressBar;
    private Context mContext;
    // Progress dialog
    private ProgressDialog pDialog;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.WriteComments);
        Comment = (EditText) findViewById(R.id.edit_comment);



        //if user presses on login
        //calling the method login
        Button searchButton = (Button) findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_comment();

                AlertDialog.Builder builder = new AlertDialog.Builder(WriteComments.this);
                //    设置Title的图标

                //    设置Title的内容
                builder.setTitle("THX for comment");
                //    设置Content来显示一个信息
                builder.setMessage("successfully comment");
                //    设置一个PositiveButton


                builder.setPositiveButton("Return", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        add_comment();
                        Intent intent = new Intent(getApplicationContext(), CommentsFragment.class);
                        startActivity(intent);

                }});

            }
        });

    }
    private void add_comment(){
        String URL_USER = "http://172.16.236.86:3000/user?fstname=haiyin";
        String edit_comment = Comment.getText().toString();
        JSONObject post_dict = new JSONObject();
        try{
            post_dict.put("comment" , edit_comment);



        }catch(Exception e){
            e.printStackTrace();
        }
        if (post_dict.length() > 0) {
            new SendDataToServer().execute(String.valueOf(post_dict));

        }

    }
}
