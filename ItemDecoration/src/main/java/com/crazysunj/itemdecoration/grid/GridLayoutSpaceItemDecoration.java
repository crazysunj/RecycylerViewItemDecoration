package com.crazysunj.itemdecoration.grid;

import android.graphics.Rect;
import android.view.View;

import com.crazysunj.itemdecoration.utils.ItemUtil;
import com.crazysunj.itemdecoration.utils.ViewUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        int size = (int) (ViewUtil.dp2px(parent.getContext(), mSpaceSize) * 1.0f / 2);
        outRect.set(ItemUtil.isFirstColumn(parent, itemPosition, view) ? size * 2 : size,
                ItemUtil.isFirstRaw(parent, itemPosition, view) ? size * 2 : size,
                ItemUtil.isLastColumn(parent, itemPosition, parent.getChildCount(), view) ? size * 2 : size,
                ItemUtil.isLastRaw(parent, itemPosition) ? size * 2 : size);
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
