package com.spider.star.nexbase.shardprefeence;

import android.content.Context;
import android.content.SharedPreferences;

public class MySession extends SessionConstant {

    private final String FILE_NAME = "nexbase_client-Preferences";

    private static MySession MySession;
    SharedPreferences preferences;

    private MySession() {
    }

    public static MySession getInstance(Context context) {
        if (MySession == null) {
            MySession = new MySession();
        }
        MySession.getPreferenceObject(context);
        return MySession;
    }

    private void getPreferenceObject(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
    }

    public void saveUserName(String key){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(IS_LOGIN, key);
        editor.commit();
    }

    public String getUserName(){
        return preferences.getString(IS_LOGIN, null);
    }


    public void saveLoginStatus(boolean status){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(LOGIN_STATUS,status);
        editor.commit();
    }
    public boolean isLogin(){
        return preferences.getBoolean(LOGIN_STATUS,false);
    }

    public void clearUserdata(){

        preferences.edit().clear().commit();

    }

}
