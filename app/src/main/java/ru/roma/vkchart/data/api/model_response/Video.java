package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

public class Video {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("photo_130")
    @Expose
    private String photo130;
    @SerializedName("photo_320")
    @Expose
    private String photo320;
    @SerializedName("photo_800")
    @Expose
    private String photo800;
    @SerializedName("access_key")
    @Expose
    private String accessKey;
    @SerializedName("repeat")
    @Expose
    private Integer repeat;
    @SerializedName("can_add")
    @Expose
    private Integer canAdd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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

    public String getPhoto130() {
        return photo130;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public String getPhoto320() {
        return photo320;
    }

    public void setPhoto320(String photo320) {
        this.photo320 = photo320;
    }

    public String getPhoto800() {
        return photo800;
    }

    public void setPhoto800(String photo800) {
        this.photo800 = photo800;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Integer getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(Integer canAdd) {
        this.canAdd = canAdd;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", comments=" + comments +
                ", views=" + views +
                ", width=" + width +
                ", height=" + height +
                ", photo130='" + photo130 + '\'' +
                ", photo320='" + photo320 + '\'' +
                ", photo800='" + photo800 + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", repeat=" + repeat +
                ", canAdd=" + canAdd +
                '}';
    }
}
