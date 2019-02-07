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
import com.nicolappli.mynews.Adapters.RecyclerViewAdapterTopStories;
import com.nicolappli.mynews.Models.NYTTopStories;
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
public class TopStoriesPageFragment extends Fragment {
    // FOR DESIGN
    @BindView(R.id.recycler_view_top_stories)
            RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout_top_stories)
            SwipeRefreshLayout mSwipeRefreshLayout;

    // FOR DATA
    private Disposable mDisposable;
    private RecyclerViewAdapterTopStories mAdapter;
    private List<NYTTopStories.Result> mTopStoriesArray = new ArrayList<>();

    public TopStoriesPageFragment() { }

    public static TopStoriesPageFragment newInstance(){
        return new TopStoriesPageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_stories_page, container, false);
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
                        Log.e("TopStories Tag", "Position : "+position);
                    }
                });
    }

    // --------------------
    // CONFIGURATION
    // --------------------

    private void buildRecyclerView(){
        this.mAdapter = new RecyclerViewAdapterTopStories(mTopStoriesArray, Glide.with(this));
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
        this.mDisposable = NYTStreams.streamFetchTopStories("home").subscribeWith(new DisposableObserver<NYTTopStories>() {
            @Override
            public void onNext(NYTTopStories topStories) {
                Log.i("TopStories Tag", "On Next");
                updateTopStoriesUI(topStories);
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

    private void disposeWhenDestroy() {
        if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // --------------------
    // UPDATE UI
    // --------------------

    private void updateTopStoriesUI(NYTTopStories topStories){
        mSwipeRefreshLayout.setRefreshing(false);
        this.mTopStoriesArray.clear();
        this.mTopStoriesArray.addAll(topStories.getResults());
        this.mAdapter.notifyDataSetChanged();
    }
}