package com.kamenov.martin.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, GameObject {
    private MyThread thread;
    private boolean[][] matrix;
    private Figure figure;
    private ArrayList<Figure> figures;
    private Grid grid;
    private FigureCreator figureCreator;

    public GamePanel(Context context) {
        super(context);
        Constants.MATRIX = new boolean[Constants.ROWS][Constants.COLS];
        figureCreator = new FigureCreator();
        figures = new ArrayList<>();
        figure = figureCreator.createFigure();
        figures.add(figure);
        grid = new Grid();


        getHolder().addCallback(this);

        setFocusable(true);
        //figure = new Figure();
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
            figure = figureCreator.createFigure();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        figure.draw(canvas);
        grid.draw(canvas);
    }
}
