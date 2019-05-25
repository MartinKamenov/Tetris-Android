package com.kamenov.martin.tetris.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kamenov.martin.tetris.Constants;
import com.kamenov.martin.tetris.R;

public class MenuActivity extends Activity implements View.OnClickListener {

    private EditText rowsText;
    private EditText colsText;
    private EditText gameSpeedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.COLS = 10;
        Constants.ROWS = 20;
        Constants.GAMESPEED = 300;
        setContentView(R.layout.activity_menu);

        rowsText = findViewById(R.id.rows_count);
        rowsText.setText("" + Constants.ROWS);
        colsText = findViewById(R.id.cols_count);
        colsText.setText("" + Constants.COLS);
        gameSpeedText = findViewById(R.id.game_speed);
        gameSpeedText.setText("" + Constants.GAMESPEED);

        Button startGameButton = findViewById(R.id.start_game);
        startGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Constants.ROWS = Integer.parseInt(rowsText.getText().toString());
        Constants.COLS = Integer.parseInt(colsText.getText().toString());
        Constants.GAMESPEED = Integer.parseInt(gameSpeedText.getText().toString());
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }
}
