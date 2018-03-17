package ru.roma.vkchart.presenters;

import java.util.List;

import ru.roma.vkchart.domain.DialogDomain;
import ru.roma.vkchart.holder.DataDialogs;
import ru.roma.vkchart.view.ViewDialog;
import ru.roma.vkchart.models.DialogModel;
import ru.roma.vkchart.models.DialogFromInternet;

/**
 * Created by Ilan on 24.02.2018.
 */

public class DialogFragmentPresenter {

    private ViewDialog view;
    private DialogDomain domain;


    public DialogFragmentPresenter(ViewDialog view) {
        this.view = view;
        domain = new DialogDomain();

    }

    public void detach(){
        view = null;
    }

    public void getListData(int lastPositionInLIst) {

        domain.getListDialog(lastPositionInLIst, new CallbackModel() {
            @Override
            public void successfull(List<DataDialogs> dataDialogses) {
                view.updateList(dataDialogses);
            }

            @Override
            public void failed(String message) {
                view.failed(message);
            }
        });

//
//        model.loadData(lastPositionInLIst,new CallbackModel() {
//
//            @Override
//            public void successfull(List<DataDialogs> dataDialogs) {
//               модель загрузила лист с offset от pagination
//
//                view.updateList(dataDialogs);
//            }
//
//            @Override
//            public void failed() {
//
//            }
//        });
    }
}
