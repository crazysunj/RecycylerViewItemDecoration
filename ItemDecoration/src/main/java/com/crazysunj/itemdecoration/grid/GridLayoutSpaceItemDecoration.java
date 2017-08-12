package com.crazysunj.itemdecoration.grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crazysunj.itemdecoration.utils.ItemUtil;
import com.crazysunj.itemdecoration.utils.ViewUtil;

/**
 * author: sunjian
 * created on: 2017/8/1 上午9:51
 * description:适配GridLayoutManager，绘制间隔
 */
public class GridLayoutSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_HEIGHT = 10;
    private int mSpaceSize;

    private GridLayoutSpaceItemDecoration(Builder builder) {
        mSpaceSize = builder.spaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        int size = ViewUtil.dp2px(parent.getContext(), mSpaceSize);
        outRect.set(ItemUtil.isFirstColumn(parent, itemPosition, view) ? size : 0, ItemUtil.isFirstRaw(parent, itemPosition, view) ? size : 0, size, size);
    }

    public static class Builder {

        private int spaceSize = DEFAULT_HEIGHT;

        public Builder() {
        }

        public Builder setSpaceSize(int spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }

        public GridLayoutSpaceItemDecoration build() {
            return new GridLayoutSpaceItemDecoration(this);
        }
    }
}
