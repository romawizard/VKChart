package ru.roma.vkchart.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import ru.roma.vkchart.R;
import ru.roma.vkchart.utils.Keys;
import ru.roma.vkchart.utils.MyLog;

public class SingInActivity extends AppCompatActivity  {

    private String[] scope = new String[]{VKScope.FRIENDS, VKScope.WALL, VKScope.MESSAGES,VKScope.PAGES,VKScope.PHOTOS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        String myToken = getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE).getString(Keys.TOKEN, null);
        if (TextUtils.isEmpty(myToken)) {
            // первый вход в приложение

            VKSdk.login(this, scope);
        }else {

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался

                MyLog.log("success autorization");
                MyLog.log("TOKEN = " +  res.accessToken);
                getSharedPreferences(Keys.MAINPREF,MODE_PRIVATE).edit().putString(Keys.TOKEN,res.accessToken).commit();
                Intent intent = new Intent(SingInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)

                MyLog.log("showError autorization");
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

