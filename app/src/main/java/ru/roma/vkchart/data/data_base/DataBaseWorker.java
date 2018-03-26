package ru.roma.vkchart.data.data_base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import ru.roma.vkchart.app.MyApplication;
import ru.roma.vkchart.domain.providers.DialogsProvider;
import ru.roma.vkchart.models.entities.Dialog;
import ru.roma.vkchart.utils.MyLog;

/**
 * Created by Ilan on 15.03.2018.
 */

public class DataBaseWorker implements DialogsProvider {

    public DataBaseWorker() {
    }

    public void insert(final List<Dialog> list){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MyLog.log("write to db");
                DialogDao dialogDao =  MyApplication.getDataBase().dialogDao();
                dialogDao.insert(list);
            }
        });

    }

    @Override
    public List<Dialog> loadData() throws Exception {
        return  new ArrayList();

    }
}
