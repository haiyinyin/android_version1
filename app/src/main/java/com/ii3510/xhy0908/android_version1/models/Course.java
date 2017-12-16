package com.ii3510.xhy0908.android_version1.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by xhy0908 on 2017/11/27.
 */

public class Course implements Response {

    private int coid;
    private String subject;
    private String professor;
    private String level;

    public Course() {
    }

    public Course(Parcel in) {
        this.coid = in.readInt();
        this.subject = in.readString();
        this.professor = in.readString();
        this.level = in.readString();

    }


    @Override
    public void initialiseWithJson(JSONObject jsonObject) throws JSONException {
        int coid = jsonObject.getInt(COURSE_ID);
        String subject = jsonObject.getString(COURSE_NAME);
        String professor = jsonObject.getString(PROFESSOR);
        String level = jsonObject.getString(COURSE_LEVEL);


        this.coid = coid;
        this.subject = subject;
        this.professor = professor;
        this.level = level;

    }

    @Override
    public HashMap<String, String> getDetails() {
        return null;
    }

    @Override
    public String getName() {
        return this.subject;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.coid);
        parcel.writeString(this.subject);
        parcel.writeString(this.professor);
        parcel.writeString(this.level);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public int getCoid() {
        return coid;
    }

    public void setCoid(int coid) {
        this.coid = coid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
