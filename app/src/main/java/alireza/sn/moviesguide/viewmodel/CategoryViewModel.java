package alireza.sn.moviesguide.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import alireza.sn.moviesguide.api.ApiClient;
import alireza.sn.moviesguide.model.Genres;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {
    MutableLiveData<List<Genres>> mutableLiveData;

    public MutableLiveData<List<Genres>> getMutableLiveData() {
        if (mutableLiveData==null){
            mutableLiveData = new MutableLiveData<>();

            ApiClient.getInstance().getGenres().enqueue(new Callback<List<Genres>>() {
                @Override
                public void onResponse(Call<List<Genres>> call, Response<List<Genres>> response) {
                    if (response.body() != null)
                        mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Genres>> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                }

            });
        }


        return mutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
