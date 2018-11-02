package com.crazysunj.itemdecoration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * author: sunjian
 * created on: 2017/8/1 上午9:14
 * description:布局方向
 */
@IntDef({LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL})
@Retention(RetentionPolicy.SOURCE)
public @interface Orientation {
}
