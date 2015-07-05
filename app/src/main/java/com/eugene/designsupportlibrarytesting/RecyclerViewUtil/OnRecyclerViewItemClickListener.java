package com.eugene.designsupportlibrarytesting.RecyclerViewUtil;

import android.view.View;

public interface OnRecyclerViewItemClickListener<ItemData> {
    public void onItemClick(View view, ItemData log);
}
