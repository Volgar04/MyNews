package com.nicolappli.mynews.Controllers.Fragments;

import android.content.Intent;
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
import com.nicolappli.mynews.Adapters.RecyclerViewAdapter;
import com.nicolappli.mynews.Controllers.Activities.ShowArticleActivity;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.ItemClickSupport;
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
    private RecyclerViewAdapter mAdapter;
    private List<NewYorkTimesAPI.Result> mMostPopularArray = new ArrayList<>();

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
        this.configureOnClickRecyclerView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // --------------------
    // ACTION
    // --------------------

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView,R.layout.recycler_view_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        NewYorkTimesAPI.Result url = mAdapter.getUrl(position);
                        String value = url.getUrl();
                        Intent showArticleActivity = new Intent(getActivity(), ShowArticleActivity.class);
                        showArticleActivity.putExtra("VALUE_URL_ARTICLE",value);
                        startActivity(showArticleActivity);
                    }
                });
    }

    // --------------------
    // CONFIGURATION
    // --------------------

    private void buildRecyclerView(){
        this.mAdapter = new RecyclerViewAdapter(mMostPopularArray, Glide.with(this));
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
        this.mDisposable = NYTStreams.streamFetchMostPopular().subscribeWith(new DisposableObserver<NewYorkTimesAPI>() {
            @Override
            public void onNext(NewYorkTimesAPI mostPopular) {
                Log.i("MostPopular Tag", "On Next");
                updateMostPopularUI(mostPopular);
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

    private void disposeWhenDestroy() {
        if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // --------------------
    // UPDATE UI
    // --------------------

    private void updateMostPopularUI(NewYorkTimesAPI mostPopular){
        this.mSwipeRefreshLayout.setRefreshing(false);
        this.mMostPopularArray.clear();
        this.mMostPopularArray.addAll(mostPopular.getResults());
        this.mAdapter.notifyDataSetChanged();
    }
}
