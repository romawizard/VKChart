package ru.roma.vkchart.domain.usecase;

import android.os.Bundle;

import ru.roma.vkchart.domain.datacource.DataSource;
import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 08.04.2018.
 */

public class SendMessageUseCase implements UseCase<Integer, Message> {

    DataSource<Integer,Message> dataSource;

    public SendMessageUseCase(DataSource<Integer, Message> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Integer execute(Message message) throws Exception {
        return dataSource.getData(message);
    }
}
