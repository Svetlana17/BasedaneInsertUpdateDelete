package com.example.user.picasopetrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    RetrofitApi retrofitApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);


        getMovie();

    }

  private    void  getMovie(){


    MoviesApiService moviesApiService = retrofitApi.getClient().create(MoviesApiService.class);

    //интерфейс реализован с помощию класса Refrofit  с базовым URL b конвертором
//      MoviesApiService service = restAdapter.create(MoviesApiService.class);

      /*Call<List<Movie>> call = moviesApiService.getPopularMovies("eb37bafc5fe27ad9ab86a74e72812c06");

      call.enqueue(new Callback<List<Movie>>() {
          @Override
          public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
              System.out.println("HELLO");
              movieAdapter.setMovieList(response.body());
          }

          @Override
          public void onFailure(Call<List<Movie>> call, Throwable t) {

          }
      });*/
      Call<ResponseModel> call = moviesApiService.getPopularMovies("eb37bafc5fe27ad9ab86a74e72812c06");

      call.enqueue(new Callback<ResponseModel>() {
          @Override
          public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
              movieAdapter.setMovieList(response.body().getResults());
          }

          @Override
          public void onFailure(Call<ResponseModel> call, Throwable t) {

          }
      });

    }}
