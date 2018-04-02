package ru.roma.vkchart.domain.providers;

import java.util.List;

import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 31.03.2018.
 */

public interface DbProvider {

    List<Dialog> getListDialogs() throws Exception;

    void saveListDialogs(List<Dialog> dialogs);

    List<Message> getListMessage(int userId) throws Exception;

    void saveListMessages (List<Message> messages);
}
