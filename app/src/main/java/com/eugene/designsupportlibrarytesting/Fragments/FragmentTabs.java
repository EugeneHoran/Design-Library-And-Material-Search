package com.eugene.designsupportlibrarytesting.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.designsupportlibrarytesting.Utilities.MyViewPagerAdapter;
import com.eugene.designsupportlibrarytesting.R;

public class FragmentTabs extends Fragment {
    private FragmentCallbacks mCallbacks;
    private View v;
    ViewPager viewPager;
    MyViewPagerAdapter viewpagerArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tabs, container, false);
        Toolbar mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mToolbar.setTitle("Tabs");
        mToolbar.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.menuClick();
            }
        });
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        mCallbacks.menuSearch();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        TabLayout mTabs = (TabLayout) v.findViewById(R.id.tabs);
        mTabs.setTabTextColors(Color.parseColor("#80ffffff"), Color.parseColor("#ffffff"));
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        viewpagerArrayAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewpagerArrayAdapter);
        mTabs.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        return v;
    }

    public interface FragmentCallbacks {
        void menuClick();

        void menuSearch();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (FragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement Fragment Two.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
