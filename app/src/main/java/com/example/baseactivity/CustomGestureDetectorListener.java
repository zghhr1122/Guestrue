package com.example.baseactivity;

import android.view.GestureDetector;
import android.view.MotionEvent;

public abstract class CustomGestureDetectorListener implements GestureDetector.OnGestureListener {
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public abstract boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
}
