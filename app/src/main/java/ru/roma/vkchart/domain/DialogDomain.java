package ru.roma.vkchart.domain;

import java.util.List;

import javax.inject.Inject;

import ru.roma.vkchart.domain.providers.DeviceStateProvider;
import ru.roma.vkchart.domain.providers.DialogsProvider;
import ru.roma.vkchart.models.entities.Dialog;
import ru.roma.vkchart.utils.Pagination;
import ru.roma.vkchart.utils.dagger.Naned;

/**
 * Created by Ilan on 13.03.2018.
 */

public class DialogDomain {


    private DeviceStateProvider stateProvider;
    private DialogsProvider apiProvider;
    private DialogsProvider dbProvider;

    @Inject
    public DialogDomain(DeviceStateProvider stateProvider, @Naned("api") DialogsProvider apiProvider
            ,@Naned("DB") DialogsProvider dbProvider) {
        this.stateProvider = stateProvider;
        this.apiProvider = apiProvider;
        this.dbProvider = dbProvider;

    }

    public List<Dialog> getListDialog() throws Exception {

        if (stateProvider.hasInternetConection()) {
            return apiProvider.loadData();
        } else {
            return dbProvider.loadData();
        }

    }

}
