package hu.bme.amorg.torpedo.render;

import android.content.Context;
import android.graphics.Canvas;

import hu.bme.amorg.torpedo.game.GameView;
import hu.bme.amorg.torpedo.model.Background;

/**
 * Created by snake on 2017. 05. 02..
 */

public class Renderer {
    private Context context;

    private int width;
    private int height;

    private Background background;
    private GameView view;


    public Renderer(Context context) {
        this.context = context;
        init(0, 0);
    }
    public void init(int width, int height) {
        this.width = width;
        this.height = height;
        background = new Background(context);
        background.size(width,height);
    }

    public void step() {
        //TODO
    }

    public void draw(Canvas canvas) {
        background.render(canvas);
        step();
        Canvas c = null;
        try {
            c = view.getHolder().lockCanvas();
            synchronized (view.getHolder()) {
                //renderer.draw(c);
            }
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }
    }

}
