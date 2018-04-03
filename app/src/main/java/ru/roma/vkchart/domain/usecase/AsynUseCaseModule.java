package ru.roma.vkchart.domain.usecase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilan on 02.04.2018.
 */

@Module
public class AsynUseCaseModule {

    @Provides
    AsynUseCase provideAsynUseCase(){
        return new RxAsynUseCase();
    }
}
