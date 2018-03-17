package ru.roma.vkchart.data_base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.roma.vkchart.adapters.DialogsAdapter;
import ru.roma.vkchart.holder.DialogsHolder;

/**
 * Created by Ilan on 14.03.2018.
 */
@Database(entities = DialogsHolder.class, version = 1)
public abstract class DialogDataBase extends RoomDatabase {

    public abstract DialogDao dialogDao();
}
