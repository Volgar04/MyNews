package com.nicolappli.mynews.Controllers.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nicolappli.mynews.Adapters.ArticleAdapter;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Utils.NetworkAsyncTask;
import com.nicolappli.mynews.Views.ArticleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesPageFragment extends Fragment implements NetworkAsyncTask.Listeners {

    Context thisContext;
    RecyclerView recyclerView;
    ArticleAdapter adapter;

    List<ArticleItem> articleList;

    @BindView(R.id.test11)
    TextView textview;

    public TopStoriesPageFragment() { }

    public static TopStoriesPageFragment newInstance(){
        return (new TopStoriesPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        thisContext=container.getContext();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_stories_page, container, false);
        ButterKnife.bind(this, rootView);

//        articleList = new ArrayList<>();

        //recyclerView = rootView.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true); // Set the size of the RecyclerView has fixed

//        recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));

        return rootView;
    }

    @OnClick(R.id.test11btn)
    public void submit(View view){
        this.executeHttpRequest();
    }

    private void executeHttpRequest(){
        new NetworkAsyncTask(this).execute("https://api.nytimes.com/svc/mostpopular/v2/emailed/1.json?api-key=AHGAejtcPdRPUyAnADLIR4H6g7nLW4E6");
    }

    @Override
    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    private void updateUIWhenStartingHTTPRequest(){
        this.textview.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textview.setText(response);
    }
}
