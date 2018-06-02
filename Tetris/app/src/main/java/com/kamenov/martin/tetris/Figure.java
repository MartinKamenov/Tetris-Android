package com.kamenov.martin.tetris;

import android.graphics.Canvas;
import android.graphics.Path;

import java.util.ArrayList;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class Figure implements GameObject {
    private boolean goingDown;
    public ArrayList<Square> squares;
    private int rotationState;
    private int minx;
    private int miny;
    private int newRow;
    private int newCol;
    private int maxx;
    private int maxy;


    public Figure(ArrayList<Square> squares) {
        this.squares = squares;
        goingDown = true;
        rotationState = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(canvas);
        }
    }

    @Override
    public void update() {
        if(canGoDown()) {
            move(Direction.DOWN, 1);
        } else {
            goingDown = false;
        }
    }

    private boolean canGoDown() {
        for (int i = 0; i < squares.size(); i++) {
            if(!squares.get(i).canGoDown(this)) {
                return false;
            }
        }

        return true;
    }

    private boolean canGoLeft() {
        for (int i = 0; i < squares.size(); i++) {
            if(!squares.get(i).canGoLeft(this)) {
                return false;
            }
        }

        return true;
    }

    private boolean canGoRight() {
        for (int i = 0; i < squares.size(); i++) {
            if(!squares.get(i).canGoRight(this)) {
                return false;
            }
        }

        return true;
    }

    public void move(Direction direction, int step) {
        if((direction == direction.LEFT && !canGoLeft() )||(direction == direction.RIGHT && !canGoRight())) {
            return;
        }
        for (int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = false;
            switch (direction) {
                case LEFT:
                    squares.get(i).left(step);
                    break;
                case RIGHT:
                    squares.get(i).right(step);
                    break;
                case DOWN:
                    squares.get(i).down(step);
                    break;
                case UP:
                    squares.get(i).up(step);
                    break;
            }

        }

        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = true;
        }
    }

    private void rotateLeft() {
        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = false;

            int newRow = squares.get(i).col() - minx;
            int newCol = -squares.get(i).row() + miny;
            newRow += miny;
            newCol += minx;

            squares.get(i).setX(newCol * Constants.CELL_WIDTH);
            squares.get(i).setY(newRow * Constants.CELL_HEIGHT);
        }
    }

    private void rotateRight() {
        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = false;

            int newRow = - squares.get(i).col() + minx;
            int newCol = squares.get(i).row() - miny;
            newRow += miny;
            newCol += minx;

            squares.get(i).setX(newCol * Constants.CELL_WIDTH);
            squares.get(i).setY(newRow * Constants.CELL_HEIGHT);
        }
    }

    public void rotate(Direction direction) {
        minx = Constants.CELL_WIDTH;
        miny = Constants.CELL_HEIGHT;
        maxx = 0;
        maxy = 0;
        for(int i = 0; i < squares.size(); i++) {
            if(minx > squares.get(i).col()) {
                minx = squares.get(i).col();
            }
            if(miny > squares.get(i).row()) {
                miny = squares.get(i).row();
            }
            if(maxx < squares.get(i).col()) {
                maxx = squares.get(i).col();
            }
            if(maxy < squares.get(i).row()) {
                maxy = squares.get(i).row();
            }
        }

        int differenceX = (maxx - minx) / 2;
        int differenceY = (maxy - miny) / 2;

        minx += differenceX;
        miny += differenceY;

        if(direction == Direction.CLOCKWISE) {
            rotateLeft();
        } else if(direction == Direction.COUNTER_CLOCKWISE) {
            rotateRight();
        }

        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = true;
        }
    }

    public boolean isGoingDown() {
        return goingDown;
    }
}
