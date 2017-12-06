package com.ii3510.xhy0908.android_version1;

/**
 * Created by xhy0908 on 2017/11/29.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ii3510.xhy0908.android_version1.models.*;

public class ProfileActivity extends AppCompatActivity {

    TextView textViewId, textViewUsername, textViewGrade, textViewIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }
}