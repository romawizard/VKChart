package ru.roma.vkchart.utils;

import java.util.List;

/**
 * Created by Ilan on 06.03.2018.
 */

public interface Paginable {

    List getData(int offset);
    int getCount();
}
