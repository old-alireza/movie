package alireza.sn.moviesguide.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import alireza.sn.moviesguide.model.Data;

public class MyDataSourceFactory extends DataSource.Factory {
    private final MutableLiveData<PageKeyedDataSource<Integer,Data>> dataLiveData = new MutableLiveData<>();
    Integer id =0;
    public MyDataSourceFactory(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public DataSource create() {
        MyDataSource myDataSource = new MyDataSource(id);
        dataLiveData.postValue(myDataSource);
        return myDataSource;
    }
}
