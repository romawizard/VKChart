package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

 public class PushSettings {
    @SerializedName("sound")
    @Expose
    private Integer sound;
    @SerializedName("disabled_until")
    @Expose
    private Integer disabledUntil;

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Integer getDisabledUntil() {
        return disabledUntil;
    }

    public void setDisabledUntil(Integer disabledUntil) {
        this.disabledUntil = disabledUntil;
    }
}
