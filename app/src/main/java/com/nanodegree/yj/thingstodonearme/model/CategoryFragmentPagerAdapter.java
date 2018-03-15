package com.nanodegree.yj.thingstodonearme.model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment1;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment2;

/**
 * Created by u2stay1915 on 3/14/18.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "music", "visual-arts", "performing-arts",
            "film", "lectures-books", "fashion" };
    private Context mContext;

    public CategoryFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CategoryFragment1.newInstance();
            case 1:
                return CategoryFragment2.newInstance();
            default:
                return null;

        }


        //return CategoryFragment1.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
