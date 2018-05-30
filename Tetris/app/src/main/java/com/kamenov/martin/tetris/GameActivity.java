package com.kamenov.martin.tetris;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.COLS = 10;
        Constants.ROWS = 20;
        Constants.CELL_WIDTH = Constants.SCREEN_WIDTH/Constants.COLS;
        Constants.CELL_HEIGHT = Constants.SCREEN_HEIGHT/Constants.ROWS;
        Constants.GAMESPEED = 350;
        Constants.SQUARE_COLOR = Color.RED;
        Constants.GRID_COLOR = Color.WHITE;

        setContentView(new GamePanel(this));
    }
}
