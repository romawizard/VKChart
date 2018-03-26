package ru.roma.vkchart.data.api.model_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ilan on 25.02.2018.
 */

public class Message {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("date")
    @Expose
    private int date;
    @SerializedName("out")
    @Expose
    private int out;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("read_state")
    @Expose
    private int readState;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = null;
    @SerializedName("chat_id")
    @Expose
    private int chatId;
    @SerializedName("chat_active")
    @Expose
    private List<Integer> chatActive = null;
    @SerializedName("push_settings")
    @Expose
    private PushSettings pushSettings;
    @SerializedName("users_count")
    @Expose
    private int usersCount;
    @SerializedName("admin_id")
    @Expose
    private int adminId;
    @SerializedName("photo_50")
    @Expose
    private String photo50;
    @SerializedName("photo_100")
    @Expose
    private String photo100;
    @SerializedName("photo_200")
    @Expose
    private String photo200;
    @SerializedName("random_id")
    @Expose
    private int randomId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public List<Integer> getChatActive() {
        return chatActive;
    }

    public void setChatActive(List<Integer> chatActive) {
        this.chatActive = chatActive;
    }

    public PushSettings getPushSettings() {
        return pushSettings;
    }

    public void setPushSettings(PushSettings pushSettings) {
        this.pushSettings = pushSettings;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }

    public int getRandomId() {
        return randomId;
    }

    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date +
                ", out=" + out +
                ", userId=" + userId +
                ", readState=" + readState +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachments +
                ", chatId=" + chatId +
                ", chatActive=" + chatActive +
                ", pushSettings=" + pushSettings +
                ", usersCount=" + usersCount +
                ", adminId=" + adminId +
                ", photo50='" + photo50 + '\'' +
                ", photo100='" + photo100 + '\'' +
                ", photo200='" + photo200 + '\'' +
                ", randomId=" + randomId +
                '}';
    }
}
