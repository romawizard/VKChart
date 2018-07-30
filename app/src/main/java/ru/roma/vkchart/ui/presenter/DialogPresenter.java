package ru.roma.vkchart.ui.presenter;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.usecase.GetDialogsUseCase;


/**
 * Created by Ilan on 24.02.2018.
 */

public class DialogPresenter extends Presenter<DialogPresenter.DialogView> {

    private GetDialogsUseCase getDialogsUseCase;

    @Inject
    public DialogPresenter(GetDialogsUseCase getDialogsUseCase) {
        this.getDialogsUseCase = getDialogsUseCase;
    }

    public void getDialogs(int offset) {
        getView().showLoading();
        getDialogsUseCase.setOffset(offset);
        getDialogsUseCase.execute(new DisposableObserver<List<Dialog>>() {
            @Override
            public void onNext(List<Dialog> dialogs) {
                getView().updateListDialogs(dialogs);
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getView().showError(e.getMessage());
                getView().hideLoading();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    @Override
    public void detach() {
        getDialogsUseCase.dispose();
        setView(null);
    }

    public interface DialogView extends Presenter.View{
         void updateListDialogs(List<Dialog> dialogs);
     }

}
