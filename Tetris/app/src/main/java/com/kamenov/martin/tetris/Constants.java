package com.kamenov.martin.tetris;

import android.graphics.Color;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static int ROWS;
    public static int COLS;

    public static int CELL_WIDTH;
    public static int CELL_HEIGHT;

    public static int GAMESPEED;
    public static boolean[][] MATRIX;

    public static int SQUARE_COLOR;
    public static int GRID_COLOR;
    public static int BACKGROUND_COLOR;
    public static int SCORE_COLOR;
    public static int[] COLORS = {
            Color.parseColor("#F52C6E"), Color.parseColor("#c61d55"),
            Color.parseColor("#0291DB"), Color.parseColor("#0075b2"),
            Color.parseColor("#19d668"), Color.parseColor("#10a04c"),
            Color.parseColor("#8d18d6"), Color.parseColor("#610d96"),
            Color.parseColor("#ff6607"), Color.parseColor("#d65200"),
            Color.parseColor("#070bff"), Color.parseColor("#00029b"),
    };
}
