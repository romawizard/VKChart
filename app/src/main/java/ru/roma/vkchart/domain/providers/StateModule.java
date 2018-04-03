package ru.roma.vkchart.domain.providers;

import dagger.Module;
import dagger.Provides;

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
