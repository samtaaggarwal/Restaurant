package com.example.user126065.restaurant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 5/5/2016.
 */
public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode",logggedin);
        editor.commit();
    }
    public void setusename(String usename)
    {
        editor.putString("usename", usename);
        editor.commit();
    }

    public String getusename()
    {
        String usename = prefs.getString("usename","");
        return usename;
    }
    public void setpassword(String passWord) {
        editor.putString("pass", passWord);
        editor.commit();
    }

    public String getpassword() {
        String passname = prefs.getString("pass","");
        return passname;
    }
    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }
}
