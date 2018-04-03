package ru.roma.vkchart.domain.providers;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.data.api.DialogFromInternet;

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
