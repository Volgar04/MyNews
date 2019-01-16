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

import com.nicolappli.mynews.Adapters.ArticleAdapter;
import com.nicolappli.mynews.R;
import com.nicolappli.mynews.Views.ArticleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesPageFragment extends Fragment {

    Context thisContext;
    RecyclerView recyclerView;
    ArticleAdapter adapter;

    List<ArticleItem> articleList;

    public TopStoriesPageFragment() { }

    public static TopStoriesPageFragment newInstance(){
        return (new TopStoriesPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisContext=container.getContext();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_stories_page, container, false);

        articleList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // Set the size of the RecyclerView has fixed

        recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));

        return rootView;
    }

}
