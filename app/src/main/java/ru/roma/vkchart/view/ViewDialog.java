package ru.roma.vkchart.view;

import java.util.List;

import ru.roma.vkchart.holder.DataDialogs;

/**
 * Created by Ilan on 24.02.2018.
 */

public interface ViewDialog {

    void updateList(List<DataDialogs> dialogsList);
    void failed(String message);
}
