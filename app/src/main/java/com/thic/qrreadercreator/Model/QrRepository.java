package com.thic.qrreadercreator.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QrRepository {
    //Defined
    private QrDAO qrDAO ;
    public static LiveData<List<model>> AllScanModel;
    public static LiveData<List<model>> AllGenerateModel;

    public QrRepository(Application application){
        QrDatabase database = QrDatabase.database(application);
         qrDAO = database.qrDAO();
         AllScanModel = qrDAO.getScanList();
         AllGenerateModel = qrDAO.getGenerateList();
    }

    // Actions
    public void insert(model insertModel){
        new InsertAsyncTask(qrDAO).execute(insertModel);
    }
    public void delete(model delModel){
        new DeleteAsyncTask(qrDAO).execute(delModel);
    }

    // LiveData Lists
    public LiveData<List<model>> getAllScanModel(){
        return AllScanModel;
    }

    public LiveData<List<model>> getAllGenerateModel(){
        return AllGenerateModel;
    }

    // AsyncTasks
    private static class InsertAsyncTask extends AsyncTask<model,Void,Void>{
        private QrDAO qrDAO;
        private InsertAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(model... Models) {
            qrDAO.modelInsert(Models[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<model,Void,Void>{
        private QrDAO qrDAO;
        private DeleteAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(model... scanModels) {
            qrDAO.modelDel(scanModels[0]);
            return null;
        }
    }
}
