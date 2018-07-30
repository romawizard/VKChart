package ru.roma.vkchart.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.roma.vkchart.ui.fragment.DialogsFragment;
import ru.roma.vkchart.ui.fragment.MessageFragment;

/**
 * Created by Ilan on 26.03.2018.
 */
@Singleton
@Component(modules ={MainModule.class})
public interface AppComponent {

    void inject(DialogsFragment dialogFragment);

    void inject(MessageFragment messageFragment);
}

