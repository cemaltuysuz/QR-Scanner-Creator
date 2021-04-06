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

    private QrRepository repository;
    private LiveData<List<model>> allModel;

    public QrViewmodel(@NonNull Application application) {
        super(application);
        repository = new QrRepository(application);
        allModel = repository.getAllModel();
    }

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
    public LiveData<List<model>> getAllNote(){
        return allModel;
    }

    MutableLiveData<model> pushDataModel = new MutableLiveData<>();

    public model getPushDataModel() {
        return this.pushDataModel.getValue();
    }

    public void setPushDataModel(model model) {
        this.pushDataModel.setValue(model);
    }
}
