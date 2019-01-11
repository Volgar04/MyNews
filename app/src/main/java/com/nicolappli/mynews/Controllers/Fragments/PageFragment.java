package com.nicolappli.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicolappli.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    // Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";


    public PageFragment() {}

    // Method that will create a new instance of PageFragment and add data to its bundle
    public static PageFragment newInstance(int position, int color){
        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        frag.setArguments(args);

        
        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        //Get widgets from layout and serialise it
        LinearLayout rootView = result.findViewById(R.id.fragment_page_rootview);
        TextView textView = result.findViewById(R.id.fragment_page_title);

        //Get data from Bundle
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        // Update widgets with it
        rootView.setBackgroundColor(color);
        textView.setText("Page numéro "+position);

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }

}
