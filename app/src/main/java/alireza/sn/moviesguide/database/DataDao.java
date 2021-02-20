package alireza.sn.moviesguide.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.model.Details;

@Dao
public interface DataDao {

    @Query("SELECT * FROM data_tb")
    LiveData<List<Data>> getData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToWatchList(Data data);

    @Delete
    void removeFromWatchList(Data data);
}
