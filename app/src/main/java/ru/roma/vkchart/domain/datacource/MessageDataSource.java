package ru.roma.vkchart.domain.datacource;

import java.util.List;

import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.providers.ApiProvider;
import ru.roma.vkchart.domain.providers.DbProvider;
import ru.roma.vkchart.domain.providers.DeviceStateProvider;

/**
 * Created by Ilan on 02.04.2018.
 */

public class MessageDataSource implements DataSource<List<Message>,Integer> {

    private DeviceStateProvider stateProvider;
    private ApiProvider apiProvider;
    private DbProvider dbProvider;

    public MessageDataSource(DeviceStateProvider stateProvider, ApiProvider apiProvider, DbProvider dbProvider) {
        this.stateProvider = stateProvider;
        this.apiProvider = apiProvider;
        this.dbProvider = dbProvider;
    }

    @Override
    public List<Message> getData(Integer userId) throws Exception {
        if (stateProvider.hasInternetConection()){
            return apiProvider.getListMessages(userId);
        }else {
            return dbProvider.getListMessage(userId);
        }
    }

    @Override
    public void saveData(List<Message> messages) {

    }
}
