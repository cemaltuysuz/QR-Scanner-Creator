package com.thic.qrreadercreator.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scan_table")
public class ScanModel {

    @PrimaryKey(autoGenerate = true)
    private int scanID;

    private String description;

    private String result;

    private String scanDate;
}
