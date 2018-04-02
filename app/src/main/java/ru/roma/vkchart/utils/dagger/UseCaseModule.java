package ru.roma.vkchart.utils.dagger;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.domain.datacource.DataSource;
import ru.roma.vkchart.domain.datacource.DataSourceLessArgument;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.usecase.LoadDialogsUseCase;
import ru.roma.vkchart.domain.usecase.LoadMessageUseCase;

/**
 * Created by Ilan on 31.03.2018.
 */
@Module
public class UseCaseModule {

    @Provides
    LoadDialogsUseCase provideLoadDialogsUseCase(DataSourceLessArgument<List<Dialog>> dataSource){
        return  new LoadDialogsUseCase(dataSource);
    }

    @Provides
    LoadMessageUseCase provideLoadMessageUseCase(DataSource<List<Message>,Integer> dataSource){
        return  new LoadMessageUseCase(dataSource);
    }
}
