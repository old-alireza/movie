package alireza.sn.moviesguide.api;

import java.util.List;

import alireza.sn.moviesguide.model.ApiResponse;
import alireza.sn.moviesguide.model.Data;
import alireza.sn.moviesguide.model.Details;
import alireza.sn.moviesguide.model.Genres;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(value = "movies")
    Call<ApiResponse> getApiResponse(@Query("page") int page);

    @GET(value = "movies/{movie_id}")
    Call<Details>getDetailMovie(@Path("movie_id")int id);

    @GET(value = "genres")
    Call<List<Genres>> getGenres();

    @GET(value = "genres/{genre_id}/movies")
    Call<ApiResponse> getApiResponseByGenre(@Path("genre_id") int id, @Query("page") int page);
}
