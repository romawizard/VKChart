package ru.roma.vkchart.domain.usecase;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ru.roma.vkchart.domain.datacource.DataSource;
import ru.roma.vkchart.domain.datacource.DataSourceLessArgument;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;

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

    @Provides
    SendMessageUseCase provideSendMessageUseCase(DataSource<Integer,Message> dataSource){
        return new SendMessageUseCase(dataSource);
    }
}
