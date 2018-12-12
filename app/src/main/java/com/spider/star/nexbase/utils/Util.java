package com.spider.star.nexbase.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Util {


    private static final Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }


    public int getScreenWidth(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.widthPixels;
    }
}
