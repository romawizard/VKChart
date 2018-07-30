package ru.roma.vkchart.data.data_base;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import ru.roma.vkchart.domain.entities.Dialog;
import ru.roma.vkchart.domain.entities.Message;

@Dao
public interface MessageDao {


    @Query("SELECT * FROM Message")
    Flowable<List<Message>> getAllDialogs();

    @Query("SELECT * FROM Dialog WHERE userId = :id")
    Flowable<Dialog> getDialogById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dialog employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Dialog> dialogs);

    @Update
    void update(Dialog employee);

    @Delete
    void delete(Dialog employee);
}
