package ru.roma.vkchart.di;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.data.repository.DeviceStateProvider;
import ru.roma.vkchart.data.repository.DeviceStateProviderImpl;

/**
 * Created by Ilan on 25.03.2018.
 */
@Module
public class StateModule {

    @Provides
    DeviceStateProvider provideDeviceStateProvider(){
        return new DeviceStateProviderImpl();
    }
}
