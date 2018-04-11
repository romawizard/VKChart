package ru.roma.vkchart.utils;

import java.util.Comparator;

import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 09.04.2018.
 */

public class MessageDateComparator implements Comparator<Message> {
    @Override
    public int compare(Message message, Message t1) {
        return message.getDate()>t1.getDate() ? -1 : message.getDate() == t1.getDate() ? 0 : 1;
    }
}
