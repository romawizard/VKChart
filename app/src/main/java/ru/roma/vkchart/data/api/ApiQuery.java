package ru.roma.vkchart.data.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;
import ru.roma.vkchart.data.api.model_response.SendMessageModelResponse;

/**
 * Created by Ilan on 25.02.2018.
 */

public interface ApiQuery {

    double  VERSION = 5.73;

    @FormUrlEncoded
    @POST("execute.fullDialogs?&v="+ VERSION )
    Call<DialogModelResponse> getDialog(@Field("offset")int offset,@Field("access_token")String token);

    @FormUrlEncoded
    @POST("messages.getHistory?&v="+ VERSION )
    Call<MessageModelResponse> getMessages(@Field("user_id")int userId, @Field("offset")int offset
            , @Field("access_token")String token);

    @FormUrlEncoded
    @POST("messages.send?&v="+ VERSION )
    Call<SendMessageModelResponse> sendMessage(@Field("user_id")int userId, @Field("message")String message
            , @Field("access_token")String token);

    @FormUrlEncoded
    @POST("account.registerDevice?&v="+ VERSION )
    Call<SendMessageModelResponse> registerDevice(@Field("token") String tokenNotif, @Field("device_id")String deviceId
           , @Field("access_token")String token);

}
