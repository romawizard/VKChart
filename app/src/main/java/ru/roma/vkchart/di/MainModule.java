package ru.roma.vkchart.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.roma.vkchart.data.api.ApiVK;
import ru.roma.vkchart.data.api.ServerResponseToEntitiesMapper;
import ru.roma.vkchart.data.data_base.DialogDao;
import ru.roma.vkchart.data.data_base.MessageDao;
import ru.roma.vkchart.data.repository.DialogRepository;
import ru.roma.vkchart.data.repository.DialogRepositoryImpl;
import ru.roma.vkchart.data.repository.MessageRepository;
import ru.roma.vkchart.data.repository.MessageRepositoryImpl;
import ru.roma.vkchart.ui.MyApplication;

@Module
public class MainModule {

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("executor_thread")
    io.reactivex.Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    DialogRepository getDialogRepository(ApiVK apiVK, ServerResponseToEntitiesMapper mapper, DialogDao dialogDao){
        return new DialogRepositoryImpl(apiVK, dialogDao, mapper);
    }

    @Provides
    @Singleton
    MessageRepository getMessageRepository(ApiVK apiVK, ServerResponseToEntitiesMapper mapper, MessageDao messageDao){
        return new MessageRepositoryImpl(apiVK,messageDao,mapper);
    }

    @Provides
    @Singleton
    ApiVK getApiVK(){
        return MyApplication.getInstance().getApiVK();
    }

    @Provides
    @Singleton
    DialogDao getDialogDao(){
        return MyApplication.getInstance().getDataBase().dialogDao();
    }

    @Provides
    @Singleton
    MessageDao getMessageDao(){
        return MyApplication.getInstance().getDataBase().messageDao();
    }

}
