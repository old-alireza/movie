package alireza.sn.moviesguide.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import alireza.sn.moviesguide.api.ApiClient;
import alireza.sn.moviesguide.database.DataDB;
import alireza.sn.moviesguide.database.DataDao;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.model.Details;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends AndroidViewModel {
    MutableLiveData<Details> mutableLiveData;
    DataDB dataDB;
    DataDao dataDao;
    LiveData<List<Data>> listLiveData;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        dataDB = DataDB.getInstance(application);
        dataDao = dataDB.getDataDao();
        listLiveData = dataDao.getData();
    }

    public MutableLiveData<Details> getMutableLiveData(int id) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();

            ApiClient.getInstance().getDetails(id).enqueue(new Callback<Details>() {
                @Override
                public void onResponse(Call<Details> call, Response<Details> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Details> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                }
            });
        }

        return mutableLiveData;
    }

    public LiveData<List<Data>> getListLiveData() {
        return listLiveData;
    }

    public void addToWatchList(Data data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dataDao.addToWatchList(data);
                return null;
            }
        }.execute();
    }

    public void removeFromWatchlist(Data data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dataDao.removeFromWatchList(data);
                return null;
            }
        }.execute();
    }
}
