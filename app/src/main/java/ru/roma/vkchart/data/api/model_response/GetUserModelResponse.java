package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ilan on 28.05.2018.
 */

public class GetUserModelResponse {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public class Response {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("sex")
        @Expose
        private Integer sex;
        @SerializedName("bdate")
        @Expose
        private String bdate;
        @SerializedName("city")
        @Expose
        private City city;
        @SerializedName("country")
        @Expose
        private Country country;
        @SerializedName("photo_max_orig")
        @Expose
        private String photoMaxOrig;
        @SerializedName("friend_status")
        @Expose
        private Integer friendStatus;
        @SerializedName("online")
        @Expose
        private Integer online;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("relation")
        @Expose
        private Integer relation;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public String getBdate() {
            return bdate;
        }

        public void setBdate(String bdate) {
            this.bdate = bdate;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public String getPhotoMaxOrig() {
            return photoMaxOrig;
        }

        public void setPhotoMaxOrig(String photoMaxOrig) {
            this.photoMaxOrig = photoMaxOrig;
        }

        public Integer getFriendStatus() {
            return friendStatus;
        }

        public void setFriendStatus(Integer friendStatus) {
            this.friendStatus = friendStatus;
        }

        public Integer getOnline() {
            return online;
        }

        public void setOnline(Integer online) {
            this.online = online;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getRelation() {
            return relation;
        }

        public void setRelation(Integer relation) {
            this.relation = relation;
        }
    }
}
