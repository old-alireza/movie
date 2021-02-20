package alireza.sn.moviesguide.api;

import java.util.List;

import alireza.sn.moviesguide.model.Details;
import alireza.sn.moviesguide.model.Genres;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://moviesapi.ir/api/v1/";

    public static ApiClient apiClient;
    private final ApiInterface apiInterface;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiClient getInstance() {
        if (apiClient == null)
            apiClient = new ApiClient();

        return apiClient;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public Call<List<Genres>> getGenres() {
        return apiInterface.getGenres();
    }

    public Call<Details> getDetails(int id) {
        return apiInterface.getDetailMovie(id);
    }
}
