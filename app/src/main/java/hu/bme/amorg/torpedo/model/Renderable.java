package hu.bme.amorg.torpedo.model;

import android.graphics.Canvas;

public interface Renderable {
    void init(int x, int y);
    void render(Canvas canvas);
}
