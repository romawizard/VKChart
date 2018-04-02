package ru.roma.vkchart.utils.dagger;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.data.api.DialogFromInternet;
import ru.roma.vkchart.domain.providers.ApiProvider;

/**
 * Created by Ilan on 26.03.2018.
 */
@Module
public class ApiProvidermModule {

    @Provides
//    @Naned("api")
    ApiProvider provideDialogFromInternet(){
        return new DialogFromInternet();
    }
}
