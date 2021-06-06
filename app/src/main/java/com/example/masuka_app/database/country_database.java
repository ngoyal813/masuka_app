package com.example.masuka_app.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.masuka_app.dao.country_dao;
import com.example.masuka_app.models.country_model;

import org.jetbrains.annotations.NotNull;

@Database(entities = {country_model.class}, version = 4)
public abstract class country_database extends RoomDatabase {

  private static String DATABASE_NAME = "COUNTRY_DATABASE";
  private static volatile country_database INSTANCE;

  public abstract country_dao country_dao();

    public static country_database getInstance(Context context){
      if (INSTANCE == null){
            synchronized (country_database.class){
                if (INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(context,country_database.class,DATABASE_NAME)
                            .addCallback(callback).fallbackToDestructiveMigration()
                            .build();
                }
            }
      }
      return INSTANCE;
  }

  static Callback callback = new Callback() {
      @Override
      public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          new Populateasynktask(INSTANCE);
      }
  };

  public static class Populateasynktask extends AsyncTask<Void,Void,Void>{
      private country_dao country_dao;
      Populateasynktask(country_database country_database){
          country_dao = country_database.country_dao();
      }
      @Override
      protected Void doInBackground(Void... voids) {
          country_dao.deleteallcountries();
          return null;
      }
  }


}
