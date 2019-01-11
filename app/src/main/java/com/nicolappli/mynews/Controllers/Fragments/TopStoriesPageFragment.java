package com.nicolappli.mynews.Controllers.Fragments;


import android.os.Bundle;
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

    private Button test;

    public TopStoriesPageFragment() { }

    public static TopStoriesPageFragment newInstance(){
        return (new TopStoriesPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_stories_page, container, false);

        test = rootView.findViewById(R.id.test1);
        test.setOnClickListener(btnTest);

        return rootView;
    }

    private View.OnClickListener btnTest = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("TAG", "Long action is starting...");
            Long endTime = System.currentTimeMillis() + 7000;
            while (System.currentTimeMillis() <  endTime) {
                //Loop during 7 secs hehehe...
            }
            Log.e("TAG", "Long action is finished !");
        }
    };

}
