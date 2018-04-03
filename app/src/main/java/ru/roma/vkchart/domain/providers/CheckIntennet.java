package ru.roma.vkchart.domain.providers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ru.roma.vkchart.ui.MyApplication;

/**
 * Created by Ilan on 24.03.2018.
 */

public class CheckIntennet implements DeviceStateProvider {


    public CheckIntennet() {
    }

    @Override
    public boolean hasInternetConection() {

        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}
