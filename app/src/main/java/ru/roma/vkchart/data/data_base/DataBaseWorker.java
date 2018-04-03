package ru.roma.vkchart.data.data_base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.providers.DbProvider;
import ru.roma.vkchart.utils.MyLog;

/**
 * Created by Ilan on 15.03.2018.
 */

public class DataBaseWorker implements DbProvider {

    public DataBaseWorker() {
    }

    @Override
    public List<Dialog> getListDialogs() throws Exception {

        MyLog.log("read from db");
        DialogDao dialogDao = MyApplication.getInstance().getDataBase().dialogDao();
        List<Dialog> dialogs =  dialogDao.getAll();
        Collections.sort(dialogs, new DialogsDateComparator());
        return dialogs;
    }

    @Override
    public void saveListDialogs(List<Dialog> dialogs) {
        MyLog.log("write to db");
        DialogDao dialogDao = MyApplication.getInstance().getDataBase().dialogDao();
        dialogDao.insert(dialogs);
    }

    @Override
    public List<Message> getListMessage(int userId) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void saveListMessages(List<Message> messages) {

    }
}
