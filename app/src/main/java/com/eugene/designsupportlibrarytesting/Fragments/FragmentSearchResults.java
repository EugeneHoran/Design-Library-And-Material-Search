package com.eugene.designsupportlibrarytesting.Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eugene.designsupportlibrarytesting.R;
import com.eugene.designsupportlibrarytesting.RecyclerViewUtil.ItemData;
import com.eugene.designsupportlibrarytesting.RecyclerViewUtil.MyRecyclerAdapter;
import com.eugene.designsupportlibrarytesting.RecyclerViewUtil.OnRecyclerViewItemClickListener;

public class FragmentSearchResults extends Fragment {
    private View v;

    private ImageView mImageBack, mImageVoiceClear;
    private EditText mSearch;
    private ProgressBar circularProgressbar;
    public static final String SEARCH_RESULTS = "search_results";

    int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search_results, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getActivity().getWindow();
            w.setStatusBarColor(Color.parseColor("#b3b3b3"));
        }
        circularProgressbar = (ProgressBar) v.findViewById(R.id.circularProgressbar);
        mSearch = (EditText) v.findViewById(R.id.edit_text_search);
        mImageBack = (ImageView) v.findViewById(R.id.image_search_back);
        mImageVoiceClear = (ImageView) v.findViewById(R.id.clearSearch);
        mSearch.setText(getArguments().getString(SEARCH_RESULTS));
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window w = getActivity().getWindow();
                    w.setStatusBarColor(getResources().getColor(R.color.primary_dark));
                }
            }
        });

        ItemData itemsData[] = {
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),
            new ItemData("Item " + position++),};

        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final MyRecyclerAdapter mAdapter = new MyRecyclerAdapter(itemsData);

        if (savedInstanceState != null) {
            circularProgressbar.setVisibility(View.GONE);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    circularProgressbar.setVisibility(View.GONE);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
            }, 1000);
        }

        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<ItemData>() {
            @Override
            public void onItemClick(View view, ItemData log) {
                Toast.makeText(getActivity(), log.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}


