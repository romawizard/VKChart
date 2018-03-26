package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilan on 25.02.2018.
 */

public class DialogModelResponse {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "DialogModelResponse{" +
                "response=" + response +
                '}';
    }

    public class Response {

        @SerializedName("dialogs")
        @Expose
        private ListDialogs dialogs;
        @SerializedName("name")
        @Expose
        private NameUser name;
        @SerializedName("my_name")
        @Expose
        private OwnerName ownerName;

        public ListDialogs getDialogs() {
            return dialogs;
        }

        public void setDialogs(ListDialogs dialogs) {
            this.dialogs = dialogs;
        }

        public NameUser getName() {
            return name;
        }

        public void setName(NameUser name) {
            this.name = name;
        }

        public OwnerName getOwnreName() {
            return ownerName;
        }

        public void setOwnerName(OwnerName ownerName) {
            this.ownerName = ownerName;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "dialogs=" + dialogs +
                    ", name=" + name +
                    ", myName=" + ownerName +
                    '}';
        }
    }
}
