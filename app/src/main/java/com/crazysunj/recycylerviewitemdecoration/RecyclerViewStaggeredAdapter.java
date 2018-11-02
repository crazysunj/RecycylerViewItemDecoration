package com.crazysunj.recycylerviewitemdecoration;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * author: sunjian
 * created on: 2017/8/1 上午11:44
 * description:
 */

public class RecyclerViewStaggeredAdapter extends RecyclerView.Adapter<RecyclerViewStaggeredAdapter.RecyclerViewVH> {

    private List<String> contents;
    private boolean isHorizontal = false;

    public RecyclerViewStaggeredAdapter(List<String> contents) {
        this.contents = contents;
    }

    public RecyclerViewStaggeredAdapter(List<String> contents, boolean isHorizontal) {
        this.contents = contents;
        this.isHorizontal = isHorizontal;
    }

    @Override
    public RecyclerViewVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewVH(LayoutInflater.from(parent.getContext()).inflate(isHorizontal ? R.layout.item_horizontal : R.layout.item_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewVH holder, int position) {
        holder.setData(contents.get(position));
    }

    @Override
    public int getItemCount() {
        return contents == null ? 0 : contents.size();
    }

    static class RecyclerViewVH extends RecyclerView.ViewHolder {

        private TextView textView;

        public RecyclerViewVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            params.height = (int) (200 + Math.random() * 400);
            textView.setLayoutParams(params);
        }

        private void setData(String content) {
            textView.setText(content);
        }
    }
}
