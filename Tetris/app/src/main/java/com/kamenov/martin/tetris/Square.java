package com.kamenov.martin.tetris;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class Square implements GameObject{
    private int x;
    private int y;
    private Paint paint;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Constants.SQUARE_COLOR);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + Constants.CELL_WIDTH, y + Constants.CELL_HEIGHT, paint);
    }

    @Override
    public void update() {

    }

    public void down() {
        y = y + Constants.CELL_HEIGHT;
    }
}
