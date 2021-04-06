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
    public int getScanID() {
        return scanID;
    }
    public String getDescription() {
        return description;
    }
    public String getResult() {
        return result;
    }
    public String getScanDate() {
        return scanDate;
    }

    public ScanModel(int scanID, String description, String result, String scanDate) {
        this.scanID = scanID;
        this.description = description;
        this.result = result;
        this.scanDate = scanDate;
    }

    public void setScanID(int scanID) {
        this.scanID = scanID;
    }
}
