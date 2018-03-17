package ru.roma.vkchart.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

public class Attachment {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("gift")
    @Expose
    private Gift gift;
    @SerializedName("sticker")
    @Expose
    private Sticker sticker;
    @SerializedName("audio")
    @Expose
    private Audio audio;
    @SerializedName("video")
    @Expose
    private Video video;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "type='" + type + '\'' +
                ", photo=" + photo +
                ", gift=" + gift +
                ", sticker=" + sticker +
                ", audio=" + audio +
                ", video=" + video +
                '}';
    }
}
