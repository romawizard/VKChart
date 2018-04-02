package ru.roma.vkchart.domain.usecase;

import java.util.List;

import ru.roma.vkchart.domain.datacource.DataSourceLessArgument;
import ru.roma.vkchart.domain.entities.Dialog;

/**
 * Created by Ilan on 31.03.2018.
 */

public class LoadDialogsUseCase implements UseCaseLessArgumet<List<Dialog>> {

    DataSourceLessArgument<List<Dialog>> dataSource;


    public LoadDialogsUseCase(DataSourceLessArgument<List<Dialog>> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Dialog> execute() throws Exception {
        List<Dialog> dialogs =  dataSource.getData();
        dataSource.saveData(dialogs);
        return dialogs;
    }
}
