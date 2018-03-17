package ru.roma.vkchart.presenters;

import java.util.List;

/**
 * Created by Ilan on 07.03.2018.
 */

public interface CallbackPagination {

    void ready(List list );

    void reload();
}
