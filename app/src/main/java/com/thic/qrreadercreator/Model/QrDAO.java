package com.thic.qrreadercreator.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QrDAO {

    // Insert
    @Insert
    void modelInsert (model model);

    // Update
    @Update
    void modelUpdate (model model);

    //Delete
    @Delete
    void modelDel (model model);

    // QUERY
    @Query("DELETE FROM qr_table")
    void modelTableDel();

    @Query("SELECT * FROM qr_table")
    LiveData<List<model>> getmodelTable();

    @Query("SELECT * FROM qr_table WHERE isScan=1")
    LiveData<List<model>> getScanList();

    @Query("SELECT * FROM qr_table WHERE isScan=0")
    LiveData<List<model>> getGenerateList();
}
