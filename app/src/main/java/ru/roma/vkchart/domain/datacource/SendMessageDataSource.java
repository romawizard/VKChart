package ru.roma.vkchart.domain.datacource;

import android.os.Bundle;

import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.providers.ApiProvider;
import ru.roma.vkchart.domain.providers.DbProvider;
import ru.roma.vkchart.domain.providers.DeviceStateProvider;

/**
 * Created by Ilan on 08.04.2018.
 */

public class SendMessageDataSource implements DataSource<Integer, Message> {

    private DeviceStateProvider stateProvider;
    private ApiProvider apiProvider;
    private DbProvider dbProvider;

    public SendMessageDataSource(DeviceStateProvider stateProvider, ApiProvider apiProvider, DbProvider dbProvider) {
        this.stateProvider = stateProvider;
        this.apiProvider = apiProvider;
        this.dbProvider = dbProvider;
    }

    @Override
    public Integer getData(Message message) throws Exception {
        return apiProvider.sendMessage(message);
    }

    @Override
    public void saveData(Integer integer) {

    }
}
