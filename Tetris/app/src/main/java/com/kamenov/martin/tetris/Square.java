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
    private Paint insidePaint;

    public Square(int x, int y, int color, int secondColor) {
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(secondColor);
        insidePaint = new Paint();
        insidePaint.setStyle(Paint.Style.FILL);
        insidePaint.setColor(color);
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
        canvas.drawRect(x, y,
                x + ((Constants.CELL_WIDTH / 8) * 7), y + ((Constants.CELL_HEIGHT / 8) * 7), insidePaint);
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

    public boolean canGoLeft(Figure figure) {
        if(col() - 1 < 0) {
            return false;
        }
        if(Constants.MATRIX[row()][col() - 1]) {
            for(int i = 0; i < figure.squares.size(); i++) {
                if(((col() - 1) == figure.squares.get(i).col()) && (row() == figure.squares.get(i).row())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    public boolean canGoRight(Figure figure) {
        if(col() + 1 >= Constants.COLS) {
            return false;
        }
        if(Constants.MATRIX[row()][col() + 1]) {
            for(int i = 0; i < figure.squares.size(); i++) {
                if(((col() + 1) == figure.squares.get(i).col()) && (row() == figure.squares.get(i).row())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    public boolean canGoUp(Figure figure) {
        if(row() - 1 < 0) {
            return false;
        }
        if(Constants.MATRIX[row() - 1][col()]) {
            for(int i = 0; i < figure.squares.size(); i++) {
                if(((row() - 1) == figure.squares.get(i).row()) && (col() == figure.squares.get(i).col())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }
}
