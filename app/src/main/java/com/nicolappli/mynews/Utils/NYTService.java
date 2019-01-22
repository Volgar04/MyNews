package com.nicolappli.mynews.Utils;

import com.nicolappli.mynews.Models.NYTTopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTService {
    //String apiKey = "AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6";

    @GET("{section}.json?api-key=AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6")
    Observable<NYTTopStories> getTopStories(@Path("section") String section);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
