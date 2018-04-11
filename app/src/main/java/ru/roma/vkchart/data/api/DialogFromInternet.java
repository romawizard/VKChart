package ru.roma.vkchart.data.api;


import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.roma.vkchart.data.api.model_response.SendMessageModelresponse;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.data.api.model_response.ItemMessage;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;
import ru.roma.vkchart.domain.providers.ApiProvider;
import ru.roma.vkchart.domain.entities.Dialog;

import ru.roma.vkchart.data.api.model_response.Attachment;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.api.model_response.DialogsHolder;
import ru.roma.vkchart.data.api.model_response.ListDialogs;
import ru.roma.vkchart.data.api.model_response.Message;
import ru.roma.vkchart.data.api.model_response.NameUser;
import ru.roma.vkchart.data.api.model_response.OwnerName;
import ru.roma.vkchart.utils.Keys;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.utils.Pagination;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ilan on 24.02.2018.
 */

public class DialogFromInternet implements ApiProvider {

    private Pagination<Dialog> dialogesPagination;
    private Pagination<ru.roma.vkchart.domain.entities.Message> messagePagination;
    private String token;

    public DialogFromInternet() {
        dialogesPagination = new Pagination<>(20);
        messagePagination = new Pagination<>(20);
        token = MyApplication.getInstance().getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE)
                .getString(Keys.TOKEN, null);
    }

    @Override
    public List<Dialog> getListDialogs() throws IOException {

        MyLog.log(" dialoges offset = " + dialogesPagination.getOffset());

        DialogModelResponse response = MyApplication.getInstance().getQuery().getDialog(dialogesPagination.getOffset(), token).execute().body();

        return dialogesPagination.next(parseToDialog(response));

    }

    @Override
    public List<ru.roma.vkchart.domain.entities.Message> getListMessages(int userId) throws Exception {

        MyLog.log("messages offset = " +messagePagination.getOffset());
        MessageModelResponse response = MyApplication.getInstance().getQuery().getMessages(userId,messagePagination.getOffset(),token)
                .execute().body();
        return messagePagination.next(parseToMessage(response));
    }

    @Override
    public Integer sendMessage(ru.roma.vkchart.domain.entities.Message message) throws Exception {

        String text = message.getBody();
        int id = message.getUserId();
//
//        try {
//            text = URLEncoder.encode(text, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            MyLog.log("кодировка не прошла");
//        }

        MyLog.log("send message = " );
        SendMessageModelresponse response = MyApplication.getInstance().getQuery().sendMessage(id,text,token)
                .execute().body();
        return response.getResponse();
    }

    private List<ru.roma.vkchart.domain.entities.Message> parseToMessage(MessageModelResponse response) {

        List<ru.roma.vkchart.domain.entities.Message> resultList = new ArrayList<>(20);

        ru.roma.vkchart.domain.entities.Message holder;

        List<ItemMessage> messages = response.getResponse().getItems();

        for ( ItemMessage item : messages){
            Integer userId = item.getUserId();
            Integer fromId = item.getFromId();
            Integer chatId = item.getChatId();
            Integer id = item.getId();
            Integer out = item.getOut();
            Integer date = item.getDate();
            Integer readState = item.getReadState();
            String body = item.getBody();
            List<Attachment> attachments = item.getAttachments();

            holder = new ru.roma.vkchart.domain.entities.Message();
            holder.setAttachments(attachments);
            holder.setBody(body);
            holder.setChatId(chatId);
            holder.setDate(date);
            holder.setFromId(fromId);
            holder.setUserId(userId);
            holder.setId(id);
            holder.setOut(out);
            holder.setReadState(readState);

            resultList.add(holder);
        }
        return resultList;
    }

    private List<Dialog> parseToDialog(DialogModelResponse body) {

        List<Dialog> resultList = new ArrayList<>(20);

        Dialog holder;

        ListDialogs listDialogs = body.getResponse().getDialogs();

        List<DialogsHolder> dialogs = listDialogs.getItems();

        for (DialogsHolder item : dialogs) {

            Message msg = item.getMessage();

            OwnerName ownerName = body.getResponse().getOwnreName();

            List<NameUser.ItemName> nameUser = body.getResponse().getName().getItemNames();

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

            for (NameUser.ItemName itemName : nameUser) {

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
            if (chartId !=0){
                holder.setUserId(0);
            }else {
                holder.setUserId(userId);
            }
            resultList.add(holder);

        }
        return resultList;
    }


}
