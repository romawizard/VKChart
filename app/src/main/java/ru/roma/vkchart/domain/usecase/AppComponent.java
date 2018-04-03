package ru.roma.vkchart.domain.usecase;

import dagger.Component;
import ru.roma.vkchart.domain.datacource.DataSourceModule;
import ru.roma.vkchart.domain.providers.ApiProvidermModule;
import ru.roma.vkchart.domain.providers.DbProviderModule;
import ru.roma.vkchart.domain.providers.StateModule;
import ru.roma.vkchart.domain.usecase.AsynUseCaseModule;
import ru.roma.vkchart.domain.usecase.UseCaseModule;
import ru.roma.vkchart.ui.presenter.DialogPresenter;
import ru.roma.vkchart.ui.presenter.MessagePresenter;

/**
 * Created by Ilan on 26.03.2018.
 */

@Component( modules = {ApiProvidermModule.class, DbProviderModule.class, StateModule.class
        , DataSourceModule.class ,UseCaseModule.class, AsynUseCaseModule.class})
public interface AppComponent {

    void injectDialogPresenter(DialogPresenter presenter);

    void injectMessagePresenter(MessagePresenter presenter);
}

