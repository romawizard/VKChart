package ru.roma.vkchart.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ilan on 01.03.2018.
 */

public class NameDialogs {

    @SerializedName("items")
    @Expose
    private List<NameUser> items = null;

    public List<NameUser> getItems() {
        return items;
    }

    public void setItems(List<NameUser> items) {
        this.items = items;
    }
}
