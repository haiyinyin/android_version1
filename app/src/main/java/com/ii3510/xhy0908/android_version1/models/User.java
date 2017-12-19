package com.ii3510.xhy0908.android_version1.models;

/**
 * Created by xhy0908 on 2017/11/27.
 */

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class User implements Response{
//define object in JSON
    private int id;
    private String identity;
    private String fstname;
    private String lstname;
    private String password;
    private String grade;

    //constructor
    public User(){

    }
    public User(Parcel in) {
        this.id = in.readInt();
        this.identity = in.readString();
        this.fstname = in.readString();
        this.lstname = in.readString();
        this.password = in.readString();
        this.grade = in.readString();
    }

    //网上后加

    public User(int id,String fstname, String lstname, String password, String identity,String grade) {
        this.id = id;
        this.fstname=fstname;
        this.lstname = lstname;
        this.password = password;
        this.grade=grade;
        this.identity=identity;
    }
    //大写的值在Response里定义，就是JSON object的名字
    //JSON解析
    @Override
    public void initialiseWithJson(JSONObject jsonObject) throws JSONException {

        int id = jsonObject.getInt(USER_ID);
        String fstname = jsonObject.getString(FIRST_NAME);
        String lstname = jsonObject.getString(LAST_NAME);
        String password = jsonObject.getString(PASSWORD);
        String grade = jsonObject.getString(STUDENT_GRADE);
        String identity=jsonObject.getString(IDENTITY);


        this.id = id;
        this.fstname = fstname;
        this.lstname = lstname;
        this.password = password;
        this.grade = grade;
        this.identity = identity;

    }

    @Override
    //一个用户一个HashMAP 大写Key值
    public HashMap<String, String> getDetails() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(USER_ID, String.valueOf(id));
        hashMap.put(FIRST_NAME, fstname);
        hashMap.put(LAST_NAME, lstname);
        hashMap.put(PASSWORD, password);
        hashMap.put(STUDENT_GRADE, grade);
        hashMap.put(IDENTITY, identity);
        return hashMap;
    }

    @Override
    public String getName() {
        return this.fstname;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.fstname);
        parcel.writeString(this.lstname);
        parcel.writeString(this.password);
        parcel.writeString(this.grade);
        parcel.writeString(this.identity);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFstname() {
        return fstname;
    }

    public void setFstname(String fstname) {
        this.fstname = fstname;
    }

    public String getLstname() {
        return lstname;
    }

    public void setLstname(String lstname) {
        this.lstname = lstname;
    }

    //public String getPassword() {
     //   return password;
    //}

    //public void setPassword(String password) {
       // this.password = password;
   // }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
