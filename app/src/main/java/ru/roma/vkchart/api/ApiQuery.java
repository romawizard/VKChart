package ru.roma.vkchart.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.roma.vkchart.MyApplication;
import ru.roma.vkchart.model_response.DialogModelResponse;
import ru.roma.vkchart.utils.Keys;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ilan on 25.02.2018.
 */

public interface ApiQuery {

     double VERSION = 5.73;


    @GET("execute.fullDialogs?&v="+ VERSION )
    Call<DialogModelResponse> getDialog(@Query("offset")int offset,@Query("access_token")String token);
}
