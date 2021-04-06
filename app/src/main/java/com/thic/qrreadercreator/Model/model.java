package com.thic.qrreadercreator.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "qr_table")
public class model {

    @PrimaryKey(autoGenerate = true)
    private int qrID;

    private String value;

    private String description;

    private String dateTime;

    private boolean isScan;

    public model(String value, String description, String dateTime, boolean isScan) {
        this.value = value;
        this.description = description;
        this.dateTime = dateTime;
        this.isScan = isScan;
    }

    public void setQrID(int qrID) {
        this.qrID = qrID;
    }

    public int getQrID() {
        return qrID;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public boolean isScan() {
        return isScan;
    }
}
