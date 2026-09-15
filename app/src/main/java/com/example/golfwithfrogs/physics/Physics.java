package com.example.golfwithfrogs.physics;

import com.example.golfwithfrogs.objects.Ball;
import com.example.golfwithfrogs.objects.Wall;

import java.util.List;

public class Physics {
    public Physics() {
    }

    public void update(Ball ball, List<Wall> walls, float deltaTime) {
        ball.update(deltaTime);
        for (Wall wall : walls) { checkCollision(ball, wall); }
    }

    private void checkCollision(Ball ball, Wall wall) {
        float wLeft = wall.getX();
        float wRight = wall.getX() + wall.getWidth();
        float wTop = wall.getY();
        float wBottom = wall.getY() + wall.getHeight();

        float bLeft = ball.getX() - ball.getRadius();
        float bRight = ball.getX() + ball.getRadius();
        float bTop = ball.getY() - ball.getRadius();
        float bBottom = ball.getY() + ball.getRadius();

        if (bRight > wLeft && bLeft < wRight && bBottom > wTop && bTop < wBottom) {
            float overlapX1 = bRight - wLeft;
            float overlapX2 = wRight - bLeft;
            float overlapY1 = bBottom - wTop;
            float overlapY2 = wBottom - bTop;

            float minOverlapX = Math.min(overlapX1, overlapX2);
            float minOverlapY = Math.min(overlapY1, overlapY2);

            float bounceCoeff = ball.getBounceCoeff();

            if (minOverlapX < minOverlapY) {
                if (overlapX1 < overlapX2) {
                    ball.setX(wLeft - ball.getRadius());
                } else {
                    ball.setX(wRight + ball.getRadius());
                }
                ball.setVx(ball.getVx() * bounceCoeff);
            } else {
                if (overlapY1 < overlapY2) {
                    ball.setY(wTop - ball.getRadius());
                } else {
                    ball.setY(wBottom + ball.getRadius());
                }
                ball.setVy(ball.getVy() * bounceCoeff);
            }
        }
    }
}
