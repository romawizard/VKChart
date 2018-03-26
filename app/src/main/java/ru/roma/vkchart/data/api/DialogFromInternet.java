package ru.roma.vkchart.data.api;


import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.roma.vkchart.app.MyApplication;
import ru.roma.vkchart.domain.providers.DialogsProvider;
import ru.roma.vkchart.models.entities.Dialog;

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

public class DialogFromInternet implements DialogsProvider {

    Pagination<Dialog> pagination;


    public DialogFromInternet() {
        pagination = new Pagination<>(20);
    }

    @Override
    public List<Dialog> loadData() throws IOException {

        MyLog.log("offset = " + pagination.getOffset());

        String token = MyApplication.getInstance().getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE)
                .getString(Keys.TOKEN, null);

        DialogModelResponse response = MyApplication.getQuery().getDialog(pagination.getOffset(), token).execute().body();

        return pagination.next(parseToDialogHolder(response));

    }

    private List<Dialog> parseToDialogHolder(DialogModelResponse body) {

        List<Dialog> resultList = new ArrayList<>();

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
            holder.setUserId(userId);

            resultList.add(holder);

        }
        return resultList;
    }


}
