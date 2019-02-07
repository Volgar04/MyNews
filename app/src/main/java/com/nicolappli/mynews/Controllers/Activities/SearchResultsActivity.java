package com.nicolappli.mynews.Controllers.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.nicolappli.mynews.Adapters.RecyclerViewAdapterSearchArticles;
import com.nicolappli.mynews.Models.NYTSearchArticles;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.NYTStreams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class SearchResultsActivity extends AppCompatActivity {
    // FOR DESIGN
    @BindView(R.id.recycler_view_search_result)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout_search_result)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // FOR DATA
    private Disposable mDisposable;
    private RecyclerViewAdapterSearchArticles mAdapter;
    private List<NYTSearchArticles.Doc> mSearchArticlesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        this.buildRecyclerView();
        this.configureSwipeRefreshLayout();
        this.executeHttpRequestWithRetrofit();

        //String[] mValues=getIntent().getStringArrayExtra("VALUES_SEARCH_ARTICLES");
        //Log.i("SearchResult Tag", "query : "+mValues[0]);
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
        this.mAdapter = new RecyclerViewAdapterSearchArticles(mSearchArticlesArray, Glide.with(this));
        this.mRecyclerView.setHasFixedSize(false);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        String[] mValues=getIntent().getStringArrayExtra("VALUES_SEARCH_ARTICLES");
        Log.i("SearchResult Tag", "begin date length : "+mValues[2].length());
        Log.i("SearchResult Tag", "end date length : "+mValues[3].length());


        //this.mDisposable = NYTStreams.streamFetchSearchArticles(mValues[0],"news_desk:("+ mValues[1] +")", mValues[2], mValues[3]).subscribeWith(new DisposableObserver<NYTSearchArticles>() {
        this.mDisposable = NYTStreams.streamFetchSearchArticles(mValues[0],"news_desk:("+ mValues[1] +")").subscribeWith(new DisposableObserver<NYTSearchArticles>() {
            @Override
            public void onNext(NYTSearchArticles searchArticles) {
                Log.i("SearchResult Tag", "On Next");
                updateSearchResultUI(searchArticles);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("SearchResult Tag", "On Error" + Log.getStackTraceString(e).toUpperCase());
            }

            @Override
            public void onComplete() {
                Log.i("SearchResult Tag", "On Complete !");
            }
        });
    }

    private void disposeWhenDestroy() {
        if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // --------------------
    // UPDATE UI
    // --------------------

    private void updateSearchResultUI(NYTSearchArticles searchArticles){
        mSwipeRefreshLayout.setRefreshing(false);
        this.mSearchArticlesArray.clear();
        this.mSearchArticlesArray.addAll(searchArticles.getResponse().getDocs());
        Log.i("SearchResult Tag", "mSearchArticlesArray.length : "+mSearchArticlesArray.isEmpty());
        this.mAdapter.notifyDataSetChanged();
    }
}
