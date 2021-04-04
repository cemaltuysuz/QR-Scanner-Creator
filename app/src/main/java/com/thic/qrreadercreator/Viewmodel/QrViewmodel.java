package com.thic.qrreadercreator.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thic.qrreadercreator.Model.GenerateModel;
import com.thic.qrreadercreator.Model.QrRepository;
import com.thic.qrreadercreator.Model.ScanModel;

import java.util.List;
import java.util.Scanner;

public class QrViewmodel extends AndroidViewModel {

    private QrRepository repository;
    private LiveData<List<ScanModel>> allScanNote;
    private LiveData<List<GenerateModel>> allGenerateModel;

    public QrViewmodel(@NonNull Application application) {
        super(application);

        repository = new QrRepository(application);
        allScanNote = repository.getAllScan();
        allGenerateModel = repository.getAllGenerate();

    }

    public void insertS (ScanModel scanModel){
        repository.insertS(scanModel);
    }
    public void updateS(ScanModel scanModel){
        repository.updateS(scanModel);
    }
    public void delS(ScanModel scanModel){
        repository.deleteS(scanModel);
    }
    public void delAllS(){
        repository.deleteAllS();
    }
    public LiveData<List<ScanModel>> getAllScanNote(){
        return allScanNote;
    }

    public void insertG (GenerateModel generateModel){
        repository.insertG(generateModel);
    }
    public void updateG(GenerateModel generateModel){
        repository.updateG(generateModel);
    }
    public void delG(GenerateModel generateModel){
        repository.deleteG(generateModel);
    }
    public void delAllG(){
        repository.deleteAllG();
    }
    public LiveData<List<GenerateModel>> getAllGenerateModel(){
        return allGenerateModel;
    }
}
