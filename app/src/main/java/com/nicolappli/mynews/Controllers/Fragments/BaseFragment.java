package com.nicolappli.mynews.Controllers.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.nicolappli.mynews.Adapters.RecyclerViewAdapter;
import com.nicolappli.mynews.Controllers.Activities.ShowArticleActivity;
import com.nicolappli.mynews.Models.NewYorkTimesAPI;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.ItemClickSupport;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    // FOR DESIGN
    @BindView(R.id.recycler_view_base)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout_base)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // FOR DATA
    public Disposable mDisposable;
    public RecyclerViewAdapter mAdapter;
    public List<NewYorkTimesAPI.Result> mArray = new ArrayList<>();

    protected abstract void executeHttpRequestWithRetrofit();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        this.buildRecyclerView();
        this.configureSwipeRefreshLayout();
        this.executeHttpRequestWithRetrofit();
        this.configureOnClickRecyclerView();
        return(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // --------------------
    // ACTION
    // --------------------

    //When the user click on an item, a new activity is launched with the content of the article corresponding to the item
    public void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView,R.layout.recycler_view_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        NewYorkTimesAPI.Result url = mAdapter.getUrl(position);
                        String value = null;
                        if(url.getUrl()!=null && !url.getUrl().isEmpty()) //get the good url for Top Stories and Most Popular
                            value = url.getUrl();
                        else if(url.getWebUrl()!=null && !url.getWebUrl().isEmpty())//get the good url for Search Article
                            value = url.getWebUrl();
                        Intent showArticleActivity = new Intent(getActivity(), ShowArticleActivity.class);
                        showArticleActivity.putExtra("VALUE_URL_ARTICLE",value);
                        startActivity(showArticleActivity);
                    }
                });
    }

    // --------------------
    // CONFIGURATION
    // --------------------


    //Build the recycler view
    public void buildRecyclerView(){
        this.mAdapter = new RecyclerViewAdapter(mArray, Glide.with(this));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    //Configure the swipe when the user refresh the page
    public void configureSwipeRefreshLayout(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    executeHttpRequestWithRetrofit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // --------------------
    // HTTP (RxJAVA)
    // --------------------


    //Avoid memory leaks
    public void disposeWhenDestroy() {
        if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    /**
     * Send the array of result to the recycler adapter for Top Stories and Most Popular
     * @param result list of NewYorkTimesAPI
     */
    public void updateUI(NewYorkTimesAPI result) {
        mSwipeRefreshLayout.setRefreshing(false);
        this.mArray.clear();
        this.mArray.addAll(result.getResults());
        this.mAdapter.notifyDataSetChanged();
    }

    /**
     * Send the array of result to the recycler adapter for Search Articles
     * @param result list of NewYorkTimesAPI
     */
    public void updateSearchResultUI(NewYorkTimesAPI result) {
        mSwipeRefreshLayout.setRefreshing(false);
        this.mArray.clear();
        this.mArray.addAll(result.getResponse().getDocs());
        this.mAdapter.notifyDataSetChanged();
    }
}
