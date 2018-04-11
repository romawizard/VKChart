package ru.roma.vkchart.data.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;
import ru.roma.vkchart.data.api.model_response.SendMessageModelresponse;

/**
 * Created by Ilan on 25.02.2018.
 */

public interface ApiQuery {

     double VERSION = 5.73;


    @GET("execute.fullDialogs?&v="+ VERSION )
    Call<DialogModelResponse> getDialog(@Query("offset")int offset,@Query("access_token")String token);

    @GET("messages.getHistory?&v="+ VERSION )
    Call<MessageModelResponse> getMessages(@Query("user_id")int userId, @Query("offset")int offset
            , @Query("access_token")String token);

    @FormUrlEncoded
    @POST("messages.send?&v="+ VERSION )
    Call<SendMessageModelresponse> sendMessage(@Field("user_id")int userId, @Field("message")String message
            , @Field("access_token")String token);

}
