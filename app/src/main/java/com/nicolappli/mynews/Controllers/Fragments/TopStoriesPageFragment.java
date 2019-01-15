package com.nicolappli.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nicolappli.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesPageFragment extends Fragment {

    public TopStoriesPageFragment() { }

    public static TopStoriesPageFragment newInstance(){
        return (new TopStoriesPageFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_stories_page, container, false);



        return rootView;
    }

}
