package ru.roma.vkchart.data.repository;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.roma.vkchart.data.api.ApiVK;
import ru.roma.vkchart.data.api.ServerResponseToEntitiesMapper;
import ru.roma.vkchart.data.api.model_response.DialogModelResponse;
import ru.roma.vkchart.data.data_base.DialogDao;
import ru.roma.vkchart.data.data_base.DialogsDateComparator;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.utils.Keys;
import ru.roma.vkchart.utils.MyLog;

import static android.content.Context.MODE_PRIVATE;

public class DialogRepositoryImpl implements DialogRepository {

    private ApiVK vkServer;
    private DialogDao dialogDao;
    private ServerResponseToEntitiesMapper mapper;
    private final String TOKEN;

    public DialogRepositoryImpl(ApiVK vkServer, DialogDao dialogDao, ServerResponseToEntitiesMapper mapper) {
        this.vkServer = vkServer;
        this.dialogDao = dialogDao;
        this.mapper = mapper;
        TOKEN = MyApplication.getInstance().getSharedPreferences(Keys.MAINPREF, MODE_PRIVATE)
                .getString(Keys.TOKEN, null);
    }

    @Override
    public Observable<List<Dialog>> getDialog(int offset) {
        refreshListDialog(offset);
        return dialogDao.getAllDialogs().map(new Function<List<Dialog>, List<Dialog>>() {
            @Override
            public List<Dialog> apply(List<Dialog> dialogs) throws Exception {
                Collections.sort(dialogs,new DialogsDateComparator());
                return dialogs;

            }
        }).toObservable();
    }

    private void refreshListDialog(int offset) {
            vkServer.getDialog(offset,TOKEN).subscribeOn(Schedulers.io()).map(new Function<DialogModelResponse, List<Dialog>>() {
                @Override
                public List<Dialog> apply(DialogModelResponse dialogModelResponse) throws Exception {
                    return mapper.map(dialogModelResponse);
                }
            }).subscribe(new Observer<List<Dialog>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<Dialog> dialogs) {
                    MyLog.log("refreshListDialog");
                    dialogDao.insert(dialogs);
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {

                }
            });
    }

}
