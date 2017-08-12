package com.crazysunj.itemdecoration.utils;

import android.content.Context;

/**
 * author: sunjian
 * created on: 2017/7/31 下午6:02
 * description:单位转换工具
 */

public class ViewUtil {
    private ViewUtil() {
    }

    public static int dp2px(Context context, int dpValue) {
        int density = (int) context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
