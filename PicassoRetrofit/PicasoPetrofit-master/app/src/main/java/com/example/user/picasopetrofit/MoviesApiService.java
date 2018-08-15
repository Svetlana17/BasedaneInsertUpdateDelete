package com.example.user.picasopetrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiService {

    /*@GET("movie/popular")
    Call<List<Movie>> getPopularMovies(@Query("api_key") String api_key);
*/
    @GET("movie/popular")
    Call<ResponseModel> getPopularMovies(@Query("api_key") String api_key);

}