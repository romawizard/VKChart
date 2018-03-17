package ru.roma.vkchart.data_base;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.roma.vkchart.holder.DialogsHolder;

/**
 * Created by Ilan on 14.03.2018.
 */
@Dao
public interface DialogDao {

    @Query("SELECT * FROM DialogsHolder")
    List<DialogsHolder> getAll();

    @Query("SELECT * FROM DialogsHolder WHERE userId = :id")
    DialogsHolder getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DialogsHolder employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DialogsHolder> dialogsHolders);

    @Update
    void update(DialogsHolder employee);

    @Delete
    void delete(DialogsHolder employee);
}
