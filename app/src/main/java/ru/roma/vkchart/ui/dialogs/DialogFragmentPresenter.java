package ru.roma.vkchart.ui.dialogs;



import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.roma.vkchart.app.MyApplication;
import ru.roma.vkchart.domain.DialogDomain;
import ru.roma.vkchart.models.entities.Dialog;
import ru.roma.vkchart.utils.MyLog;


/**
 * Created by Ilan on 24.02.2018.
 */

public class DialogFragmentPresenter {

    @Inject
    DialogDomain domain;
    private ViewDialog view;
    private  boolean loading = false;


    public DialogFragmentPresenter(ViewDialog view) {
        this.view = view;
        MyApplication.getInstance().getComponent().injectDialogPtresenter(this);

    }

    public void detach(){
        view = null;
    }

    public void getDialogs() {

        if (!loading) {
            loading = true;

            io.reactivex.Observable.fromCallable(new Callable<List<Dialog>>() {
                @Override
                public List<Dialog> call() throws Exception {
                    return domain.getListDialog();
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Dialog>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            MyLog.log("onSubscribe");
                        }

                        @Override
                        public void onNext(@NonNull List<Dialog> dialogsHolders) {

                            view.updateList(dialogsHolders);
                            loading = false;
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            if (e instanceof IOException){
                                view.showError("ошибка при загрузке данных");
                            }else {
                                view.showError("ошибка BD");
                            }
                            loading = false;

                        }

                        @Override
                        public void onComplete() {
                            loading = false;
                        }
                    });
        }
    }
}
