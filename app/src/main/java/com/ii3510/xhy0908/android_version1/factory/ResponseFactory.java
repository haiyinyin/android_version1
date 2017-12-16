package com.ii3510.xhy0908.android_version1.factory;

import android.os.Parcel;

/**
 * Created by xhy0908 on 2017/11/27.
 */
import com.ii3510.xhy0908.android_version1.models.*;

public class ResponseFactory {
    public Response create(String type) {
        switch (type) {
            case "user":
                return new User();
            case "comments":
                return new Comments();
            case "course":
                return new Course();
            case "software":
                return new Software();

            default:
                throw new IllegalArgumentException("Unknown resource of type " + type);
        }
    }

    public Response create(String type, Parcel in) {
        switch (type) {
            case "user":
                return new User(in);
            case "comments":
                return new Comments(in);
            case "course":
                return new Course(in);
            case "software":
                return new Software(in);

            default:
                throw new IllegalArgumentException("Unknown resource of type " + type);
        }
    }

}
