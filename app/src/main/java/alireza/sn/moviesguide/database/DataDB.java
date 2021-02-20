package alireza.sn.moviesguide.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.model.Details;

@Database(entities = Data.class,version = 1,exportSchema = false)
public abstract class DataDB extends RoomDatabase {

    public static DataDB dataDB;
    public abstract DataDao getDataDao();

    public static DataDB getInstance(Context context){
        if (dataDB == null)
            dataDB = Room.databaseBuilder(context, DataDB.class,"data_db")
                    .build();

        return dataDB;
    }

}
