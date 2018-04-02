package ru.roma.vkchart.domain.usecase;

/**
 * Created by Ilan on 31.03.2018.
 */

public interface UseCase<Result,Argument> {
    Result execute(Argument arg) throws Exception;
}
