package com.eugene.designsupportlibrarytesting.Utilities;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.designsupportlibrarytesting.R;


public class MyViewPagerAdapter extends PagerAdapter {

    @Override
    public CharSequence getPageTitle(int position) { // Tab text
        if (position == 0) {
            return "Tab 1";
        }
        if (position == 1) {
            return "Tab 2";
        }
        if (position == 2) {
            return "Tab 3";
        }
        return getPageTitle(position);
    }

    /*
    Returns provided id's view (children) to populate pages (ViewGroup, parent) inside of ViewPager
     */
    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        int resId = 0;
        switch (position) {
            case 0:
                resId = R.id.tabs1;
                break;
            case 1:
                resId = R.id.tabs2;
                break;
            case 2:
                resId = R.id.tabs3;
                break;
        }
        return viewGroup.findViewById(resId);
    }

    @Override

    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }
}