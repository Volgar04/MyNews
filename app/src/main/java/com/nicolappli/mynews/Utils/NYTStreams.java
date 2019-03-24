package com.nicolappli.mynews.Utils;

import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {
    public static Observable<NewYorkTimesAPI> streamFetchTopStories(String section){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NewYorkTimesAPI> streamFetchMostPopular(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NewYorkTimesAPI> streamFetchSearchArticles(String query, String news_desk, String begin_date, String end_date){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getSearchArticle(query, news_desk, begin_date, end_date)//, begin_date, end_date
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NewYorkTimesAPI> streamFetchSection(String news_desk, String page){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getSection(news_desk, page)//, begin_date, end_date
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NewYorkTimesAPI> streamFetchNotification(String query, String news_desk, String begin_date){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getNotification(query, news_desk, begin_date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
