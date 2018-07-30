package ru.roma.vkchart.data.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.roma.vkchart.data.api.ApiVK;
import ru.roma.vkchart.data.api.ServerResponseToEntitiesMapper;
import ru.roma.vkchart.data.api.model_response.MessageModelResponse;
import ru.roma.vkchart.data.data_base.MessageDao;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;
import ru.roma.vkchart.utils.Keys;

import static android.content.Context.MODE_PRIVATE;

public class MessageRepositoryImpl implements MessageRepository {

    private ApiVK vkServer;
    private MessageDao messageDao;
    private ServerResponseToEntitiesMapper mapper;
    private final String TOKEN;

    public MessageRepositoryImpl(ApiVK vkServer, MessageDao messageDao, ServerResponseToEntitiesMapper mapper) {
        this.vkServer = vkServer;
        this.messageDao = messageDao;
        this.mapper = mapper;
        TOKEN = MyApplication.getInstance().getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE)
                .getString(Keys.TOKEN, null);
    }

    @Override
    public Observable<List<MessageUIItem>> getListMessages(Integer userId) {
        return vkServer.getMessages(userId,0,TOKEN).map(new Function<MessageModelResponse, List<MessageUIItem>>() {
            @Override
            public List<MessageUIItem> apply(MessageModelResponse messageModelResponse) throws Exception {
                return mapper.map(messageModelResponse);
            }
        });
    }
}
