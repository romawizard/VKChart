package ru.roma.vkchart.data_base;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import ru.roma.vkchart.MyApplication;
import ru.roma.vkchart.holder.DialogsHolder;
import ru.roma.vkchart.utils.MyLog;

/**
 * Created by Ilan on 15.03.2018.
 */

public class DataBaseWorker {

   public void insert(final List<DialogsHolder> list){
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
}
