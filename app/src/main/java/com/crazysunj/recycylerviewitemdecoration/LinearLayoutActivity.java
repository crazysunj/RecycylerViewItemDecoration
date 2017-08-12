package com.crazysunj.recycylerviewitemdecoration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.crazysunj.itemdecoration.linear.LinearLayoutDividerItemDecoration;
import com.crazysunj.itemdecoration.linear.LinearLayoutSpaceItemDecoration;
import com.crazysunj.itemdecoration.Orientation;

import java.util.Arrays;
import java.util.List;

import static com.crazysunj.recycylerviewitemdecoration.MainActivity.BOTTOM;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.DIVIDER;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.LEFT;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.ORIENTATION;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.RIGHT;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.SPACE;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.TOP;
import static com.crazysunj.recycylerviewitemdecoration.MainActivity.TYPE;

public class LinearLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        Intent intent = getIntent();
        final int type = intent.getIntExtra(TYPE, 0);
        final int left = intent.getIntExtra(LEFT, 0);
        final int top = intent.getIntExtra(TOP, 0);
        final int right = intent.getIntExtra(RIGHT, 0);
        final int bottom = intent.getIntExtra(BOTTOM, 0);
        @Orientation final int orientation = intent.getIntExtra(ORIENTATION, LinearLayout.VERTICAL);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        List<String> strings = Arrays.asList(MainActivity.getList());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(strings.subList(1, strings.size()));
        RecyclerView.ItemDecoration itemDecoration;
        if (type == SPACE) {
            itemDecoration = new LinearLayoutSpaceItemDecoration.Builder()
                    .setOrientation(orientation)
                    .build();
        } else if (type == DIVIDER) {
            itemDecoration = new LinearLayoutDividerItemDecoration.Builder()
                    .setDividerColor(Color.RED)
                    .setLeftMargin(left)
                    .setTopMargin(top)
                    .setRightMargin(right)
                    .setBottomMargin(bottom)
                    .isShowLastDivider(false)
                    .setOrientation(orientation)
                    .build();
        } else {
            itemDecoration = new LinearLayoutDividerItemDecoration.Builder()
                    .setDividerDrawable(getResources().getDrawable(R.drawable.red2blue))
                    .setLeftMargin(left)
                    .setTopMargin(top)
                    .setRightMargin(right)
                    .setBottomMargin(bottom)
                    .setOrientation(orientation)
                    .build();
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(this, orientation, false));
        recyclerview.addItemDecoration(itemDecoration);
        recyclerview.setAdapter(adapter);
    }
}
