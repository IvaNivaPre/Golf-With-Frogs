package com.example.golfwithfrogs.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball extends GameObject {
    private float radius;
    private float vx = 0, vy = 0;
    private float friction = 0.98f;

    private float bounceCoeff = -0.75f;


    public Ball(float x, float y, float radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void update(float deltaTime) {
        x += vx * deltaTime;
        y += vy * deltaTime;

        vx *= Math.pow(friction, deltaTime * 60);
        vy *= Math.pow(friction, deltaTime * 60);

        if (Math.abs(vx) < 5) vx = 0;
        if (Math.abs(vy) < 5) vy = 0;
    }

    public void strike(float speedX, float speedY) {
        this.vx = speedX;
        this.vy = speedY;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, radius, paint);
    }

    public float getRadius() { return radius; }
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public float getVx() { return vx; }
    public void setVx(float vx) { this.vx = vx; }
    public float getVy() { return vy; }
    public void setVy(float vy) { this.vy = vy; }
    public float getBounceCoeff() { return bounceCoeff; }
    public void setBounceCoeff(float bounceCoeff) { this.bounceCoeff = bounceCoeff; }

}
