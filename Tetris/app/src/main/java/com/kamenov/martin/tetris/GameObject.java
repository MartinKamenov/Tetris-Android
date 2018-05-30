package com.kamenov.martin.tetris;

import android.graphics.Canvas;

/**
 * Created by Martin on 30.5.2018 г..
 */

public interface GameObject {
    void draw(Canvas canvas);

    void update();
}
