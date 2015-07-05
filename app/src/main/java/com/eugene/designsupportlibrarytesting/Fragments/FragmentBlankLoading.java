package com.eugene.designsupportlibrarytesting.Fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.designsupportlibrarytesting.R;

public class FragmentBlankLoading extends Fragment {
    View v;
    int menuId;
    Toolbar mToolbar, mToolbarAppBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_blank_loading, container, false);
        Bundle extras = getArguments();
        if (extras != null) {
            menuId = extras.getInt("menuId");
        }
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar_blank);
        mToolbarAppBar = (Toolbar) v.findViewById(R.id.toolbar);

        CoordinatorLayout main_content = (CoordinatorLayout) v.findViewById(R.id.main_content);
        main_content.setVisibility(View.GONE);
        switch (menuId) {
            case R.id.nav_journal:
                mToolbar.setTitle("Tabs");
                mToolbar.inflateMenu(R.menu.menu_main);
                break;
            case R.id.nav_nutrition:
                main_content.setVisibility(View.VISIBLE);
                mToolbar.setVisibility(View.GONE);
                mToolbarAppBar.inflateMenu(R.menu.menu_main);
                CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar);
                collapsingToolbar.setTitle("AppBar");
                collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
                collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.white));
                break;
            case R.id.nav_weight:
                mToolbar.setTitle("Weight");
                break;
            default:
                break;
        }
        return v;
    }
}

