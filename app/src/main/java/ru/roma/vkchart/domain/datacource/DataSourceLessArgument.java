package ru.roma.vkchart.domain.datacource;

/**
 * Created by Ilan on 31.03.2018.
 */

public interface DataSourceLessArgument<Result> {

    Result getData() throws Exception;
    void  saveData(Result result);
}
