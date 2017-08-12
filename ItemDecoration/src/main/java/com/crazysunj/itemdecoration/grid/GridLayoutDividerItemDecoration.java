package com.crazysunj.itemdecoration.grid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.crazysunj.itemdecoration.utils.ItemUtil;

/**
 * author: sunjian
 * created on: 2017/8/1 上午9:53
 * description:适配GridLayoutManager，绘制网格线
 */
@SuppressWarnings("SuspiciousNameCombination")
public class GridLayoutDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_HEIGHT = 2;
    private Drawable mDivider;
    private Builder mBuilder;

    private GridLayoutDividerItemDecoration(Builder builder) {
        this.mBuilder = builder;
        if (builder.dividerDrawable == null) {
            mDivider = new ColorDrawable(Color.GRAY) {
                @Override
                public int getIntrinsicHeight() {
                    return mBuilder.dividerHeight;
                }

                @Override
                public int getIntrinsicWidth() {
                    return mBuilder.dividerHeight;
                }
            };
            ((ColorDrawable) mDivider).setColor(builder.dividerColor);
        } else {
            mDivider = builder.dividerDrawable;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            boolean isVertical = manager.getOrientation() == LinearLayoutManager.VERTICAL;
            drawHorizontal(c, parent, spanCount, isVertical, true);
            drawVertical(c, parent, spanCount, isVertical, true);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            boolean isVertical = manager.getOrientation() == LinearLayoutManager.VERTICAL;
            drawHorizontal(c, parent, spanCount, isVertical, false);
            drawVertical(c, parent, spanCount, isVertical, false);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, int spanCount, boolean isVertical, boolean isGrid) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            final View child = parent.getChildAt(i);
            // 最后一行，不需要绘制底边线
            if (ItemUtil.isLastRaw(i, childCount, spanCount, isVertical, isGrid, child)) {
                continue;
            }

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();

            int leftMargin = 0;
            int rightMargin = 0;
            if (isGrid) {
                if (isVertical) {
                    if (i % spanCount == 0) {
                        leftMargin = mBuilder.leftMargin;//第一列
                    }

                    if ((i + 1) % spanCount == 0) {
                        rightMargin = mBuilder.rightMargin;//最后一列
                    }
                } else {
                    if (i / spanCount == 0) {
                        //第一列
                        leftMargin = mBuilder.leftMargin;
                    }

                    if (i >= (childCount - childCount % spanCount)) {
                        rightMargin = mBuilder.rightMargin;//最后一列
                    }
                }
            }
            mDivider.setBounds(left + leftMargin, top, right - rightMargin, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent, int spanCount, boolean isVertical, boolean isGrid) {

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (!isGrid) {
                final int sTop = child.getTop() - params.topMargin - mDivider.getIntrinsicHeight();
                final int sBottom = child.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight();
                final int sLeft = child.getLeft() - params.leftMargin - mDivider.getIntrinsicWidth();
                final int sRight = sLeft + mDivider.getIntrinsicWidth();
                mDivider.setBounds(sLeft, sTop, sRight, sBottom);
                mDivider.draw(c);
            }
            // 最后一列，不需要绘制右边线
            if (ItemUtil.isLastColumn(i, childCount, spanCount, isVertical, isGrid, child)) {
                continue;
            }

            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();


            int topMargin = 0;
            int bottomMargin = 0;
            if (isGrid) {
                if (isVertical) {
                    if (i / spanCount == 0) {
                        //第一行
                        topMargin = mBuilder.topMargin;
                    }

                    if (i >= (childCount - childCount % spanCount)) {
                        bottomMargin = mBuilder.bottomMargin;//最后一行
                    }
                } else {
                    if (i % spanCount == 0) {
                        topMargin = mBuilder.topMargin;//第一行
                    }

                    if ((i + 1) % spanCount == 0) {
                        bottomMargin = mBuilder.bottomMargin;//最后一行
                    }
                }
            }
            mDivider.setBounds(left, top + topMargin, right, bottom - bottomMargin);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        int childCount = parent.getAdapter().getItemCount();
        if (ItemUtil.isLastRaw(parent, itemPosition, childCount, view)) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (ItemUtil.isLastColumn(parent, itemPosition, childCount, view)) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }
    }

    public static class Builder {

        private Drawable dividerDrawable;
        private int dividerHeight = DEFAULT_HEIGHT;
        private int dividerColor = Color.GRAY;
        private int leftMargin, rightMargin, topMargin, bottomMargin;

        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        public Builder setDividerHeight(int dividerHeight) {
            this.dividerHeight = dividerHeight;
            return this;
        }

        public Builder setDividerDrawable(Drawable dividerDrawable) {
            this.dividerDrawable = dividerDrawable;
            return this;
        }

        public Builder setLeftMargin(int leftMargin) {
            this.leftMargin = leftMargin;
            return this;
        }

        public Builder setRightMargin(int rightMargin) {
            this.rightMargin = rightMargin;
            return this;
        }

        public Builder setTopMargin(int topMargin) {
            this.topMargin = topMargin;
            return this;
        }

        public Builder setBottomMargin(int bottomMargin) {
            this.bottomMargin = bottomMargin;
            return this;
        }

        public GridLayoutDividerItemDecoration build() {
            return new GridLayoutDividerItemDecoration(this);
        }
    }
}
