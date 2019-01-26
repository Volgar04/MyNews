package com.nicolappli.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.nicolappli.mynews.Adapters.RecyclerViewAdapterMostPopular;
import com.nicolappli.mynews.Models.NYTMostPopular;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.NYTStreams;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends Fragment {
    // FOR DESIGN
    @BindView(R.id.recycler_view_most_popular)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout_most_popular)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // FOR DATA
    private Disposable mDisposable;
    private RecyclerViewAdapterMostPopular mAdapter;
    private List<NYTMostPopular.Result> mMostPopularArray = new ArrayList<>();

    public MostPopularPageFragment() { }

    public static MostPopularPageFragment newInstance() {
        return (new MostPopularPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_most_popular_page, container, false);
        ButterKnife.bind(this, rootView);
        this.buildRecyclerView();
        this.configureSwipeRefreshLayout();
        this.executeHttpRequestWithRetrofit();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // --------------------
    // CONFIGURATION
    // --------------------

    private void buildRecyclerView(){
        this.mAdapter = new RecyclerViewAdapterMostPopular(mMostPopularArray, Glide.with(this));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureSwipeRefreshLayout(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    // --------------------
    // HTTP (RxJAVA)
    // --------------------

    public void executeHttpRequestWithRetrofit(){
        this.mDisposable = NYTStreams.streamFetchMostPopular().subscribeWith(new DisposableObserver<NYTMostPopular>() {
            @Override
            public void onNext(NYTMostPopular mostPopular) {
                Log.d("MostPopular Tag", "On Next");
                updateMostPopularUI(mostPopular);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MostPopular Tag", "On Error" + Log.getStackTraceString(e).toUpperCase());
            }

            @Override
            public void onComplete() {
                Log.d("MostPopular Tag", "On Complete !");
            }
        });
    }

    private void disposeWhenDestroy() {
        if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // --------------------
    // UPDATE UI
    // --------------------

    private void updateMostPopularUI(NYTMostPopular mostPopular){
        this.mSwipeRefreshLayout.setRefreshing(false);
        this.mMostPopularArray.clear();
        this.mMostPopularArray.addAll(mostPopular.getResults());
        this.mAdapter.notifyDataSetChanged();
    }
}
