package com.example.golfwithfrogs.physics;

import com.example.golfwithfrogs.objects.Ball;
import com.example.golfwithfrogs.objects.Wall;

import java.util.List;

public class Physics {
    public Physics() {
    }

    public void update(Ball ball, List<Wall> walls, float deltaTime) {
        ball.update(deltaTime);
        for (Wall wall : walls) { wall.update(deltaTime); }
    }

}
