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

public class ProfileFragment extends Fragment {

    final String SHARED_PREF_NAME = "shared_pref_name";
    //final Context ctx = getContext().getApplicationContext();

    public ProfileFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        //logout
        view.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              logout(getContext().getApplicationContext());

                Intent intent = new Intent(getContext(), LoginActivity.class);

                startActivity(intent);
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