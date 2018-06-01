package com.nanodegree.yj.thingstodonearme.model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment1;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment10;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment11;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment12;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment13;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment2;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment3;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment4;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment5;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment6;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment7;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment8;
import com.nanodegree.yj.thingstodonearme.ui.CategoryFragment9;

/**
 * Created by u2stay1915 on 3/14/18.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 13;
    private String tabTitles[] = new String[] { "music", "visual-arts", "performing-arts",
            "film", "lectures-books", "fashion", "food-and-drink", "festivals-fairs",
            "charities", "sports-active-life", "nightlife", "kids-family", "other" };
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
            case 2:
                return CategoryFragment3.newInstance();
            case 3:
                return CategoryFragment4.newInstance();
            case 4:
                return CategoryFragment5.newInstance();
            case 5:
                return CategoryFragment6.newInstance();
            case 6:
                return CategoryFragment7.newInstance();
            case 7:
                return CategoryFragment8.newInstance();
            case 8:
                return CategoryFragment9.newInstance();
            case 9:
                return CategoryFragment10.newInstance();
            case 10:
                return CategoryFragment11.newInstance();
            case 11:
                return CategoryFragment12.newInstance();
            case 12:
                return CategoryFragment13.newInstance();
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
