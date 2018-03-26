package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ilan on 01.03.2018.
 */

public class ListDialogs {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<DialogsHolder> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DialogsHolder> getItems() {
        return items;
    }

    public void setItems(List<DialogsHolder> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Response{" +
                "count=" + count +
                ", items=" + items +
                '}';
    }

}
