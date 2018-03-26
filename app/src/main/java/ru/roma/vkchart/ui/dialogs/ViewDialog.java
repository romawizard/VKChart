package ru.roma.vkchart.ui.dialogs;

import java.util.List;

import ru.roma.vkchart.models.entities.Dialog;

/**
 * Created by Ilan on 24.02.2018.
 */

public interface ViewDialog {

    void updateList(List<Dialog> dialogsList);
    void showError(String message);
}
