package com.thic.qrreadercreator.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thic.qrreadercreator.Model.QrRepository;
import com.thic.qrreadercreator.Model.model;
import java.util.List;


public class QrViewmodel extends AndroidViewModel {

    //Defined variable
    private static MutableLiveData<List<model>> ScanSelectedList = new MutableLiveData<>();
    private static MutableLiveData<List<model>> GenerateSelectedList = new MutableLiveData<>();
    private static MutableLiveData<Boolean>singleSelect = new MutableLiveData<>();

    private QrRepository repository;
    private LiveData<List<model>> allScanModel;
    private LiveData<List<model>> allGenerateModel;

    public QrViewmodel(@NonNull Application application) {
        super(application);
        repository = new QrRepository(application);
        allScanModel = repository.getAllScanModel();
        allGenerateModel = repository.getAllGenerateModel();
    }

    //Action methods
    public void insert(model model){
        repository.insert(model);
    }
    public void update(model model){
        repository.update(model);
    }
    public void del(model model){
        repository.delete(model);
    }
    public void delAll(){
        repository.deleteAll();
    }

    //Get Lists
    /*public LiveData<List<model>> getAllModel(){
        return allModel;
    }*/
    public LiveData<List<model>> getAllScanModel(){
        return allScanModel;
    }
    public LiveData<List<model>> getAllGenerateModel(){
        return allGenerateModel;
    }

    //Single select lists Get and set
    public static MutableLiveData<List<model>> getScanSelectedList() {
        return ScanSelectedList;
    }
    public static void setScanSelectedList(List<model> selectList) {
        ScanSelectedList.setValue(selectList);
    }

    public static MutableLiveData<List<model>> getGenerateSelectedList() {
        return GenerateSelectedList;
    }
    public static void setGenerateSelectedList(List<model> selectList) {
        GenerateSelectedList.setValue(selectList);
    }

    public MutableLiveData<Boolean> getSingleSelect() {
        return singleSelect;
    }
    public static void setSingleSelect(boolean bool) {
        singleSelect.setValue(bool);
    }

    //Push data list
    static MutableLiveData<model> pushDataModel = new MutableLiveData<>();
    public model getPushDataModel() {
        return this.pushDataModel.getValue();
    }
    public static void setPushDataModel(model model) {
        pushDataModel.setValue(model);
    }

    //ActionMode Mutable Live data
    public static MutableLiveData<Boolean>actionModeİsActive = new MutableLiveData<>();

}
