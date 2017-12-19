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
    private String name;
    private String content;
    private String time;
    private String user;

    public Comments(){

    }

    public Comments(Parcel in){
        this.cid=in.readInt();
        this.name=in.readString();
        this.content=in.readString();
        this.time=in.readString();
        this.user=in.readString();

    }

    @Override
    public void initialiseWithJson(JSONObject jsonObject) throws JSONException {
        int cid=jsonObject.getInt(COMMENTS_ID);
        String name=jsonObject.getString(COURSE_NAME);
        String content=jsonObject.getString(CONTENT);
        String time=jsonObject.getString(COMMENT_TIME);
        String user=jsonObject.getString(COMMENT_USER);

        this.cid=cid;
        this.name=name;
        this.content=content;
        this.time=time;
        this.user=user;

    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String, String>hashMap=new HashMap<>();
        hashMap.put(COMMENTS_ID,String.valueOf(cid));
        hashMap.put(COURSE_NAME,name);
        hashMap.put(CONTENT,content);
        hashMap.put(COMMENT_TIME,time);
        hashMap.put(COMMENT_USER,user);
        return hashMap;
    }

    @Override
    public String getName() {
        return this.name;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.cid);
        parcel.writeString(this.name);
        parcel.writeString(this.content);
        parcel.writeString(this.time);
        parcel.writeString(this.user);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };



}
