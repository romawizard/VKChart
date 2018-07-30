package ru.roma.vkchart.ui.ui_item;

import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 04.06.2018.
 */

public class MessageUIItem extends Message {

    private boolean check = false;

    public MessageUIItem() {
    }

    public MessageUIItem(String s, int userId) {
        super(s,userId);
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
