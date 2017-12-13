package com.ii3510.xhy0908.android_version1.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by xhy0908 on 2017/11/27.
 */

public class Comments implements Response{

    private int cid;
    private String subject;
    private String content;
    private String user;


    //constructor
    public Comments() {
    }

    public Comments(Parcel in) {
        this.cid = in.readInt();
        this.subject = in.readString();
        this.content = in.readString();
        this.user = in.readString();

    }
    @Override
    public void initialiseWithJson(JSONObject jsonObject) throws JSONException {
        int cid = jsonObject.getInt(COMMENTS_ID);
        String subject = jsonObject.getString(COURSE_NAME);
        String content = jsonObject.getString(CONTENT);
        String user = jsonObject.getString(FIRST_NAME);

        this.cid = cid;
        this.subject = subject;
        this.content = content;
        this.user = user;


    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(COMMENTS_ID, String.valueOf(cid));
        hashMap.put(COURSE_NAME, subject);
        hashMap.put(CONTENT, content);
        hashMap.put(USER, user);

        return hashMap;
    }

    @Override
    public String getName() {
        return this.subject;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.cid);
        parcel.writeString(this.subject);
        parcel.writeString(this.content);
        parcel.writeString(this.user);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
