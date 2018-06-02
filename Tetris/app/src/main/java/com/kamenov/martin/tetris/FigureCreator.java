package com.kamenov.martin.tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class FigureCreator {
    private int[][][] numbers = {
            {{(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT},
                    {(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0}},
            {{(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT},
                    {(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0}},
            {{(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2 - 2) * Constants.CELL_WIDTH, 0},
                    {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, 0}},
            {{(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT},
                    {(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0}},
            {{(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0},
                    {(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT}},
            {{(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0},
                    {(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, 0}, {(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT}},
            {{(Constants.COLS / 2 + 1) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT},
                    {(Constants.COLS / 2 - 1) * Constants.CELL_WIDTH, Constants.CELL_HEIGHT}, {(Constants.COLS / 2) * Constants.CELL_WIDTH, 0}},
    };

    public Figure createFigure() {
        Random rnd = new Random();
        int number = rnd.nextInt(numbers.length);
        int colorNumber = rnd.nextInt(Constants.COLORS.length);
        ArrayList<Square> list = new ArrayList<>();
        int color = Constants.COLORS[colorNumber];
        for(int i = 0; i < numbers[number].length; i++) {
            list.add(new Square(numbers[number][i][0], numbers[number][i][1], color));
        }

        return new Figure(list);
    }
}
