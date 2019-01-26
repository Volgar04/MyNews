package com.nicolappli.mynews.Utils;

import com.nicolappli.mynews.Models.NYTMostPopular;
import com.nicolappli.mynews.Models.NYTSearchArticle;
import com.nicolappli.mynews.Models.NYTTopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTService {
    //String apiKey = "AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6";

    @GET("topstories/v2/{section}.json?api-key=AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6")
    Observable<NYTTopStories> getTopStories(@Path("section") String section);

    @GET("mostpopular/v2/shared/1.json?api-key=AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6")
    Observable<NYTMostPopular> getMostPopular();

    @GET("search/v2/articlesearch.json?fl=web_url,snippet,pub_date,news_desk,multimedia,document_type,type_of_material&begin_date=20180101&end_date=20190101&fq=football&page=3&sort=newest&api-key=AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6")
    Observable<NYTSearchArticle> getSearchArticle(@Query("q") String query,
                                                  @Query("fq") String news_desk,
                                                  @Query("begin_date") String begin_date,
                                                  @Query("end_date") String end_date);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
