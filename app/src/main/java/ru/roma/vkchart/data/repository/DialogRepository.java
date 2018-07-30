package ru.roma.vkchart.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.roma.vkchart.domain.entities.Dialog;

public interface DialogRepository {

    Observable<List<Dialog>> getDialog(int offset);

}
