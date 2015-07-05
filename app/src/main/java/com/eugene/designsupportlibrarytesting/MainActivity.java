package com.eugene.designsupportlibrarytesting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.eugene.designsupportlibrarytesting.Fragments.FragmentBlankLoading;
import com.eugene.designsupportlibrarytesting.Fragments.FragmentNutrition;
import com.eugene.designsupportlibrarytesting.Fragments.FragmentSearch;
import com.eugene.designsupportlibrarytesting.Fragments.FragmentSearchResults;
import com.eugene.designsupportlibrarytesting.Fragments.FragmentTabs;
import com.eugene.designsupportlibrarytesting.Fragments.FragmentWeight;
import com.eugene.designsupportlibrarytesting.Utilities.MLRoundedImageView;

public class MainActivity extends AppCompatActivity implements
    FragmentTabs.FragmentCallbacks,
    FragmentNutrition.FragmentCallbacks,
    FragmentWeight.FragmentCallbacks,
    FragmentSearch.FragmentCallbacks {

    private DrawerLayout mNavigationDrawer;
    private Fragment fragment;
    private TextView mHeaderText;

    private static final String NAV_ITEM_ID = "navItemId";
    private int mNavItemId;

    private static final String FIRST_FRAGMENT_ADDED = "is_first_fragment_added";
    private boolean isFirstFragmentAdded = false;

    private static final String TAG_FRAGMENT_SEARCH_RESULT = "search_result_tag";

    MLRoundedImageView imageView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
            isFirstFragmentAdded = savedInstanceState.getBoolean(FIRST_FRAGMENT_ADDED);
        } else {
            mNavItemId = R.id.nav_journal;
        }

        mHeaderText = (TextView) findViewById(R.id.headerText);

        mNavigationDrawer = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav);

        // select the correct nav menu item
        mNavigationView.getMenu().findItem(mNavItemId).setChecked(true);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() != R.id.nav_settings) {
                    mNavItemId = menuItem.getItemId();
                    switchFragment(menuItem.getItemId());
                    menuItem.setChecked(true);
                    Fragment loading = new FragmentBlankLoading();
                    Bundle b = new Bundle();
                    b.putInt("menuId", mNavItemId);
                    loading.setArguments(b);
                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, loading).commit();
                } else {
                    Intent settings = new Intent(MainActivity.this, Settings.class);
                    startActivity(settings);
                }
                handleNavigationDrawer();
                return false;
            }
        });
        switchFragment(mNavItemId);
        imageView = (MLRoundedImageView) findViewById(R.id.imageView);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        imageView.setImageResource(R.drawable.me);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.dad);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Handles Navigation Logic
     */
    private void switchFragment(int menuId) {
        switch (menuId) {
            case R.id.nav_journal:
                fragment = new FragmentTabs();
                mHeaderText.setText("Tabs");
                break;
            case R.id.nav_nutrition:
                fragment = new FragmentNutrition();
                mHeaderText.setText("AppBar");
                break;
            case R.id.nav_weight:
                fragment = new FragmentWeight();
                mHeaderText.setText("Weight");
                break;
            default:
                break;
        }
        if (fragment != null && !isFirstFragmentAdded) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
            isFirstFragmentAdded = true;
        }
        if (mNavigationDrawer != null)
            mNavigationDrawer.setDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerClosed(View drawerView) {
                    // Check to see if fragment is NUTRITION, has child fragments.
                    if (fragment != null && isFirstFragmentAdded) {
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment).commit();
                        isFirstFragmentAdded = true;
                    }
                }

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                }

                @Override
                public void onDrawerStateChanged(int newState) {
                }
            });
    }


    /**
     * Listeners: Handles fragment navigation icon click
     */
    @Override
    public void menuClick() {
        handleNavigationDrawer();
    }

    @Override
    public void menuSearch() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerSearch, new FragmentSearch()).addToBackStack(null).commit();
    }

    @Override
    public void searchClicked(String searchText) {
        Bundle bundle = new Bundle();
        bundle.putString(FragmentSearchResults.SEARCH_RESULTS, searchText);
        FragmentSearchResults fragmentSearchResults = new FragmentSearchResults();
        fragmentSearchResults.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerSearch, fragmentSearchResults, TAG_FRAGMENT_SEARCH_RESULT).addToBackStack(null).commit();
    }

    /**
     * Used to handle the closing and opening of the Navigation Drawer.
     * Prevent repetitive statements
     */
    private void handleNavigationDrawer() {
        if (mNavigationDrawer != null) {
            if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
                mNavigationDrawer.closeDrawer(GravityCompat.START);
            } else {
                mNavigationDrawer.openDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, mNavItemId);
        outState.putBoolean(FIRST_FRAGMENT_ADDED, isFirstFragmentAdded);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavigationDrawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_SEARCH_RESULT) != null) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_SEARCH_RESULT)).commit();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window w = getWindow();
                w.setStatusBarColor(getResources().getColor(R.color.primary_dark));
            }
        } else {
            super.onBackPressed();
        }
    }
}
