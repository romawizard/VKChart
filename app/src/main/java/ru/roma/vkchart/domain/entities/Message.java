package ru.roma.vkchart.domain.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

import ru.roma.vkchart.data.api.model_response.Attachment;

/**
 * Created by Ilan on 01.04.2018.
 */
@Entity
public class Message {

    @PrimaryKey
    private Integer messageId;
    private Integer userId;
    private Integer fromId;
    private Integer date;
    private Integer readState;
    private Integer out;
    private Integer id;
    private Integer chatId;
    private String body;
    @Ignore
    private List<Attachment> attachments = null;
    private  boolean sent = true;
    private  boolean error = false;

    public Message( String body,int userId) {
        Date date = new Date();
        long unixDate = date.getTime()/1000L;
        this.date = (int)unixDate;
        this.body = body;
        this.userId = userId;
        this.readState = 0;
        this.out = 1;
        this.sent = false;
        this.fromId = userId;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
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
