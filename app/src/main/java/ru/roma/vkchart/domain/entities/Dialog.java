package ru.roma.vkchart.domain.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import java.util.List;

import ru.roma.vkchart.data.api.model_response.Attachment;

/**
 * Created by Ilan on 03.03.2018.
 */

@Entity(primaryKeys = {"userId", "chartId"})
public class Dialog {

    @NonNull
    private Integer userId;
    private Integer online;
    private Integer out;
    private Integer count;
    private Integer readState;
    private Integer userCount;
    private Integer date;
    private Integer adminId;
    @NonNull
    private Integer chartId;
    private String firstName;
    private String lastName;
    private String firstNameOwner;
    private String lastNameOwner;
    private String photoOwner;
    private String title;
    private String body;
    private String photo100;
    @Ignore
    private List<Integer> chartActive;
    @Ignore
    private List<Attachment> attachment;



    public Integer getOut() {
        return out;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getReadState() {
        return readState;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getChartId() {
        return chartId;
    }

    public List<Attachment> getAttachment() {
        return attachment;
    }

    public String getPhoto100() {
        return photo100;
    }

    public List<Integer> getChartActive() {
        return chartActive;
    }

    public String getFirstNameOwner() {
        return firstNameOwner;
    }

    public String getLastNameOwner() {
        return lastNameOwner;
    }

    public String getPhotoOwner() {
        return photoOwner;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public void setDate(int data) {
        this.date = data;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstNameOwner(String firstNameOwner) {
        this.firstNameOwner = firstNameOwner;
    }

    public void setLastNameOwner(String lastNameOwner) {
        this.lastNameOwner = lastNameOwner;
    }

    public void setPhotoOwner(String photoOwner) {
        this.photoOwner = photoOwner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setChartId(int chartId) {
        this.chartId = chartId;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public void setChartActive(List<Integer> chartActive) {
        this.chartActive = chartActive;
    }

    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dialog holder = (Dialog) o;

        if (!(getDate().equals(holder.getDate()))) return false;
        if (!(getUserId().equals(holder.getUserId()))) return false;
        if (!getFirstName().equals(holder.getFirstName())) return false;
        return getLastName().equals(holder.getLastName());

    }

    @Override
    public int hashCode() {
        int result = getDate();
        result = 31 * result + getUserId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}


