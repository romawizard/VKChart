package ru.roma.vkchart.models;

import ru.roma.vkchart.presenters.CallbackModel;

/**
 * Created by Ilan on 28.02.2018.
 */

public interface DialogModel {

    void  loadData(int offset,CallbackModel callback);
}
