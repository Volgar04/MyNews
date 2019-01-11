package com.nicolappli.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolappli.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends Fragment {


    public MostPopularPageFragment() { }

    public static MostPopularPageFragment newInstance(){
        return (new MostPopularPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular_page, container, false);
    }

}
