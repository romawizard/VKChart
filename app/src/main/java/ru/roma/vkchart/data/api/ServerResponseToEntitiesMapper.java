package ru.roma.vkchart.data.api;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.roma.vkchart.data.api.model_response.Attachment;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.api.model_response.DialogsHolder;
import ru.roma.vkchart.data.api.model_response.GetUserModelResponse;
import ru.roma.vkchart.data.api.model_response.ItemMessage;
import ru.roma.vkchart.data.api.model_response.ListDialogs;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;
import ru.roma.vkchart.data.api.model_response.NameUser;
import ru.roma.vkchart.data.api.model_response.OwnerName;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.User;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;

/**
 * Created by Ilan on 27.05.2018.
 */
@Singleton
public class ServerResponseToEntitiesMapper {

    @Inject
    public ServerResponseToEntitiesMapper() {
    }

    public List<MessageUIItem> map(MessageModelResponse response) {

        List<MessageUIItem> resultList = new ArrayList<>(20);

       MessageUIItem holder;

        List<ItemMessage> messages = response.getResponse().getItems();

        for (ItemMessage item : messages) {
            Integer userId = item.getUserId();
            Integer fromId = item.getFromId();
            Integer chatId = item.getChatId();
            Integer id = item.getId();
            Integer out = item.getOut();
            Integer date = item.getDate();
            Integer readState = item.getReadState();
            Integer messageId = item.getId();
            String body = item.getBody();
            List<Attachment> attachments = item.getAttachments();

            holder = new MessageUIItem();
            holder.setAttachments(attachments);
            holder.setBody(body);
            holder.setChatId(chatId);
            holder.setDate(date);
            holder.setFromId(fromId);
            holder.setUserId(userId);
            holder.setId(id);
            holder.setOut(out);
            holder.setReadState(readState);
            holder.setMessageId(messageId);

            resultList.add(holder);
        }
        return resultList;
    }

    public List<Dialog> map(DialogModelResponse response) {

        List<Dialog> resultList = new ArrayList<>(20);

        Dialog holder;

        ListDialogs listDialogs = response.getResponse().getDialogs();

        List<DialogsHolder> dialogs = listDialogs.getItems();

        for (DialogsHolder item : dialogs) {

            ru.roma.vkchart.data.api.model_response.Message msg = item.getMessage();

            OwnerName ownerName = response.getResponse().getOwnreName();

            List<NameUser.ItemName> nameUsers = response.getResponse().getName().getItemNames();

            int messageId = msg.getId();
            int out = msg.getOut();
            int count = listDialogs.getCount();
            int readState = msg.getReadState();
            int online = 0;
            int date = msg.getDate();
            int userId = msg.getUserId();
            int userCount = msg.getUsersCount();
            int adminId = msg.getAdminId();
            int chartId = msg.getChatId();
            String title = msg.getTitle();
            String bodyMsg = msg.getBody();
            List<Attachment> attachment = msg.getAttachments();
            List<Integer> chardActive = msg.getChatActive();
            String firstNameOwner = ownerName.getFirstName();
            String lastNameOwner = ownerName.getLastName();
            String photo100Owner = ownerName.getPhoto100();
            String firstName = "";
            String lastName = "";
            String photo = msg.getPhoto100();

            for (NameUser.ItemName itemName : nameUsers) {

                int id = itemName.getId();
                if (id == userId) {
                    firstName = itemName.getFirstName();
                    lastName = itemName.getLastName();
                    online = itemName.getOnline();
                    if (TextUtils.isEmpty(photo)) {
                        photo = itemName.getPhoto100();
                    }
                }
            }
            holder = new Dialog();

            holder.setId(messageId);
            holder.setAdminId(adminId);
            holder.setAttachment(attachment);
            holder.setBody(bodyMsg);
            holder.setChartActive(chardActive);
            holder.setCount(count);
            holder.setChartId(chartId);
            holder.setDate(date);
            holder.setFirstName(firstName);
            holder.setFirstNameOwner(firstNameOwner);
            holder.setLastName(lastName);
            holder.setLastNameOwner(lastNameOwner);
            holder.setReadState(readState);
            holder.setOnline(online);
            holder.setPhoto100(photo);
            holder.setPhotoOwner(photo100Owner);
            holder.setOut(out);
            holder.setTitle(title);
            holder.setUserCount(userCount);
            if (chartId != 0) {
                holder.setUserId(0);
            } else {
                holder.setUserId(userId);
            }
            resultList.add(holder);

        }
        return resultList;
    }

    public User map(GetUserModelResponse response){

        GetUserModelResponse.Response raw = response.getResponse().get(0);
        User user = new User();

        user.setId(raw.getId());
        user.setFirstName(raw.getFirstName());
        user.setLastName(raw.getLastName());
        user.setSex(raw.getSex());
        user.setBdate(raw.getBdate());
        user.setCity(raw.getCity().getTitle());
        user.setCountry(raw.getCountry().getTitle());
        user.setFriendStatus(raw.getFriendStatus());
        user.setStatus(raw.getStatus());
        user.setRelation(raw.getRelation());
        user.setOnline(raw.getOnline());

        return user;
    }
}