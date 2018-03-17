package ru.roma.vkchart.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ru.roma.vkchart.MyApplication;
import ru.roma.vkchart.models.DialogFromInternet;
import ru.roma.vkchart.models.DialogModel;
import ru.roma.vkchart.presenters.CallbackModel;

/**
 * Created by Ilan on 13.03.2018.
 */

public class DialogDomain {

    DialogFromInternet internetModel;

    public DialogDomain() {
        internetModel = new DialogFromInternet();
    }

    public void getListDialog(int lastPositionInLIst, CallbackModel callbackModel) {

        if (hasInternetConnection()){
            internetModel.loadData(lastPositionInLIst,callbackModel);
        }


    }


    private boolean hasInternetConnection() {

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
