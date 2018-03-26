package ru.roma.vkchart.domain.providers;

import java.io.IOException;
import java.util.List;

import ru.roma.vkchart.models.entities.Dialog;

/**
 * Created by Ilan on 24.03.2018.
 */

public interface DialogsProvider {

    List<Dialog> loadData() throws Exception;
}
