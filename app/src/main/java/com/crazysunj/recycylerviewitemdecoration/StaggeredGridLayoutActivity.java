package com.crazysunj.recycylerviewitemdecoration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
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

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);
        Intent intent = getIntent();
        final int type = intent.getIntExtra(TYPE, 0);
        final int left = intent.getIntExtra(LEFT, 0);
        final int top = intent.getIntExtra(TOP, 0);
        final int right = intent.getIntExtra(RIGHT, 0);
        final int bottom = intent.getIntExtra(BOTTOM, 0);
        @Orientation final int orientation = intent.getIntExtra(ORIENTATION, LinearLayout.VERTICAL);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        List<String> strings = Arrays.asList(MainActivity.getList());
        RecyclerViewStaggeredAdapter adapter = new RecyclerViewStaggeredAdapter(strings.subList(1, strings.size()), orientation == LinearLayoutManager.HORIZONTAL);
        RecyclerView.ItemDecoration itemDecoration;
        if (type == SPACE) {
            itemDecoration = new GridLayoutSpaceItemDecoration.Builder()
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
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, orientation);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(itemDecoration);
        recyclerview.setAdapter(adapter);
    }
}
