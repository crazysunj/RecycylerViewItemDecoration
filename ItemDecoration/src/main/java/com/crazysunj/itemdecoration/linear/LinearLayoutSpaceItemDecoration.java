package com.crazysunj.itemdecoration.linear;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crazysunj.itemdecoration.Orientation;
import com.crazysunj.itemdecoration.utils.ViewUtil;


/**
 * author: sunjian
 * created on: 2017/8/1 上午9:20
 * description:适合LinearLayoutManager的RecyclerView,设置空白间隔
 */
public class LinearLayoutSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private static final int DEFAULT_HEIGHT = 10;
    private int mSpaceSize;
    private int mOrientation;

    private LinearLayoutSpaceItemDecoration(Builder builder) {
        mSpaceSize = builder.spaceSize;
        mOrientation = builder.orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        if (position == itemCount - 1) {//最后一个不需要
            return;
        }
        int size = ViewUtil.dp2px(parent.getContext(), mSpaceSize);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, size);
        } else {
            outRect.set(0, 0, size, 0);
        }
    }

    public static class Builder {

        private int spaceSize = DEFAULT_HEIGHT;
        private int orientation = LinearLayoutManager.VERTICAL;

        public Builder setSpaceSize(int spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }

        public Builder setOrientation(@Orientation int orientation) {
            this.orientation = orientation;
            return this;
        }

        public LinearLayoutSpaceItemDecoration build() {
            return new LinearLayoutSpaceItemDecoration(this);
        }
    }
}
