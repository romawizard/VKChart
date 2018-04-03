package ru.roma.vkchart.ui;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.roma.vkchart.ui.activities.SingInActivity;
import ru.roma.vkchart.data.api.ApiQuery;
import ru.roma.vkchart.data.data_base.DialogDataBase;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.domain.usecase.DaggerAppComponent;
import ru.roma.vkchart.domain.usecase.AppComponent;

/**
 * Created by Ilan on 20.02.2018.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private ApiQuery query;
    private DialogDataBase dataBase;
    private AppComponent appComponent;
    private VKAccessTokenTracker vkAccessTokenTracker;

    public static MyApplication getInstance() {
        return instance;
    }

    public ApiQuery getQuery() {
        return query;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        VKSdk.initialize(this);

        getVkAccessTokenTracker().startTracking();

        initializeRetrofit();

        dataBase = Room.databaseBuilder(this,DialogDataBase.class,"dataBase").build();

        appComponent = DaggerAppComponent.create();
    }

    private VKAccessTokenTracker getVkAccessTokenTracker() {


        vkAccessTokenTracker = new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
                if (newToken == null) {
                    // VKAccessToken is invalid
                    MyLog.log("VKAccessToken is invalid");

                    Intent intent = new Intent(MyApplication.this, SingInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        };
        return vkAccessTokenTracker;
    }

    private void initializeRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        query = retrofit.create(ApiQuery.class);

    }

    public  DialogDataBase getDataBase() {
        return dataBase;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
