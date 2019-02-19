package com.nicolappli.mynews.Controllers.Fragments;

import android.support.v4.app.Fragment;
import android.util.Log;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.Utils.NYTStreams;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    protected void executeHttpRequestWithRetrofit() {
        this.mDisposable = NYTStreams.streamFetchMostPopular().subscribeWith(new DisposableObserver<NewYorkTimesAPI>() {
            @Override
            public void onNext(NewYorkTimesAPI result) {
                Log.i("MostPopular Tag", "On Next");
                updateUI(result);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MostPopular Tag", "On Error" + Log.getStackTraceString(e).toUpperCase());
            }

            @Override
            public void onComplete() {
                Log.i("MostPopular Tag", "On Complete !");
            }
        });
    }
}
