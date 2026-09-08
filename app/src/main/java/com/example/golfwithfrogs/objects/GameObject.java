package com.example.golfwithfrogs.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
    protected float x;
    protected float y;

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(float deltaTime);
    public abstract void draw(Canvas canvas, Paint paint);

    public float getX() { return x; }
    public float getY() { return y; }
}
