package com.ii3510.xhy0908.android_version1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import android.content.Intent;

import com.ii3510.xhy0908.android_version1.LoginActivity;

import android.content.SharedPreferences;
import android.content.Context;
import java.util.jar.Attributes;


/**
 * Created by xhy0908 on 2017/11/28.
 */

public class HomeActivity extends AppCompatActivity {
    /** Called when the activity is first created. */

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    final String SHARED_PREF_NAME = "shared_pref_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("HomeActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();



        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_course:
                        fragment = new CourseFragment();
                        break;
                    case R.id.action_comments:
                        fragment = new CommentsFragment();
                        break;

                    case R.id.action_profile:
                        SharedPreferences pref = getSharedPreferences("MyPref",MODE_PRIVATE);
                        String str =pref.getString("nameKey",null);
                        if(str==null)
                        //sharedPreferences.getString("nameKey", null) == null
                        {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            break;
                        }else{

                        fragment = new ProfileFragment();
                        break;}

                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }


}
