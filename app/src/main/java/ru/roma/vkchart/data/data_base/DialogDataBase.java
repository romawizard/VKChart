package ru.roma.vkchart.data.data_base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;

/**
 * Created by Ilan on 14.03.2018.
 */
@Database(entities = {Dialog.class, Message.class}, version = 1)
public abstract class DialogDataBase extends RoomDatabase {

    public abstract DialogDao dialogDao();

    public abstract MessageDao messageDao();
}
