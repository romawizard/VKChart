package ru.roma.vkchart.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.roma.vkchart.ui.ui_item.MessageUIItem;

public interface MessageRepository {

    Observable<List<MessageUIItem>> getListMessages(Integer userId);
}
