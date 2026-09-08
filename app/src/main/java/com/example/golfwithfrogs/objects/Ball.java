package com.example.golfwithfrogs.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball extends GameObject {
    private float radius;

    public Ball(float x, float y, float radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void update(float deltaTime) {
        System.out.println("update");
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, radius, paint);
    }

    public float getRadius() { return radius; }

}
