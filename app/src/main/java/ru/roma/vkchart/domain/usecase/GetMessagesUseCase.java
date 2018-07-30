package ru.roma.vkchart.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import ru.roma.vkchart.data.repository.MessageRepository;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;

/**
 * Created by Ilan on 01.04.2018.
 */

public class GetMessagesUseCase extends UseCase<List<MessageUIItem>> {

    private final MessageRepository messageRepository;
    private Integer userId = 0;

    @Inject
    public GetMessagesUseCase(@Named("executor_thread") Scheduler executorThread,
                              @Named("ui_thread") Scheduler uiThread, MessageRepository messageRepository) {
        super(executorThread, uiThread);
        this.messageRepository = messageRepository;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Observable<List<MessageUIItem>> createObservableUseCase() {
        return messageRepository.getListMessages(userId);
    }
}
