package com.kamenov.martin.tetris;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class Figure implements GameObject {
    private boolean goingDown;
    public ArrayList<Square> squares;
    private int minx;
    private int miny;
    private int newRow;
    private int newCol;
    private int maxx;
    private int maxy;


    public Figure(ArrayList<Square> squares) {
        this.squares = squares;
        goingDown = true;
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

    private boolean canGoUp() {
        for (int i = 0; i < squares.size(); i++) {
            if(!squares.get(i).canGoUp(this)) {
                return false;
            }
        }

        return true;
    }

    public boolean canGoDown() {
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
        if((direction == direction.LEFT && !canGoLeft() )||(direction == direction.RIGHT && !canGoRight())
                ||(direction == direction.DOWN && !canGoDown() )||(direction == direction.UP && !canGoUp())) {
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
        if(!canFigureRotateLeft()) {
            return;
        }
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

    private boolean canFigureRotateLeft() {
        boolean canRotateLeft = true;
        for(int i = 0; i < squares.size(); i++) {

            int newRow = squares.get(i).col() - minx;
            int newCol = -squares.get(i).row() + miny;
            newRow += miny;
            newCol += minx;

            if(newRow < 0 || newRow >= Constants.ROWS ||
                newCol < 0 || newCol >= Constants.COLS) {
                return false;
            }

            if(Constants.MATRIX[newRow][newCol]) {
                boolean isFromCurrentFigure = false;
                for(int j = 0; j < squares.size(); j++) {
                    if(squares.get(j).getX() / Constants.CELL_WIDTH == newCol &&
                        squares.get(j).getY() / Constants.CELL_HEIGHT == newRow) {
                        isFromCurrentFigure = true;
                    }
                }

                if(!isFromCurrentFigure) {
                    canRotateLeft = false;
                    break;
                }
            }
        }

        return canRotateLeft;
    }

    private void rotateRight() {
        ArrayList<int[]> coordinates = new ArrayList<>();

        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = false;
            coordinates.add(new int[] {squares.get(i).row(), squares.get(i).col()});

            int newRow = - squares.get(i).col() + minx;
            int newCol = squares.get(i).row() - miny;
            newRow += miny;
            newCol += minx;

            squares.get(i).setX(newCol * Constants.CELL_WIDTH);
            squares.get(i).setY(newRow * Constants.CELL_HEIGHT);
        }
    }

    private void returnState(ArrayList<int[]> coordinates) {
        for(int i = 0; i < squares.size(); i++) {
            squares.get(i).setX(coordinates.get(i)[0]);
            squares.get(i).setY(coordinates.get(i)[1]);
            Constants.MATRIX[coordinates.get(i)[0] / Constants.CELL_WIDTH][coordinates.get(i)[0] / Constants.CELL_HEIGHT] = true;
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
