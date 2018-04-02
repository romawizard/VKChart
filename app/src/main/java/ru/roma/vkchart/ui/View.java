package ru.roma.vkchart.ui;

import java.util.List;

import ru.roma.vkchart.domain.entities.Dialog;

/**
 * Created by Ilan on 24.02.2018.
 */

public interface View<T> {

    void updateList(List<T> list);
    void showError(String message);
}
