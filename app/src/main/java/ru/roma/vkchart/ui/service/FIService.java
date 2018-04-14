package ru.roma.vkchart.ui.service;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.zzd;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.roma.vkchart.data.api.model_response.SendMessageModelResponse;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.utils.Keys;
import ru.roma.vkchart.utils.MyLog;

/**
 * Created by Ilan on 14.04.2018.
 */

public class FIService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("my log", "Refreshed NOTIFI token : " + refreshedToken);
        getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE).edit().putString(Keys.TOKEN_NOTIF, refreshedToken).commit();

        while (true) {
            String token = getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE).getString(Keys.TOKEN, null);
            if (!TextUtils.isEmpty(token)) {

                String androidID = Settings.Secure.getString(MyApplication.getInstance().getContentResolver()
                        , Settings.Secure.ANDROID_ID);

                MyLog.log("androidID = " + androidID);

                String settings = "{\"msg\":\"on\", \"chat\":[\"no_sound\",\"no_text\"]," +
                        " \"friend\":\"on\", \"reply\":\"on\", \"mention\":\"fr_of_fr\"}";

                MyApplication.getInstance().getQuery().registerDevice(refreshedToken,androidID,token).enqueue(new Callback<SendMessageModelResponse>() {
                    @Override
                    public void onResponse(Call<SendMessageModelResponse> call, Response<SendMessageModelResponse> response) {
                        MyLog.log("success register device, response = " + response.body().getResponse());
                    }

                    @Override
                    public void onFailure(Call<SendMessageModelResponse> call, Throwable t) {

                    }
                } );


                return;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
