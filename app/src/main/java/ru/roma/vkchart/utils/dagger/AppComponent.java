package ru.roma.vkchart.utils.dagger;

import dagger.Component;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.ui.dialogs.DialogPresenter;
import ru.roma.vkchart.ui.messages.MessagePresenter;

/**
 * Created by Ilan on 26.03.2018.
 */

@Component( modules = {ApiProvidermModule.class, DbProviderModule.class, StateModule.class
        , DataSourceModule.class ,UseCaseModule.class, AsynUseCaseModule.class})
public interface AppComponent {

    void injectDialogPresenter(DialogPresenter presenter);

    void injectMessagePresenter(MessagePresenter presenter);
}

