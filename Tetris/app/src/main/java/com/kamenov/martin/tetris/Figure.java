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
        ArrayList<int[]> coordinates = new ArrayList<>();
        ArrayList<Direction> directions = new ArrayList<>();

        boolean outOfRange = false;
        boolean colideWithFigure = false;
        boolean outOfRangeIteration = false;

        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = false;
            outOfRangeIteration = false;
            coordinates.add(new int[] {squares.get(i).getX(), squares.get(i).getY()});

            int newRow = squares.get(i).col() - minx;
            int newCol = -squares.get(i).row() + miny;
            newRow += miny;
            newCol += minx;

            if(newRow < 0) {
                directions.add(Direction.DOWN);
                outOfRange = true;
                outOfRangeIteration = true;
            }

            if(newRow >= Constants.ROWS) {
                directions.add(Direction.UP);
                outOfRange = true;
                outOfRangeIteration = true;
            }

            if(newCol < 0) {
                directions.add(Direction.RIGHT);
                outOfRange = true;
                outOfRangeIteration = true;
            }

            if(newCol >= Constants.COLS) {
                directions.add(Direction.LEFT);
                outOfRange = true;
                outOfRangeIteration = true;
            }

            if(!outOfRangeIteration && Constants.MATRIX[newRow][newCol]) {
                colideWithFigure = true;
            }

            squares.get(i).setX(newCol * Constants.CELL_WIDTH);
            squares.get(i).setY(newRow * Constants.CELL_HEIGHT);
        }

        if(!outOfRange && colideWithFigure) {
            returnState(coordinates);
            return;
        }

        if(outOfRange) {
            for (int i = 0; i < directions.size(); i++) {
                for (int j = 0; j < squares.size(); j++) {
                    Square square = squares.get(j);
                    switch (directions.get(i)) {
                        case UP:
                            square.setY(square.getY() - Constants.CELL_HEIGHT);
                            break;
                        case DOWN:
                            square.setY(square.getY() + Constants.CELL_HEIGHT);
                            break;
                        case LEFT:
                            square.setX(square.getX() - Constants.CELL_WIDTH);
                            break;
                        case RIGHT:
                            square.setX(square.getX() + Constants.CELL_WIDTH);
                            break;
                    }
                }
            }
        }
        outOfRange = false;
        colideWithFigure = false;

        for(int i = 0; i < squares.size(); i++) {
            if(squares.get(i).row() < 0 || squares.get(i).row() >= Constants.ROWS||
                    squares.get(i).col() < 0 || squares.get(i).col() >= Constants.COLS) {
                outOfRange = true;
                break;
            } else if(Constants.MATRIX[squares.get(i).row()][squares.get(i).col()]){
                colideWithFigure = true;
            }
        }

        if(outOfRange || colideWithFigure) {
            returnState(coordinates);
        }
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
