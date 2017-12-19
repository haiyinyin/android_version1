package com.ii3510.xhy0908.android_version1;

/**
 * Created by xhy0908 on 2017/11/29.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "http://pastebin.com/raw/Em972E5s";

    final String SHARED_PREF_NAME = "MyPref";
    //final Context ctx = getContext().getApplicationContext();

    public ProfileFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("ProfileFragment");
        View view = inflater.inflate(R.layout.activity_profile, container, false);




        //logout
        view.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout(getContext().getApplicationContext());

                Intent intent = new Intent(getContext(), MainActivity.class);

                startActivity(intent);
                getActivity().finish();

            }

        });


        return view;
    }
    public void logout(Context ctx){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取Editor
        editor.clear();
        editor.apply();

    }


}