package ru.roma.vkchart.utils;

import android.util.Log;

import ru.roma.vkchart.BuildConfig;

/**
 * Created by Ilan on 24.02.2018.
 */

public class MyLog {

    public  static void log(String log){
        if (BuildConfig.DEBUG){
            Log.d("MY LOG",log);
        }
    }
}
