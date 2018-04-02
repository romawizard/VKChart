package ru.roma.vkchart.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;

/**
 * Created by Ilan on 25.02.2018.
 */

public interface ApiQuery {

     double VERSION = 5.73;


    @GET("execute.fullDialogs?&v="+ VERSION )
    Call<DialogModelResponse> getDialog(@Query("offset")int offset,@Query("access_token")String token);

    @GET("messages.getHistory?&v="+ VERSION )
    Call<MessageModelResponse> getMwsages(@Query("user_id")int userId,@Query("offset")int offset
            ,@Query("access_token")String token);


}
