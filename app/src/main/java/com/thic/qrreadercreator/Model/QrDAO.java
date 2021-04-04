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
    void scanInsert (ScanModel scanModel);

    @Insert
    void generateInsert (GenerateModel generateModel);

    // Update
    @Update
    void scanUpdate (ScanModel scanModel);

    @Update
    void generateUpdate (GenerateModel generateModel);

    //Delete
    @Delete
    void scanDel (ScanModel scanModel);

    @Delete
    void generateDel (GenerateModel generateModel);

    // QUERY

    @Query("DELETE FROM scan_table")
    void scanTableDel();

    @Query("DELETE FROM generate_table")
    void generateTableDel();

    @Query("SELECT * FROM scan_table")
    LiveData<List<ScanModel>> getScanTable();

    @Query("SELECT * FROM generate_table")
    LiveData<List<GenerateModel>> getGenerateTable();
}
