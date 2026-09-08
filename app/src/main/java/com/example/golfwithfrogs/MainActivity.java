package com.example.golfwithfrogs;

import android.app.Activity;
import android.os.Bundle;

import com.example.golfwithfrogs.game.GameView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new GameView(this));
    }
}

// Весь файл
