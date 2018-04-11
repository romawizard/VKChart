package ru.roma.vkchart.domain.entities;

import java.util.Date;
import java.util.List;

import ru.roma.vkchart.data.api.model_response.Attachment;
import ru.roma.vkchart.utils.MyLog;

/**
 * Created by Ilan on 01.04.2018.
 */

public class Message {

    private Integer userId;
    private Integer fromId;
    private Integer date;
    private Integer readState;
    private Integer out;
    private Integer id;
    private Integer chatId;
    private String body;
    private List<Attachment> attachments = null;

    public Message( String body,int userId) {
        out = 1;

        Date date = new Date();
        long unixDate = date.getTime()/1000L;
        MyLog.log("time = "+ unixDate);
        this.date = (int)unixDate;
        this.body = body;
        this.userId = userId;
    }

    public Message() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getReadState() {
        return readState;
    }

    public void setReadState(Integer readState) {
        this.readState = readState;
    }

    public Integer getOut() {
        return out;
    }

    public void setOut(Integer out) {
        this.out = out;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!getUserId().equals(message.getUserId())) return false;
        if (!getFromId().equals(message.getFromId())) return false;
        if (!getDate().equals(message.getDate())) return false;
        return getBody().equals(message.getBody());

    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getFromId().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getBody().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", fromId=" + fromId +
                ", date=" + date +
                ", body='" + body + '\'' +
                '}';
    }
}
