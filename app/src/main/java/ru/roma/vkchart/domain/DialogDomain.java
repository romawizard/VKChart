package ru.roma.vkchart.domain;

import java.util.List;

import javax.inject.Inject;

import ru.roma.vkchart.data.data_base.DataBaseWorker;
import ru.roma.vkchart.domain.providers.ApiProvider;
import ru.roma.vkchart.domain.providers.DeviceStateProvider;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.utils.dagger.Naned;

/**
 * Created by Ilan on 13.03.2018.
 */

public class DialogDomain {


    private DeviceStateProvider stateProvider;
    private ApiProvider apiProvider;
    private ApiProvider dbProvider;

    @Inject
    public DialogDomain(DeviceStateProvider stateProvider, @Naned("api") ApiProvider apiProvider
            ,@Naned("DB") ApiProvider dbProvider) {
        this.stateProvider = stateProvider;
        this.apiProvider = apiProvider;
        this.dbProvider = dbProvider;

    }

    public List<Dialog> getListDialog() throws Exception {

        if (stateProvider.hasInternetConection()) {
             List<Dialog> dialogs =  apiProvider.getListDialogs();

            return dialogs;
        } else {
            return dbProvider.getListDialogs();
        }

    }

}
