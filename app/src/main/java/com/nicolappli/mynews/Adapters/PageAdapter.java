package com.nicolappli.mynews.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.nicolappli.mynews.Controllers.Fragments.BusinessPageFragment;
import com.nicolappli.mynews.Controllers.Fragments.MostPopularPageFragment;
import com.nicolappli.mynews.Controllers.Fragments.TopStoriesPageFragment;

public class PageAdapter extends FragmentPagerAdapter {
    public static final String[] topStoriesSection={"home", "", "business"};
    private String tabTitle[] = {"TOP STORIES", "MOST POPULAR", "BUSINESS"};

    // Default Constructor
    public PageAdapter(FragmentManager mgr){
        super(mgr);
    }

    @Override
    public int getCount() {
        return tabTitle.length; // Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: // Page number 1
                return TopStoriesPageFragment.newInstance();
            case 1: // Page number 2
                return MostPopularPageFragment.newInstance();
            case 2: // Page number 3
                return BusinessPageFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: // Page number 1
                return "TOP STORIES";
            case 1: // Page number 2
                return "MOST POPULAR";
            case 2: // Page number 3
                return "BUSINESS";
            default:
                return null;
        }
    }


}
