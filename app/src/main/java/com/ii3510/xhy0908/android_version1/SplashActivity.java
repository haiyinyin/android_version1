package com.ii3510.xhy0908.android_version1;

/**
 * Created by xhy0908 on 2017/11/26.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ii3510.xhy0908.android_version1.global.AppConstants;
import com.ii3510.xhy0908.android_version1.utils.SpUtils;

/**
     * @desc 启动屏
     * Created by devilwwj on 16/1/23.
     */
    public class SplashActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // 判断是否是第一次开启应用
            boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);
            // 如果是第一次启动，则先进入功能引导页
            if (!isFirstOpen) {
                Intent intent = new Intent(this, WelcomeGuideActivity.class);
                startActivity(intent);
                finish();
                return;
            }

             //如果不是第一次启动app，则正常显示启动屏
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    enterHomeActivity();
                }
            }, 2000);
        }


        private void enterHomeActivity() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
