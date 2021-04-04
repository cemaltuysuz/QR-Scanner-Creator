package com.thic.qrreadercreator.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {GenerateModel.class,ScanModel.class},version = 1)
public abstract class QrDatabase extends RoomDatabase {

    private static String database_name = "qrDatabase";
    private static QrDatabase instance;
    public abstract QrDAO qrDAO();

    public static synchronized QrDatabase database(Context context){
        if (instance == null){
            instance = Room.databaseBuilder
                    (context.getApplicationContext(),QrDatabase.class,database_name)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private QrDAO qrDAO;
         private PopulateDbAsyncTask(QrDatabase qrDatabase){
             qrDAO = qrDatabase.qrDAO();
         }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
