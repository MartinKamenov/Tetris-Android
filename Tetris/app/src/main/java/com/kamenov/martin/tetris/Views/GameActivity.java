package com.kamenov.martin.tetris.Views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.kamenov.martin.tetris.Constants;
import com.kamenov.martin.tetris.GamePanel;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels - 40;

        Constants.CELL_WIDTH = Constants.SCREEN_WIDTH/Constants.COLS;
        Constants.CELL_HEIGHT = Constants.SCREEN_HEIGHT/Constants.ROWS;

        Constants.SQUARE_COLOR = Color.BLUE;
        Constants.GRID_COLOR = Color.BLACK;
        Constants.BACKGROUND_COLOR = Color.WHITE;
        Constants.SCORE_COLOR = Color.parseColor("#60000000");

        setContentView(new GamePanel(this));
    }
}
