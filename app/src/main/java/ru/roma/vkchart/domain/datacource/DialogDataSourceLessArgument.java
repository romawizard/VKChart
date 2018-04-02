package ru.roma.vkchart.domain.datacource;

import java.util.List;

import javax.inject.Inject;

import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.providers.ApiProvider;
import ru.roma.vkchart.domain.providers.DbProvider;
import ru.roma.vkchart.domain.providers.DeviceStateProvider;

/**
 * Created by Ilan on 31.03.2018.
 */

public class DialogDataSourceLessArgument implements DataSourceLessArgument<List<Dialog>> {

    DeviceStateProvider stateProvider;
    ApiProvider apiProvider;
    DbProvider dbProvider;

    @Inject
    public DialogDataSourceLessArgument(DeviceStateProvider stateProvider, ApiProvider apiProvider, DbProvider dbProvider) {
        this.stateProvider = stateProvider;
        this.apiProvider = apiProvider;
        this.dbProvider = dbProvider;
    }

    @Override
    public List<Dialog> getData() throws Exception {
        if (stateProvider.hasInternetConection()) {
            return apiProvider.getListDialogs();
        } else {
            return dbProvider.getListDialogs();
        }
    }

    @Override
    public void saveData(final List<Dialog> dialogs) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                dbProvider.saveListDialogs(dialogs);

            }
        });
        t.start();
    }
}
