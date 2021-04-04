package com.thic.qrreadercreator.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "generate_table")
public class GenerateModel {

    @PrimaryKey(autoGenerate = true)
    private int generateID;

    private String description;

    private int resourceDirection;

    private String generateDate;

    public void setGenerateID(int generateID) {
        this.generateID = generateID;
    }

    public int getGenerateID() {
        return generateID;
    }

    public String getDescription() {
        return description;
    }

    public int getResourceDirection() {
        return resourceDirection;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public GenerateModel(String description, int resourceDirection, String generateDate) {
        this.description = description;
        this.resourceDirection = resourceDirection;
        this.generateDate = generateDate;
    }
}
