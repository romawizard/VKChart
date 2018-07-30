package ru.roma.vkchart.ui.service;

import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
        Log.d("my log", "Refreshed NOTIFY token : " + refreshedToken);
        getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE).edit().putString(Keys.TOKEN_NOTIF, refreshedToken).apply();

        while (true) {
            String token = getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE).getString(Keys.TOKEN, null);
            if (!TextUtils.isEmpty(token)) {

//                String androidID = Settings.Secure.getString(MyApplication.getInstance().getContentResolver()
//                        , Settings.Secure.ANDROID_ID);
                String androidId = "";
                try {
                    androidId  = AdvertisingIdClient.getAdvertisingIdInfo(getBaseContext()).getId();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }

                MyLog.log("androidID = " + androidId);

                String settings = "{\"msg\":\"on\", \"chat\":[\"no_sound\",\"no_text\"]," +
                        " \"friend\":\"on\", \"reply\":\"on\", \"mention\":\"fr_of_fr\"}";


                MyApplication.getInstance().getApiVK().registerDevice(refreshedToken, androidId, token).subscribe(new Observer<SendMessageModelResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendMessageModelResponse sendMessageModelResponse) {
                        MyLog.log("success register device, response = " + sendMessageModelResponse.getResponse());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

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
