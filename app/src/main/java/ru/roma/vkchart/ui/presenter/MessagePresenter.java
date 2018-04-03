package ru.roma.vkchart.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.usecase.AsynUseCase;
import ru.roma.vkchart.domain.usecase.LoadMessageUseCase;
import ru.roma.vkchart.ui.fragment.View;

/**
 * Created by Ilan on 01.04.2018.
 */

public class MessagePresenter {

    @Inject
    LoadMessageUseCase loadMessageUseCase;
    @Inject
    AsynUseCase asynUseCase;
    private View view;
    private boolean loading = false;

    public MessagePresenter(View view) {
        this.view = view;
        MyApplication.getInstance().getAppComponent().injectMessagePresenter(this);
    }

    public void getMessages(int userId) {
        if (!loading) {
            loading = true;
            asynUseCase.wrap(loadMessageUseCase, userId).subscribe(new Observer<List<Message>>() {


                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull List<Message> messages) {
                    view.updateList(messages);
                    loading = false;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    view.showError("ошибка при загрузке сообщений");
                    loading = false;
                }

                @Override
                public void onComplete() {
                    loading = false;
                }
            });
        }

    }

    public void detach() {
        view = null;
    }
}
