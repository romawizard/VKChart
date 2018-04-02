package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ilan on 02.04.2018.
 */

public class MessageModelResponse {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    public class Response {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("items")
        @Expose
        private List<ItemMessage> items = null;
        @SerializedName("in_read")
        @Expose
        private Integer inRead;
        @SerializedName("out_read")
        @Expose
        private Integer outRead;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<ItemMessage> getItems() {
            return items;
        }

        public void setItems(List<ItemMessage> items) {
            this.items = items;
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

    }
}
