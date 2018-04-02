package ru.roma.vkchart.domain.usecase;

/**
 * Created by Ilan on 31.03.2018.
 */

public interface UseCaseLessArgumet<Result> {
    Result execute() throws Exception;
}


