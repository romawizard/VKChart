package ru.roma.vkchart.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import ru.roma.vkchart.domain.entities.Message;
import ru.roma.vkchart.domain.usecase.AsynUseCase;
import ru.roma.vkchart.domain.usecase.LoadMessageUseCase;
import ru.roma.vkchart.domain.usecase.SendMessageUseCase;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.ui.fragment.View;

/**
 * Created by Ilan on 01.04.2018.
 */

public class MessagePresenter {

    @Inject
    LoadMessageUseCase loadMessageUseCase;
    @Inject
    AsynUseCase asynUseCase;
    @Inject
    SendMessageUseCase sendmessageUseCase;
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
                    if (view != null) {
                        view.updateList(messages);
                    }
                    loading = false;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    if (view != null) {
                        view.showError("ошибка при загрузке сообщений");
                    }
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

    public void sendMessage(final Message message) {
        if (!loading){
            loading = true;
            asynUseCase.wrap(sendmessageUseCase,message).subscribe(new Observer<Integer>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull Integer integer) {
                    message.setId(integer);
                    message.setSent(true);
                    view.update();
                    loading = false;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    message.setErorr(true);
                    view.update();
                    view.showError("ощибка при отправке сообщения");
                    loading = false;
                }

                @Override
                public void onComplete() {
                    loading = false;
                }
            });
        }
    }
}
