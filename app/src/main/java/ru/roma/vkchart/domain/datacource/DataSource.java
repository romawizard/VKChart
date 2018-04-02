package ru.roma.vkchart.domain.datacource;

/**
 * Created by Ilan on 02.04.2018.
 */

public interface DataSource<Result,Argument> {

    Result getData(Argument argument) throws Exception;
    void  saveData(Result result);
}
