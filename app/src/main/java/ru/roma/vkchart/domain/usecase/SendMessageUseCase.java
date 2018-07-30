package ru.roma.vkchart.domain.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import ru.roma.vkchart.data.repository.DialogRepository;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;

/**
 * Created by Ilan on 08.04.2018.
 */

public class SendMessageUseCase extends UseCase<Integer> {

    private final DialogRepository dialogRepository;

    @Inject
    public SendMessageUseCase(@Named("executor_thread") Scheduler executorThread,
                              @Named("ui_thread") Scheduler uiThread, DialogRepository dialogRepository) {
        super(executorThread, uiThread);
        this.dialogRepository = dialogRepository;
    }

    @Override
    protected Observable<Integer> createObservableUseCase() {
        return null;
    }

    public void addMessage(MessageUIItem message) {

    }
}
