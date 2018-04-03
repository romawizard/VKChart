package ru.roma.vkchart.domain.providers;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.data.data_base.DataBaseWorker;

/**
 * Created by Ilan on 26.03.2018.
 */

@Module
public class DbProviderModule {

    @Provides
//    @Naned("DB")
    DbProvider provideDbWorker(){
        return new DataBaseWorker();
    }
}
