package com.thic.qrreadercreator.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QrRepository {

    private QrDAO qrDAO ;
    private LiveData<List<ScanModel>> AllScan;
    private LiveData<List<GenerateModel>> AllGenerate;

    public QrRepository(Application application){
        QrDatabase database = QrDatabase.database(application);
         qrDAO = database.qrDAO();
         AllScan = qrDAO.getScanTable();
         AllGenerate = qrDAO.getGenerateTable();
    }

    // Actions
    public void insertS (ScanModel scanModel){
        new InsertScanAsyncTask(qrDAO).execute(scanModel);
    }
    public void updateS (ScanModel scanModel){
        new UpdateScanAsyncTask(qrDAO).execute(scanModel);
    }
    public void deleteS (ScanModel scanModel){
        new DeleteScanAsyncTask(qrDAO).execute(scanModel);
    }

    public void deleteAllS (){
        new DeleteAllScanAsyncTask(qrDAO).execute();
    }

    public void insertG (GenerateModel generateModel){
        new InsertGenerateAsyncTask(qrDAO).execute(generateModel);
    }
    public void updateG (GenerateModel generateModel){
        new UpdateGenerateAsyncTask(qrDAO).execute(generateModel);
    }
    public void deleteG (GenerateModel generateModel){
        new DeleteGenerateAsyncTask(qrDAO).execute(generateModel);
    }

    public void deleteAllG (){
        new DeleteAllGenerateAsyncTask(qrDAO).execute();
    }

    // LiveData Lists
    public LiveData<List<ScanModel>> getAllScan(){
        return AllScan;
    }
    public LiveData<List<GenerateModel>> getAllGenerate(){
        return AllGenerate;
    }

    // AsyncTasks
    private static class InsertScanAsyncTask extends AsyncTask<ScanModel,Void,Void>{
        private QrDAO qrDAO;
        private InsertScanAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(ScanModel... scanModels) {
            qrDAO.scanInsert(scanModels[0]);
            return null;
        }
    }
    private static class UpdateScanAsyncTask extends AsyncTask<ScanModel,Void,Void>{
        private QrDAO qrDAO;
        private UpdateScanAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(ScanModel... scanModels) {
            qrDAO.scanUpdate(scanModels[0]);
            return null;
        }
    }
    private static class DeleteScanAsyncTask extends AsyncTask<ScanModel,Void,Void>{
        private QrDAO qrDAO;
        private DeleteScanAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(ScanModel... scanModels) {
            qrDAO.scanDel(scanModels[0]);
            return null;
        }
    }
    private static class DeleteAllScanAsyncTask extends AsyncTask<ScanModel,Void,Void>{
        private QrDAO qrDAO;
        private DeleteAllScanAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(ScanModel... scanModels) {
            qrDAO.scanTableDel();
            return null;
        }
    }

    private static class InsertGenerateAsyncTask extends AsyncTask<GenerateModel,Void,Void>{
        private QrDAO qrDAO;
        private InsertGenerateAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(GenerateModel... generateModels) {
            qrDAO.generateInsert(generateModels[0]);
            return null;
        }
    }
    private static class DeleteGenerateAsyncTask extends AsyncTask<GenerateModel,Void,Void>{
        private QrDAO qrDAO;
        private DeleteGenerateAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(GenerateModel... generateModels) {
            qrDAO.generateDel(generateModels[0]);
            return null;
        }
    }
    private static class UpdateGenerateAsyncTask extends AsyncTask<GenerateModel,Void,Void>{
        private QrDAO qrDAO;
        private UpdateGenerateAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(GenerateModel... generateModels) {
            qrDAO.generateUpdate(generateModels[0]);
            return null;
        }
    }
    private static class DeleteAllGenerateAsyncTask extends AsyncTask<GenerateModel,Void,Void>{
        private QrDAO qrDAO;
        private DeleteAllGenerateAsyncTask(QrDAO qrDAO){
            this.qrDAO =qrDAO;
        }
        @Override
        protected Void doInBackground(GenerateModel... generateModels) {
            qrDAO.generateTableDel();
            return null;
        }
    }
}
