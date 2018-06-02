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

    public Square(int x, int y, int color) {
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int col() {
        return this.x / Constants.CELL_WIDTH;
    }

    public int row() {
        return this.y / Constants.CELL_HEIGHT;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + Constants.CELL_WIDTH, y + Constants.CELL_HEIGHT, paint);
    }

    @Override
    public void update() {

    }

    public void down(int step) {
        y = y + step * Constants.CELL_HEIGHT;
    }

    public void left(int step) {
        x = x - step * Constants.CELL_WIDTH;
    }

    public void up(int step) {
        y = y - step * Constants.CELL_HEIGHT;
    }

    public void right(int step) {
        x = x + step * Constants.CELL_WIDTH;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean canGoDown(Figure figure) {
        if(row() + 1 >= Constants.ROWS) {
            return false;
        }
        if(Constants.MATRIX[row() + 1][col()]) {
            for(int i = 0; i < figure.squares.size(); i++) {
                if(((row() + 1) == figure.squares.get(i).row()) && (col() == figure.squares.get(i).col())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }
}
