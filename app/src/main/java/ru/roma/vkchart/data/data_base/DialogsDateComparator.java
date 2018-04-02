package ru.roma.vkchart.data.data_base;

import java.util.Comparator;

import ru.roma.vkchart.domain.entities.Dialog;

/**
 * Created by Ilan on 27.03.2018.
 */

public class DialogsDateComparator implements Comparator<Dialog> {

    @Override
    public int compare(Dialog dialog, Dialog t1) {
        return dialog.getDate()>t1.getDate() ? -1 : dialog.getDate() == t1.getDate() ? 0 : 1;
    }
}
