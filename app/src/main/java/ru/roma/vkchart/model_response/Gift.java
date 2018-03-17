package ru.roma.vkchart.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

public class Gift {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("thumb_256")
    @Expose
    private String thumb256;
    @SerializedName("thumb_96")
    @Expose
    private String thumb96;
    @SerializedName("thumb_48")
    @Expose
    private String thumb48;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThumb256() {
        return thumb256;
    }

    public void setThumb256(String thumb256) {
        this.thumb256 = thumb256;
    }

    public String getThumb96() {
        return thumb96;
    }

    public void setThumb96(String thumb96) {
        this.thumb96 = thumb96;
    }

    public String getThumb48() {
        return thumb48;
    }

    public void setThumb48(String thumb48) {
        this.thumb48 = thumb48;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", thumb256='" + thumb256 + '\'' +
                ", thumb96='" + thumb96 + '\'' +
                ", thumb48='" + thumb48 + '\'' +
                '}';
    }
}
