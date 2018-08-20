package hu.bme.amorg.torpedo.render;

import android.content.Context;
import android.graphics.Canvas;

import hu.bme.amorg.torpedo.game.GameView;
import hu.bme.amorg.torpedo.game.Player;
import hu.bme.amorg.torpedo.model.Background;
import hu.bme.amorg.torpedo.model.Ship;

/**
 * Created by snake on 2017. 05. 02..
 */

public class Renderer extends Thread {

    private Context context;

    private int width;
    private int height;

    private Background background;
    private GameView view;

    private Player player;


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
        background.init(width, height);

        player = new Player(context);
        player.init(width, height);

        layoutMatrixPlayer = new int[10][10];

        render(0, 0);
    }


    public void render(int x, int y) {

        Canvas c = null;
        try {

            c = view.getHolder().lockCanvas();

            synchronized (view.getHolder()) {
                background.render(c);
                player.renderShips(c);
                shipsOpponent.render(c);
                shipsOpponent.drawX(c, x, y); //a játékos támadása
                shipsPlayer.drawX(c, x, y); //az ellenfél támadása
                sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                view.getHolder().unlockCanvasAndPost(c);

            }
        }
    }

}
