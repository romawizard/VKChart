package ru.roma.vkchart.domain.usecase;

import java.util.List;

import ru.roma.vkchart.data.api.model_response.ItemMessage;
import ru.roma.vkchart.domain.datacource.DataSource;
import ru.roma.vkchart.domain.datacource.DataSourceLessArgument;
import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 01.04.2018.
 */

public class LoadMessageUseCase implements UseCase<List<Message>,Integer> {

    DataSource<List<Message>,Integer> dataSource;

    public LoadMessageUseCase(DataSource<List<Message>,Integer> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Message> execute(Integer userId) throws Exception {
        List<Message> messages = dataSource.getData(userId);
        dataSource.saveData(messages);
        return messages;
    }
}
