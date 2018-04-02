package ru.roma.vkchart.utils.dagger;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.domain.usecase.AsynUseCase;
import ru.roma.vkchart.domain.usecase.RxAsynUseCase;

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
