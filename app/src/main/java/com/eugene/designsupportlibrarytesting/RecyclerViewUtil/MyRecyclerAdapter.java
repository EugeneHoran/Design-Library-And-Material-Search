package com.eugene.designsupportlibrarytesting.RecyclerViewUtil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eugene.designsupportlibrarytesting.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements View.OnClickListener {
    private ItemData[] itemsData;
    private OnRecyclerViewItemClickListener<ItemData> itemClickListener;

    public MyRecyclerAdapter(ItemData[] itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycler_view, null);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        ItemData item = itemsData[position];
        viewHolder.itemView.setTag(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewTitle;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            ItemData itemData = (ItemData) view.getTag();
            itemClickListener.onItemClick(view, itemData);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<ItemData> listener) {
        this.itemClickListener = listener;
    }
}