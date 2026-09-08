package com.example.golfwithfrogs.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ScaleGestureDetector;
import android.view.MotionEvent;

import com.example.golfwithfrogs.objects.Ball;
import com.example.golfwithfrogs.objects.Grass;
import com.example.golfwithfrogs.objects.Wall;
import com.example.golfwithfrogs.physics.Physics;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private final Paint paint;
    private float cameraScale = 1.0f;
    private final float minScale = 0.5f;
    private final float maxScale = 4.0f;


    private float cameraX = 0;
    private float cameraY = 0;

    private float lastTouchX;
    private float lastTouchY;
    private int activePointerId = 1;

    private final ScaleGestureDetector scaleDetector;

    private final Grass grass;
    private final Ball ball;
    private List<Wall> walls;
    private final Physics physics;
    private long lastFrameTime;


    public GameView(Context context) {
        super(context);

        paint = new Paint();
        paint.setAntiAlias(false);

        grass = new Grass(0, 0, 2048, 2048);
        ball = new Ball(1000, 1000, 16);
        walls = new ArrayList<>();
        physics = new Physics();

        walls.add(new Wall(200, 200, 1600, 40));
        walls.add(new Wall(200, 1760, 1600, 40));
        walls.add(new Wall(200, 1760, 40, 160));
        walls.add(new Wall(1760, 200, 40, 160));

        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);

        final int action = event.getActionMasked(); // ?

        switch (action) {
            case MotionEvent.ACTION_DOWN: { // Что за ACTION DOWN
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                activePointerId = event.getPointerId(0); // Что за аргумент
                break;
            }

            case MotionEvent.ACTION_MOVE: { // Что это за блок?
                if (scaleDetector.isInProgress()) break;

                final int pointerIndex = event.findPointerIndex(activePointerId);
                if (pointerIndex == -1) break;

                final float x = event.getX(pointerIndex);
                final float y = event.getY(pointerIndex);

                float dx = x - lastTouchX;
                float dy = y - lastTouchY;

                cameraX -= dx / cameraScale;
                cameraY -= dy / cameraScale;

                lastTouchX = x;
                lastTouchY = y;

                invalidate();
                break;
            }

            case MotionEvent.ACTION_UP:  // Почему тут так написано? Это что-то вроде или?
            case MotionEvent.ACTION_CANCEL: {
                activePointerId = -1;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIdx = event.getActionIndex(); // ?
                final int pointerId = event.getPointerId(pointerIdx);

                if (pointerId == activePointerId) {
                    final int newPointerIdx = pointerIdx == 0 ? 1 : 0;  // ?
                    lastTouchX = event.getX(newPointerIdx);
                    lastTouchY = event.getY(newPointerIdx);
                    activePointerId = event.getPointerId(newPointerIdx);
                }
                break;
            }
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) / (float)Math.pow(10, 9);
        lastFrameTime = currentTime;
        update(deltaTime);
        drawGame(canvas);
        postInvalidateOnAnimation();
    }

    private void update(float deltaTime) {
        physics.update(ball, walls, deltaTime);
    }

    private void drawGame(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.save();
        canvas.translate(-cameraX, -cameraY);
        canvas.scale(cameraScale, cameraScale, getWidth() / 2f, getHeight() / 2.0f);

        grass.draw(canvas, paint);
        for (Wall wall : walls) {
            wall.draw(canvas, paint);
        }

        ball.draw(canvas, paint);

        canvas.restore();
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            cameraScale *= detector.getScaleFactor();

            cameraScale = Math.max(minScale, Math.min(cameraScale, maxScale));

            invalidate();
            return true;
        }
    }
}
