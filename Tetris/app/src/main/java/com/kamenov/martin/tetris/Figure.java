package com.kamenov.martin.tetris;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class Figure implements GameObject {
    private boolean goingDown;
    public ArrayList<Square> squares;
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
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).down();
        }
    }

    public boolean isGoingDown() {
        return goingDown;
    }
}
