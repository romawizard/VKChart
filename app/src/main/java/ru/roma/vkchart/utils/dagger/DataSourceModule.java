package ru.roma.vkchart.utils.dagger;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.domain.datacource.DataSource;
import ru.roma.vkchart.domain.datacource.DataSourceLessArgument;
import ru.roma.vkchart.domain.datacource.DialogDataSourceLessArgument;
import ru.roma.vkchart.domain.datacource.MessageDataSource;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.providers.ApiProvider;
import ru.roma.vkchart.domain.providers.DbProvider;
import ru.roma.vkchart.domain.providers.DeviceStateProvider;

/**
 * Created by Ilan on 31.03.2018.
 */

@Module
public class DataSourceModule {

    @Provides
    DataSourceLessArgument<List<Dialog>> provideDataSource(DeviceStateProvider stateProvider, ApiProvider apiProvider, DbProvider dbProvider){
        return  new DialogDataSourceLessArgument(stateProvider,apiProvider,dbProvider);
    }

    @Provides
    DataSource<List<Message>,Integer> provideMessageDataSource(DeviceStateProvider stateProvider, ApiProvider apiProvider, DbProvider dbProvider){
        return  new MessageDataSource(stateProvider,apiProvider,dbProvider);
    }
}
