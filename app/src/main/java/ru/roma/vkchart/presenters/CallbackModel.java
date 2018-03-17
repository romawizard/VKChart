package ru.roma.vkchart.presenters;

import java.util.List;

import ru.roma.vkchart.holder.DataDialogs;

/**
 * Created by Ilan on 27.02.2018.
 */

public interface CallbackModel {

    void successfull(List<DataDialogs> dataDialogses);
    void failed(String message);
}
