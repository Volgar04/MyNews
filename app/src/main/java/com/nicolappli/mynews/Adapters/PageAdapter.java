package com.nicolappli.mynews.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nicolappli.mynews.Controllers.Fragments.ArtsFragment;
import com.nicolappli.mynews.Controllers.Fragments.BusinessFragment;
import com.nicolappli.mynews.Controllers.Fragments.ScienceFragment;
import com.nicolappli.mynews.Controllers.Fragments.MostPopularFragment;
import com.nicolappli.mynews.Controllers.Fragments.PoliticsFragment;
import com.nicolappli.mynews.Controllers.Fragments.SportsFragment;
import com.nicolappli.mynews.Controllers.Fragments.TopStoriesFragment;
import com.nicolappli.mynews.Controllers.Fragments.TravelFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private String tabTitle[] = {"TOP STORIES", "MOST POPULAR", "ARTS", "BUSINESS", "SCIENCE", "POLITICS", "SPORTS", "TRAVEL"};

    // Default Constructor
    protected PageAdapter(FragmentManager mgr){
        super(mgr);
    }

    @Override
    public int getCount() {
        return tabTitle.length; // Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return ArtsFragment.newInstance();
            case 3:
                return BusinessFragment.newInstance();
            case 4:
                return ScienceFragment.newInstance();
            case 5:
                return PoliticsFragment.newInstance();
            case 6:
                return SportsFragment.newInstance();
            case 7:
                return TravelFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
