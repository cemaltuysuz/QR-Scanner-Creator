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

    private static MutableLiveData<List<model>>selectedList = new MutableLiveData<>();
    private QrRepository repository;
    private LiveData<List<model>> allModel;
    private static MutableLiveData<Boolean>singleSelect = new MutableLiveData<>();


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

    public static MutableLiveData<List<model>> getSelectedList() {
        return selectedList;
    }

    public static void setSelectedList(List<model> selectList) {
        selectedList.setValue(selectList);
    }

    public MutableLiveData<Boolean> getSingleSelect() {
        return singleSelect;
    }

    public static void setSingleSelect(boolean bool) {
        singleSelect.setValue(bool);
    }

    static MutableLiveData<model> pushDataModel = new MutableLiveData<>();
    public static MutableLiveData<Boolean>actionModeİsActive = new MutableLiveData<>();

    public model getPushDataModel() {
        return this.pushDataModel.getValue();
    }

    public static void setPushDataModel(model model) {
        pushDataModel.setValue(model);
    }

    public String toolbarTitle (){
        String title = selectedList.getValue().size()+" Selected";
        return title;
    }
}
