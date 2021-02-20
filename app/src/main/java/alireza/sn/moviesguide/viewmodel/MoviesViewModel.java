package alireza.sn.moviesguide.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

import alireza.sn.moviesguide.api.MyDataSource;
import alireza.sn.moviesguide.api.MyDataSourceFactory;
import alireza.sn.moviesguide.database.DataDB;
import alireza.sn.moviesguide.database.DataDao;
import alireza.sn.moviesguide.model.Data;

public class MoviesViewModel extends AndroidViewModel {
    LiveData<PagedList<Data>> pagedListLiveData;
    public static int id;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        MyDataSourceFactory factory = new MyDataSourceFactory(id);

        PagedList.Config config = new PagedList.Config.Builder().setPageSize(MyDataSource.SIZE_PAGE)
                .setEnablePlaceholders(true).build();
        pagedListLiveData = new LivePagedListBuilder(factory, config).build();
    }

    public LiveData<PagedList<Data>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
