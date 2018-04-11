package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 08.04.2018.
 */

public class SendMessageModelresponse {

    @SerializedName("response")
    @Expose
    private Integer response;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }
}
