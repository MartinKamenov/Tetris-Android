package com.kamenov.martin.tetris;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Martin on 30.5.2018 г..
 */

public class MyThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private final GamePanel gamePanel;
    private boolean running;

    public MyThread(SurfaceHolder surfaceHolder, GamePanel panel) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = panel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        super.run();

        while (running) {
            Canvas canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                sleep(Constants.GAMESPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
