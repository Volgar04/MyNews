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
public class BusinessPageFragment extends Fragment {


    public BusinessPageFragment() { }

    public static BusinessPageFragment newInstance(){
        return (new BusinessPageFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_page, container, false);
    }

}
