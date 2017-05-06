package hu.bme.amorg.torpedo.render;

import android.content.Context;
import android.graphics.Canvas;

import hu.bme.amorg.torpedo.game.GameView;
import hu.bme.amorg.torpedo.model.Background;
import hu.bme.amorg.torpedo.model.Ship;

/**
 * Created by snake on 2017. 05. 02..
 */

public class Renderer extends Thread{
    private Context context;

    private int width;
    private int height;

    private Background background;
    private GameView view;
    private Ship shipsPlayer;
    private Ship shipsOpponent;

    private Canvas canvas=null;
    private boolean running=false;

    private int[][] layoutMatrixPlayer;

    public Renderer(Context context, GameView view) {
        this.context = context;
        this.view = view;
        init(0, 0);

    }

    public void init(int width, int height) {
        this.width = width;
        this.height = height;

        background = new Background(context);
        background.size(width, height);

        shipsPlayer = new Ship(context, 1);
        shipsPlayer.size(width, height);

        shipsOpponent = new Ship(context, 2);
        shipsOpponent.size(width, height);

        layoutMatrixPlayer = new int[10][10];

        render(0,0);
    }

    public void step() {
        //TODO
    }

    public void render(int x, int y) {

        step();
        Canvas c = null;
        try {

            c = view.getHolder().lockCanvas();

            synchronized (view.getHolder()) {
                background.render(c);
                shipsPlayer.render(c);
                shipsOpponent.render(c);
                shipsOpponent.drawX(c, x, y);
                shipsPlayer.drawX(c, x, y);
            }
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);

            }
        }
    }

}
