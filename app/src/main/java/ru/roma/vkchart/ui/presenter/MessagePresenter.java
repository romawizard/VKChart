package ru.roma.vkchart.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.roma.vkchart.domain.usecase.GetMessagesUseCase;
import ru.roma.vkchart.domain.usecase.SendMessageUseCase;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;

/**
 * Created by Ilan on 01.04.2018.
 */

public class MessagePresenter extends Presenter<MessagePresenter.MessageView > {

    private GetMessagesUseCase getMessagesUseCase;
    private SendMessageUseCase sendmessageUseCase;

    @Inject
    public MessagePresenter(GetMessagesUseCase getMessagesUseCase, SendMessageUseCase sendmessageUseCase) {
        this.getMessagesUseCase = getMessagesUseCase;
        this.sendmessageUseCase = sendmessageUseCase;
    }

    public void getMessages(int userId) {
        getMessagesUseCase.setUserId(userId);
        getView().showLoading();
        getMessagesUseCase.execute(new DisposableObserver<List<MessageUIItem>>() {
            @Override
            public void onNext(List<MessageUIItem> messageUIItems) {
                getView().updateList(messageUIItems);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getView().showError("ошибка при загрузке сообщений");
                getView().hideLoading();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }



    public void sendMessage(final MessageUIItem message) {
        sendmessageUseCase.addMessage(message);
        sendmessageUseCase.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                message.setId(integer);
                message.setSent(true);
                getView().update();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                message.setError(true);
                getView().update();
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void detach() {
        setView(null);
        getMessagesUseCase.dispose();
        sendmessageUseCase.dispose();
    }

    public interface MessageView extends Presenter.View{

        void updateList(List<MessageUIItem> messageUIItems);
    }
}
