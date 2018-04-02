package ru.roma.vkchart.domain.usecase;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import ru.roma.vkchart.utils.MyLog;

/**
 * Created by Ilan on 31.03.2018.
 */

public class RxAsynUseCase implements AsynUseCase {
    @Override
    public <T, A> Observable<T> wrap(final UseCase<T, A> useCase, final A arg) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> eb) throws Exception {
                try {
                    eb.onNext(useCase.execute(arg));
                    eb.onComplete();
                } catch (Exception e) {
                    eb.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<T> wrap(final UseCaseLessArgumet<T> useCase) {
        return Observable.fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return useCase.execute();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
