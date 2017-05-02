package hu.bme.amorg.torpedo.model;

import android.graphics.Canvas;

public interface Renderable {
    void step();
    void size(int x, int y);
    void render(Canvas canvas);
}
