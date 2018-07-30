package ru.roma.vkchart.data.api;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.api.model_response.GetUserModelResponse;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;
import ru.roma.vkchart.data.api.model_response.SendMessageModelResponse;

/**
 * Created by Ilan on 25.02.2018.
 */

public interface ApiVK {

    double VERSION = 5.73;

    @FormUrlEncoded
    @POST("execute.fullDialogs?&v="+ VERSION )
    Observable<DialogModelResponse> getDialog(@Field("offset")int offset,@Field("access_token")String token);

    @FormUrlEncoded
    @POST("messages.getHistory?&v="+ VERSION )
    Observable<MessageModelResponse> getMessages(@Field("user_id")int userId, @Field("offset")int offset
            , @Field("access_token")String token);

    @FormUrlEncoded
    @POST("messages.send?&v="+ VERSION )
    Observable<SendMessageModelResponse> sendMessage(@Field("user_id")int userId, @Field("message")String message
            , @Field("access_token")String token);

    @FormUrlEncoded
    @POST("account.registerDevice?&v="+ VERSION )
    Observable<SendMessageModelResponse> registerDevice(@Field("token") String tokenNotify, @Field("device_id")String deviceId
           , @Field("access_token")String token);

    @FormUrlEncoded
    @POST("users.get?&fields=bdate,city,country,photo_max_orig,friend_status,online,relation,sex,status&v="+ VERSION )
    Observable<GetUserModelResponse> getUser(@Field("user_id")int userId, @Field("access_token")String token);


}
