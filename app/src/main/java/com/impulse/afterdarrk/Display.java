package com.impulse.afterdarrk;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.impulse.afterdarrk.Enemy.Enemy;
import com.impulse.afterdarrk.Utils.CartesianCoords;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Display extends View implements View.OnTouchListener{

    private List<DisplayObj> objs;

    public Display(Context context) {
        super(context);
        objs = new ArrayList<>();
        setOnTouchListener(this);
    }

    public void addObj(DisplayObj obj) {
        objs.add(obj);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (DisplayObj obj : objs) {
            obj.draw(canvas);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        for (Iterator<DisplayObj> iterator = objs.iterator(); iterator.hasNext();) {
            DisplayObj obj = iterator.next();

            if (obj.isHit(new CartesianCoords(event.getX(), event.getY()))) {
                obj.touch(v, event);
                return true;
            }
        }
        return false;
    }

    public void remove(Enemy enemy) {
        objs.remove(enemy);
    }
}