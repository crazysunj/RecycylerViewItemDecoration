package com.crazysunj.recycylerviewitemdecoration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.crazysunj.itemdecoration.grid.GridLayoutDividerItemDecoration;
import com.crazysunj.itemdecoration.grid.GridLayoutSpaceItemDecoration;
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

public class GridLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        Intent intent = getIntent();
        final int type = intent.getIntExtra(TYPE, 0);
        final int left = intent.getIntExtra(LEFT, 0);
        final int top = intent.getIntExtra(TOP, 0);
        final int right = intent.getIntExtra(RIGHT, 0);
        final int bottom = intent.getIntExtra(BOTTOM, 0);
        @Orientation final int orientation = intent.getIntExtra(ORIENTATION, LinearLayout.VERTICAL);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        List<String> strings = Arrays.asList(MainActivity.getList());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(strings.subList(1, strings.size()), orientation == LinearLayoutManager.HORIZONTAL);
        RecyclerView.ItemDecoration itemDecoration;
        if (type == SPACE) {
            itemDecoration = new GridLayoutSpaceItemDecoration.Builder()
                    .setSpaceSize(30)
                    .build();
        } else if (type == DIVIDER) {
            itemDecoration = new GridLayoutDividerItemDecoration.Builder()
                    .setDividerColor(Color.RED)
                    .setLeftMargin(left)
                    .setTopMargin(top)
                    .setRightMargin(right)
                    .setBottomMargin(bottom)
                    .build();
        } else {
            itemDecoration = new GridLayoutDividerItemDecoration.Builder()
                    .setLeftMargin(left)
                    .setDividerDrawable(getResources().getDrawable(R.drawable.red2blue))
                    .setTopMargin(top)
                    .setRightMargin(right)
                    .setBottomMargin(bottom)
                    .build();
        }
        recyclerview.setLayoutManager(new GridLayoutManager(this, 3, orientation, false));
        recyclerview.addItemDecoration(itemDecoration);
        recyclerview.setAdapter(adapter);
    }
}
