package ru.roma.vkchart.holder;

import java.util.List;

import ru.roma.vkchart.model_response.Attachment;

/**
 * Created by Ilan on 03.03.2018.
 */

public interface DataDialogs {

    Integer getOut();

    Integer getCount();

    Integer getReadState();

    Integer getUserCount();

    Integer getDate();

    Integer getUserId();

    Integer getAdminId();

    String getFirstName();

    String getLastName();

    String getTitle();

    String getBody();

    Integer getChartId();

    List<Attachment> getAttachment();

    String getPhoto100();

    List<Integer> getChartActive();

    String getFirstNameOwner();

    String getLastNameOwner();

    String getPhotoOwner();

    Integer getOnline();
}
