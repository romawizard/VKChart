package ru.roma.vkchart.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

 public class Dialogs {
    @SerializedName("message")
    @Expose
    private Message message;
    @SerializedName("in_read")
    @Expose
    private Integer inRead;
    @SerializedName("out_read")
    @Expose
    private Integer outRead;
    @SerializedName("unread")
    @Expose
    private String unRead;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getInRead() {
        return inRead;
    }

    public void setInRead(Integer inRead) {
        this.inRead = inRead;
    }

    public Integer getOutRead() {
        return outRead;
    }

    public void setOutRead(Integer outRead) {
        this.outRead = outRead;
    }

    public String getUnRead() {
        return unRead;
    }

    public void setUnRead(String unRead) {
        this.unRead = unRead;
    }

    @Override
    public String toString() {
        return "Dialogs{" +
                "message=" + message +
                ", inRead=" + inRead +
                ", outRead=" + outRead +
                '}';
    }
}
