package com.crazysunj.recycylerviewitemdecoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    public static final String LEFT = "LEFT";
    public static final String TOP = "TOP";
    public static final String RIGHT = "RIGHT";
    public static final String BOTTOM = "BOTTOM";
    public static final String TYPE = "TYPE";
    public static final String ORIENTATION = "ORIENTATION";
    public static final int SPACE = 0;
    public static final int DIVIDER = 1;
    public static final int DRAWABLE = 2;

    private TextView tv_left;
    private TextView tv_top;
    private TextView tv_right;
    private TextView tv_bottom;
    private SeekBar sb_left;
    private SeekBar sb_top;
    private SeekBar sb_right;
    private SeekBar sb_bottom;
    private RadioButton rb_horizontal;
    private RadioButton rb_vertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_top = (TextView) findViewById(R.id.tv_top);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_bottom = (TextView) findViewById(R.id.tv_bottom);
        sb_left = (SeekBar) findViewById(R.id.sb_left);
        sb_top = (SeekBar) findViewById(R.id.sb_top);
        sb_right = (SeekBar) findViewById(R.id.sb_right);
        sb_bottom = (SeekBar) findViewById(R.id.sb_bottom);
        rb_horizontal = (RadioButton) findViewById(R.id.rb_horizontal);
        rb_vertical = (RadioButton) findViewById(R.id.rb_vertical);
        sb_left.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_left.setText(String.format(Locale.getDefault(), "l:%d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_top.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_top.setText(String.format(Locale.getDefault(), "t:%d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_right.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_right.setText(String.format(Locale.getDefault(), "r:%d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_bottom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_bottom.setText(String.format(Locale.getDefault(), "b:%d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onclick1(View view) {
        Intent intent = new Intent(this, LinearLayoutActivity.class);
        intent.putExtra(TYPE, SPACE);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        intent.putExtra(ORIENTATION, rb_horizontal.isChecked() ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        intent.putExtra(LEFT, sb_left.getProgress());
        intent.putExtra(TOP, sb_top.getProgress());
        intent.putExtra(RIGHT, sb_right.getProgress());
        intent.putExtra(BOTTOM, sb_bottom.getProgress());
        startActivity(intent);
    }

    public void onclick2(View view) {
        Intent intent = new Intent(this, LinearLayoutActivity.class);
        intent.putExtra(TYPE, DIVIDER);
        handleIntent(intent);
    }

    public void onclick3(View view) {
        Intent intent = new Intent(this, LinearLayoutActivity.class);
        intent.putExtra(TYPE, DRAWABLE);
        handleIntent(intent);
    }

    public void onclick4(View view) {
        Intent intent = new Intent(this, GridLayoutActivity.class);
        intent.putExtra(TYPE, SPACE);
        handleIntent(intent);
    }

    public void onclick5(View view) {
        Intent intent = new Intent(this, GridLayoutActivity.class);
        intent.putExtra(TYPE, DIVIDER);
        handleIntent(intent);
    }

    public void onclick6(View view) {
        Intent intent = new Intent(this, GridLayoutActivity.class);
        intent.putExtra(TYPE, DRAWABLE);
        handleIntent(intent);
    }

    public void onclick7(View view) {
        Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
        intent.putExtra(TYPE, SPACE);
        handleIntent(intent);
    }

    public void onclick8(View view) {
        Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
        intent.putExtra(TYPE, DIVIDER);
        handleIntent(intent);
    }

    public void onclick9(View view) {
        Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
        intent.putExtra(TYPE, DRAWABLE);
        handleIntent(intent);
    }

    public static String[] getList() {
        return "天生我材必有用千金散尽还复来".split("");
    }
}
