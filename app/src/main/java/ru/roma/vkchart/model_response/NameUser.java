package ru.roma.vkchart.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ilan on 01.03.2018.
 */

public class NameUser {

    @SerializedName("items")
    @Expose
    private List<ItemName> itemNames = null;

    public List<ItemName> getItemNames() {
        return itemNames;
    }

    public void setItemNames(List<ItemName> itemNames) {
        this.itemNames = itemNames;
    }

    public class ItemName {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("photo_100")
        @Expose
        private String photo100;
        @SerializedName("online")
        @Expose
        private Integer online;
        @SerializedName("deactivated")
        @Expose
        private String deactivated;

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

        public String getPhoto100() {
            return photo100;
        }

        public void setPhoto100(String photo100) {
            this.photo100 = photo100;
        }

        public Integer getOnline() {
            return online;
        }

        public void setOnline(Integer online) {
            this.online = online;
        }

        public String getDeactivated() {
            return deactivated;
        }

        public void setDeactivated(String deactivated) {
            this.deactivated = deactivated;
        }

    }

  }
