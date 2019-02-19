package com.nicolappli.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.NYTStreams;

import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoliticsFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new PoliticsFragment();
    }

    @Override
    protected void executeHttpRequestWithRetrofit() {
        this.mDisposable = NYTStreams.streamFetchSection("news_desk:(\"Politics\")").subscribeWith(new DisposableObserver<NewYorkTimesAPI>() {
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
