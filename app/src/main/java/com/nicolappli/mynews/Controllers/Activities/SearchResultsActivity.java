package com.nicolappli.mynews.Controllers.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.nicolappli.mynews.Adapters.RecyclerViewAdapter;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.ItemClickSupport;
import com.nicolappli.mynews.Utils.NYTStreams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    // FOR DATA
    private Disposable mDisposable;
    private RecyclerViewAdapter mAdapter;
    private List<NewYorkTimesAPI.Result> mSearchArticlesArray = new ArrayList<>();
    private int nbrArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.buildRecyclerView();
        this.configureSwipeRefreshLayout();
        this.executeHttpRequestWithRetrofit();
        this.configureOnClickRecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }



    // --------------------
    // CONFIGURATION
    // --------------------

    private void buildRecyclerView() {
        this.mAdapter = new RecyclerViewAdapter(mSearchArticlesArray, Glide.with(this));
        this.mRecyclerView.setHasFixedSize(false);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    //configure click on recycler's view item
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.recycler_view_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        NewYorkTimesAPI.Result url = mAdapter.getUrl(position);
                        String value = url.getWebUrl();
                        Intent showArticleActivity = new Intent(SearchResultsActivity.this, ShowArticleActivity.class);
                        showArticleActivity.putExtra("VALUE_URL_ARTICLE", value);
                        startActivity(showArticleActivity);
                    }
                });
    }

    // --------------------
    // HTTP (RxJAVA)
    // --------------------

    public void executeHttpRequestWithRetrofit() {
        String[] mValues = getIntent().getStringArrayExtra("VALUES_SEARCH_ARTICLES");

        this.mDisposable = NYTStreams.streamFetchSearchArticles(mValues[0], "news_desk:(" + mValues[1] + ")", mValues[2], mValues[3]).subscribeWith(new DisposableObserver<NewYorkTimesAPI>() {
            @Override
            public void onNext(NewYorkTimesAPI searchArticles) {
                Log.i("SearchResult Tag", "On Next");
                updateSearchResultUI(searchArticles);
                nbrArticle=searchArticles.getResponse().getDocs().size();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("SearchResult Tag", "On Error" + Log.getStackTraceString(e).toUpperCase());
            }

            @Override
            public void onComplete() {
                Log.i("SearchResult Tag", "On Complete !");
                if(nbrArticle==0){
                    new AlertDialog.Builder(SearchResultsActivity.this)
                            .setTitle("Error")
                            .setMessage("No found article !")
                            .setCancelable(false)
                            .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .show();
                }
            }
        });
    }

    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // --------------------
    // UPDATE UI
    // --------------------

    private void updateSearchResultUI(NewYorkTimesAPI searchArticles) {
        mSwipeRefreshLayout.setRefreshing(false);
        this.mSearchArticlesArray.clear();
        this.mSearchArticlesArray.addAll(searchArticles.getResponse().getDocs());
        Log.i("SearchResult Tag", "mSearchArticlesArray.length : " + mSearchArticlesArray.isEmpty());
        this.mAdapter.notifyDataSetChanged();
    }

    public void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
