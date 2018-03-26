package ru.roma.vkchart.utils.dagger;

import dagger.Component;
import ru.roma.vkchart.ui.dialogs.DialogFragmentPresenter;

/**
 * Created by Ilan on 26.03.2018.
 */

@Component( modules = {ApiProvidermModule.class, DbProviderModule.class, StateModule.class})
public interface DialogComponent {

    void injectDialogPtresenter(DialogFragmentPresenter presenter);
}

