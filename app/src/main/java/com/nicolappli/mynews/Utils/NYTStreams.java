package com.nicolappli.mynews.Utils;

import com.nicolappli.mynews.Models.NYTMostPopular;
import com.nicolappli.mynews.Models.NYTSearchArticles;
import com.nicolappli.mynews.Models.NYTTopStories;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {
    public static Observable<NYTTopStories> streamFetchTopStories(String section){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTMostPopular> streamFetchMostPopular(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTSearchArticles> streamFetchSearchArticles(String query, String news_desk){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getSearchArticle(query, news_desk)//, begin_date, end_date
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
