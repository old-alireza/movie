package alireza.sn.moviesguide.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import org.jetbrains.annotations.NotNull;

import alireza.sn.moviesguide.fragment.MoviesFragment;
import alireza.sn.moviesguide.model.ApiResponse;
import alireza.sn.moviesguide.model.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDataSource extends PageKeyedDataSource<Integer, Data> {
    public static final int FIRST_PAGE = 1;
    public static final int SIZE_PAGE = 25;

    //for show the genre and used by category section
    int id;

    public MyDataSource(int id) {
        this.id = id;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        MoviesFragment.showProgress();

        if (id != 0) {
            ApiClient.getInstance().getApiInterface().getApiResponseByGenre(id, FIRST_PAGE).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(@NotNull Call<ApiResponse> call, @NotNull Response<ApiResponse> response) {
                    if (response.body() != null)
                        callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);

                    MoviesFragment.hideProgress();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                    MoviesFragment.hideProgress();
                }
            });
        } else {
            ApiClient.getInstance().getApiInterface().getApiResponse(FIRST_PAGE).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body() != null)
                        callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);

                    MoviesFragment.hideProgress();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                    MoviesFragment.hideProgress();
                }
            });
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        MoviesFragment.showProgress2();
        if (id != 0) {
            ApiClient.getInstance().getApiInterface().getApiResponseByGenre(id, params.key).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body() != null) {
                        int key = params.key + 1;
                        callback.onResult(response.body().getData(), key);
                    }
                    MoviesFragment.hideProgress2();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                    MoviesFragment.hideProgress2();
                }
            });

        } else {
            ApiClient.getInstance().getApiInterface().getApiResponse(params.key).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body() != null) {
                        int key = params.key + 1;
                        callback.onResult(response.body().getData(), key);
                    }
                    MoviesFragment.hideProgress2();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                    MoviesFragment.hideProgress2();
                }
            });
        }
    }
}
