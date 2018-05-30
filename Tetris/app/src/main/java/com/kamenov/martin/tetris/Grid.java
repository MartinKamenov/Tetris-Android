package com.kamenov.martin.tetris;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class Grid implements GameObject {
    private Paint paint;

    public Grid() {
        this.paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Constants.GRID_COLOR);
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 1; i < Constants.COLS; i++) {
            canvas.drawLine(Constants.CELL_WIDTH * i, 0, Constants.CELL_WIDTH * i, Constants.SCREEN_HEIGHT, paint);
        }

        for(int i = 1; i < Constants.ROWS; i++) {
            canvas.drawLine(0, Constants.CELL_HEIGHT * i, Constants.SCREEN_WIDTH, Constants.CELL_HEIGHT * i, paint);
        }
    }

    @Override
    public void update() {

    }
}
