package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

public class Sticker {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("photo_64")
    @Expose
    private String photo64;
    @SerializedName("photo_128")
    @Expose
    private String photo128;
    @SerializedName("photo_256")
    @Expose
    private String photo256;
    @SerializedName("photo_352")
    @Expose
    private String photo352;
    @SerializedName("photo_512")
    @Expose
    private String photo512;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPhoto64() {
        return photo64;
    }

    public void setPhoto64(String photo64) {
        this.photo64 = photo64;
    }

    public String getPhoto128() {
        return photo128;
    }

    public void setPhoto128(String photo128) {
        this.photo128 = photo128;
    }

    public String getPhoto256() {
        return photo256;
    }

    public void setPhoto256(String photo256) {
        this.photo256 = photo256;
    }

    public String getPhoto352() {
        return photo352;
    }

    public void setPhoto352(String photo352) {
        this.photo352 = photo352;
    }

    public String getPhoto512() {
        return photo512;
    }

    public void setPhoto512(String photo512) {
        this.photo512 = photo512;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Sticker{" +
                "id=" + id +
                ", productId=" + productId +
                ", photo64='" + photo64 + '\'' +
                ", photo128='" + photo128 + '\'' +
                ", photo256='" + photo256 + '\'' +
                ", photo352='" + photo352 + '\'' +
                ", photo512='" + photo512 + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
