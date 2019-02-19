package com.nicolappli.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.Utils.NYTStreams;

import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScienceFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new ScienceFragment();
    }

    @Override
    protected void executeHttpRequestWithRetrofit() {
        this.mDisposable = NYTStreams.streamFetchSection("news_desk:(\"Science\")").subscribeWith(new DisposableObserver<NewYorkTimesAPI>() {
            @Override
            public void onNext(NewYorkTimesAPI result) {
                Log.i("TopStories Tag", "On Next");
                updateSearchResultUI(result);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TopStories Tag", "On Error" + Log.getStackTraceString(e).toUpperCase());
            }

            @Override
            public void onComplete() {
                Log.i("TopStories Tag", "On Complete !");
            }
        });
    }
}
