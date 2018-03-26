package ru.roma.vkchart.utils.dagger;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.domain.providers.CheckIntennet;
import ru.roma.vkchart.domain.providers.DeviceStateProvider;

/**
 * Created by Ilan on 25.03.2018.
 */
@Module
public class StateModule {

    @Provides
    DeviceStateProvider provideDeviceStateProvider(){
        return new CheckIntennet();
    }
}
