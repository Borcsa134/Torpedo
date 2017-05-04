package hu.bme.amorg.torpedo.render;

import android.content.Context;
import android.graphics.Canvas;

import hu.bme.amorg.torpedo.game.GameView;
import hu.bme.amorg.torpedo.model.Background;
import hu.bme.amorg.torpedo.model.Ship;

/**
 * Created by snake on 2017. 05. 02..
 */

public class Renderer {
    private Context context;

    private int width;
    private int height;

    private Background background;
    private GameView view;
    private Ship ships;


    public Renderer(Context context, GameView view) {
        this.context = context;
        this.view = view;
        init(0, 0);
    }
    public void init(int width, int height) {
        this.width = width;
        this.height = height;
        background = new Background(context);
        ships = new Ship(context);
        background.size(width,height);
        ships.size(width,height);
        draw();
    }

    public void step() {
        //TODO
    }

    public void draw() {

        step();
        Canvas c = null;
        try {

            c = view.getHolder().lockCanvas();

            synchronized (view.getHolder()) {
                background.render(c);
                ships.render(c);
            }
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }
    }

}
