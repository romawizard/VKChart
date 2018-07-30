package ru.roma.vkchart.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import ru.roma.vkchart.data.repository.DialogRepository;
import ru.roma.vkchart.domain.entities.Dialog;

/**
 * Created by Ilan on 31.03.2018.
 */

public class GetDialogsUseCase extends UseCase<List<Dialog>> {

    private final DialogRepository dialogRepository;
    private int offset;

    @Inject
    public GetDialogsUseCase(@Named("executor_thread") Scheduler executorThread,
                      @Named("ui_thread")Scheduler uiThread, DialogRepository dialogRepository) {
        super(executorThread, uiThread);
        this.dialogRepository = dialogRepository;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    protected Observable<List<Dialog>> createObservableUseCase() {
        return dialogRepository.getDialog(offset);
    }
}
