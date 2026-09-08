package com.example.golfwithfrogs.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Grass extends GameObject {
    private float width;
    private float height;

    public Grass(float x, float y, float width, float height) {
        super(x, y);

        this.width = width;
        this.height = height;
    }

    public void update(float deltaTime) {
        System.out.println("update");
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(x, y, this.width, this.height, paint);
    }

    public float getWidth() { return this.width; }
    public float getHeight() { return this.height; }
}
