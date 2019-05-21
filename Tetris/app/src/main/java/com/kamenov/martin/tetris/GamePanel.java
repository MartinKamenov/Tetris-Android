package com.kamenov.martin.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, GameObject {
    private MyThread thread;
    private Figure figure;
    private ArrayList<Square> squares;
    private Grid grid;
    private FigureCreator figureCreator;
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;
    private int score;
    private Paint scorePaint;

    public GamePanel(Context context) {
        super(context);
        Constants.MATRIX = new boolean[Constants.ROWS][Constants.COLS];
        figureCreator = new FigureCreator();
        squares = new ArrayList<>();
        figure = figureCreator.createFigure();
        for(int i = 0; i < figure.squares.size(); i++){
            squares.add(figure.squares.get(i));
            Constants.MATRIX[figure.squares.get(i).row()][figure.squares.get(i).col()] = true;
        }
        grid = new Grid();
        score = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Constants.SCORE_COLOR);
        scorePaint.setStyle(Paint.Style.FILL);
        scorePaint.setStrokeWidth(10);
        scorePaint.setTextSize(50);
        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MyThread(surfaceHolder, this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void update() {
        figure.update();
        if(!figure.isGoingDown()) {
            checkForRows();
            if(checkIfGameOver()) {
                stopGame();
                return;
            }
            figure = figureCreator.createFigure();
            for(int i = 0; i < figure.squares.size(); i++){
                squares.add(figure.squares.get(i));
            }
        }
    }


    private void checkForRows() {
        for(int i = 0; i < Constants.MATRIX.length; i++) {
            boolean rowIsFull = true;
            for(int j = 0; j < Constants.MATRIX[i].length; j++) {
                if(!Constants.MATRIX[i][j]) {
                    rowIsFull = false;
                    break;
                }
            }
            if(rowIsFull) {
                removeRow(i);
                score++;
            }
        }
    }

    private boolean checkIfGameOver() {
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < Constants.MATRIX[i].length; j++) {
                if(Constants.MATRIX[i][j]) {
                    return true;
                }
            }
        }

        return false;
    }

    private void stopGame() {
        thread.setRunning(false);
    }

    private void removeRow(int row) {
        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = false;
            if(squares.get(i).row() == row) {
                squares.remove(i);
                i--;
            }
            else if(squares.get(i).row() < row) {
                squares.get(i).down(1);
            }
        }

        for(int i = 0; i < squares.size(); i++) {
            Constants.MATRIX[squares.get(i).row()][squares.get(i).col()] = true;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Constants.BACKGROUND_COLOR);
        canvas.drawRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, backgroundPaint);

        for(int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(canvas);
        }
        grid.draw(canvas);

        canvas.drawText("Score: " + score, 0, 50, scorePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if(Math.abs(deltaX)>Math.abs(deltaY)) {
                    if(deltaX > 0) {
                        figure.move(Direction.RIGHT, 1);
                    }
                    else if(deltaX < 0) {
                        figure.move(Direction.LEFT, 1);
                    }
                }
                else if(Math.abs(deltaY)>=Math.abs(deltaX)) {
                    if(deltaY > 0) {
                        while(figure.canGoDown()) {
                            figure.move(Direction.DOWN, 1);
                        }
                        //figure.move(Direction.DOWN, 1);
                        //figure.rotate(Direction.COUNTER_CLOCKWISE);
                    }
                    else if(deltaY < 0) {
                        figure.rotate(Direction.CLOCKWISE);
                    }
                }

                break;
        }
        return true;
    }
}
