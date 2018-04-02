package ru.roma.vkchart.domain.usecase;

import io.reactivex.Observable;

/**
 * Created by Ilan on 31.03.2018.
 */

public interface AsynUseCase {

    <T, A> Observable<T> wrap(final UseCase<T, A> useCase, final A arg);

    <T> Observable<T> wrap(final UseCaseLessArgumet<T> useCase);
}
