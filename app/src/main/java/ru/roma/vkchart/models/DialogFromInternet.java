package ru.roma.vkchart.models;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.roma.vkchart.MyApplication;
import ru.roma.vkchart.data_base.DataBaseWorker;
import ru.roma.vkchart.holder.DialogsHolder;
import ru.roma.vkchart.holder.DataDialogs;
import ru.roma.vkchart.presenters.CallbackModel;
import ru.roma.vkchart.model_response.Attachment;
import ru.roma.vkchart.model_response.DialogModelResponse;
import ru.roma.vkchart.model_response.Dialogs;
import ru.roma.vkchart.model_response.ListDialogs;
import ru.roma.vkchart.model_response.Message;
import ru.roma.vkchart.model_response.NameUser;
import ru.roma.vkchart.model_response.OwnerName;
import ru.roma.vkchart.utils.Keys;
import ru.roma.vkchart.utils.MyLog;
import ru.roma.vkchart.utils.Pagination;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ilan on 24.02.2018.
 */

public class DialogFromInternet implements DialogModel {

    Pagination<DialogsHolder> pagination;
    private int count = -1;
    private  boolean loading = false;

    public DialogFromInternet() {
        pagination = new Pagination(20);
    }

    @Override
    public void loadData(final int lastPosition, final CallbackModel callback) {

        if (lastPosition != count && !loading) {

            loading = true;

            int offset = pagination.getOffset();


            String token = MyApplication.getInstance().getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE).getString(Keys.TOKEN, null);

            MyApplication.getQuery().getDialog(offset, token).enqueue(new Callback<DialogModelResponse>() {
                @Override
                public void onResponse(Call<DialogModelResponse> call, Response<DialogModelResponse> response) {

                    count = response.body().getResponse().getDialogs().getCount();

                    List<DialogsHolder> loadedList = parseToDialogHolder(response.body());


                    List result = pagination.next(loadedList);
                    if (pagination.isSuccess()) {
                        callback.successfull(result);
                    } else {
                        loading = false;
                        loadData(0, callback);
                    }

                    loading = false;
                    MyLog.log(" dialog load data is success");
                }

                @Override
                public void onFailure(Call<DialogModelResponse> call, Throwable t) {
                    MyLog.log("onFailure in getDialog retrofit ");
                    callback.failed("ошибка во время загрузки данных");
                    t.printStackTrace();
                    loading = false;
                }
            });
        }
    }

    private List<DialogsHolder> parseToDialogHolder(DialogModelResponse body) {

        List<DialogsHolder> resultList = new ArrayList<>();

        DialogsHolder holder;

        ListDialogs listDialogs = body.getResponse().getDialogs();

        List<Dialogs> dialogs = listDialogs.getItems();

        for (Dialogs item : dialogs) {

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
            holder = new DialogsHolder();

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
