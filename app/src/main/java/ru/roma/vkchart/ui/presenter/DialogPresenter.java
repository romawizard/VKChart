package ru.roma.vkchart.ui.presenter;



import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import ru.roma.vkchart.ui.MyApplication;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.usecase.AsynUseCase;
import ru.roma.vkchart.domain.usecase.LoadDialogsUseCase;
import ru.roma.vkchart.ui.fragment.View;


/**
 * Created by Ilan on 24.02.2018.
 */

public class DialogPresenter {

//    @Inject
//    DialogDomain domain;
    @Inject
    LoadDialogsUseCase loadDialogsUseCase;
    @Inject
    AsynUseCase asynUseCase;
    private View view;
    private boolean loading = false;


    public DialogPresenter(View view) {
        this.view = view;
        MyApplication.getInstance().getAppComponent().injectDialogPresenter(this);
    }

    public void detach(){
        view = null;
    }

//    public void getDialogs() {
//
//        if (!loading) {
//            loading = true;
//
//            io.reactivex.Observable.fromCallable(new Callable<List<Dialog>>() {
//                @Override
//                public List<Dialog> call() throws Exception {
//                    return domain.getListDialog();
//                }
//            })
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<List<Dialog>>() {
//                        @Override
//                        public void onSubscribe(@NonNull Disposable d) {
//                            MyLog.log("onSubscribe");
//                        }
//
//                        @Override
//                        public void onNext(@NonNull List<Dialog> dialogsHolders) {
//
//                            view.updateList(dialogsHolders);
//                            loading = false;
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//                            e.printStackTrace();
//                            if (e instanceof IOException){
//                                view.showError("ошибка при загрузке данных");
//                            }else {
//                                view.showError("ошибка BD");
//                            }
//                            loading = false;
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            loading = false;
//                        }
//                    });
//        }
//    }

    public void getDialogs(){
        if (!loading) {

            loading = true;

            asynUseCase.wrap(loadDialogsUseCase).subscribe(new Observer<List<Dialog>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull List<Dialog> dialogs) {
                    view.updateList(dialogs);
                    loading = false;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                    view.showError("error");
                    loading = false;
                }

                @Override
                public void onComplete() {
                    loading = false;
                }
            });
        }
    }

    public void ready() {

    }
}
