package com.ii3510.xhy0908.android_version1.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by xhy0908 on 2017/12/12.
 */

public class Software implements Response {
    private int coid;
    private String name;
    private String level;
    private String professor;
    private String time;
    private String salle;

    public Software(){

    }

    public Software(Parcel in){
        this.coid=in.readInt();
        this.name=in.readString();
        this.level=in.readString();
        this.professor=in.readString();
        this.time=in.readString();
        this.salle=in.readString();
    }

    @Override
    public void initialiseWithJson(JSONObject jsonObject) throws JSONException {
        int coid=jsonObject.getInt(COURSE_ID);
        String name=jsonObject.getString(COURSE_NAME);
        String level=jsonObject.getString(COURSE_LEVEL);
        String professor=jsonObject.getString(PROFESSOR);
        String time=jsonObject.getString(TIME);
        String salle=jsonObject.getString(SALLE);

        this.coid=coid;
        this.name=name;
        this.level=level;
        this.professor=professor;
        this.time=time;
        this.salle=salle;
    }
    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String, String>hashMap=new HashMap<>();
        hashMap.put(COURSE_ID,String.valueOf(coid));
        hashMap.put(COURSE_NAME,name);
        hashMap.put(COURSE_LEVEL,level);
        hashMap.put(PROFESSOR,professor);
        hashMap.put(TIME,time);
        hashMap.put(SALLE,salle);
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
        parcel.writeInt(this.coid);
        parcel.writeString(this.name);
        parcel.writeString(this.level);
        parcel.writeString(this.professor);
        parcel.writeString(this.time);
        parcel.writeString(this.salle);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Software createFromParcel(Parcel in) {
            return new Software(in);
        }

        public Software[] newArray(int size) {
            return new Software[size];
        }
    };
}
