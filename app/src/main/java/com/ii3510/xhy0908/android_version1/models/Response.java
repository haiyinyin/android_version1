package com.ii3510.xhy0908.android_version1.models;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by xhy0908 on 2017/11/27.
 */

public interface Response extends Parcelable {
    String USER_ID = "id";
    String IDENTITY = "identity";
    String FIRST_NAME = "fstname";
    String LAST_NAME = "lstname";
    String PASSWORD = "password";
    String STUDENT_GRADE = "grade";


    String USER = "user";


    //COURSE  与JSON server匹配
    String COURSE_ID = "coid";
    //+COURSE NAME
    String COURSE_LEVEL="level";
    String PROFESSOR="professor";
    String SALLE="salle";
    String TIME="time";
    String MAJOR="major";

    //COMMENTS
    String COMMENTS_ID="cid";
    String COURSE_NAME = "name";
    String CONTENT = "content";
    String COMMENT_TIME="time";
    String COMMENT_USER="user";

    void initialiseWithJson(JSONObject jsonObject) throws JSONException;

    /**
     * Generic method to get the detailed information of a Response object
     * @return A map of the parameters of a Response object
     */
    HashMap<String, String> getDetails();

    /**
     * Generic method to get the name or title of a Response object
     * @return The name or title of a Response object
     */
    String getName();


}
