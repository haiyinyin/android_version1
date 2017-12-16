package com.ii3510.xhy0908.android_version1;

/**
 * Created by xhy0908 on 2017/11/29.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ii3510.xhy0908.android_version1.SharedPrefManager;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        //logout
        view.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }

        });




        return view;
    }
}