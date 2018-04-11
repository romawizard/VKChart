package ru.roma.vkchart.domain.providers;

import android.os.Bundle;

import java.util.List;

import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 24.03.2018.
 */

public interface ApiProvider {

    List<Dialog> getListDialogs() throws Exception;

    List<Message> getListMessages(int userId) throws Exception;

    Integer sendMessage(Message message) throws Exception;
}
