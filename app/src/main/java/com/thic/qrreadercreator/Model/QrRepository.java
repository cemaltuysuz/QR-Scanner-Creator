package com.thic.qrreadercreator.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QrRepository {

    private QrDAO qrDAO ;
    public static LiveData<List<model>> Allmodel;

    public QrRepository(Application application){
        QrDatabase database = QrDatabase.database(application);
         qrDAO = database.qrDAO();
         Allmodel = qrDAO.getmodelTable();
    }

    // Actions
    public void insert(model insertModel){
        new InsertAsyncTask(qrDAO).execute(insertModel);
    }
    public void update(model updateModel){
        new UpdateAsyncTask(qrDAO).execute(updateModel);
    }
    public void delete(model delModel){
        new DeleteAsyncTask(qrDAO).execute(delModel);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(qrDAO).execute();
    }

    // LiveData Lists
    public LiveData<List<model>> getAllModel(){
        return Allmodel;
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
    private static class UpdateAsyncTask extends AsyncTask<model,Void,Void>{
        private QrDAO qrDAO;
        private UpdateAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(model... scanModels) {
            qrDAO.modelUpdate(scanModels[0]);
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
    private static class DeleteAllAsyncTask extends AsyncTask<model,Void,Void>{
        private QrDAO qrDAO;
        private DeleteAllAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(model... scanModels) {
            qrDAO.modelTableDel();
            return null;
        }
    }

}
