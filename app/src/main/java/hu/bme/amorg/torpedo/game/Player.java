package hu.bme.amorg.torpedo.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import hu.bme.amorg.torpedo.model.Ship;

public class Player {

    private Context context;

    private int width;
    private int height;
    private int startX;
    private int startY;
    private int boxsize = 0;

    private float density;

    private Ship shipsPlayer;
    private Ship shipsOpponent;


    public Player(Context context) {
        this.context = context;

        density = context.getResources().getDisplayMetrics().density;
        if (density >= 3.0) {
            boxsize = 80;
        }
        startX = width / 2 - boxsize * 5;
        startY = 3 * height / 4 - (5 * boxsize);
    }

    public void init(int width, int height) {

        this.width = width;
        this.height = height;

        shipsPlayer = new Ship(context, 1);
        shipsPlayer.init(width, height);

        shipsOpponent = new Ship(context, 2);
        shipsOpponent.init(width, height);
    }

    public void renderShips(Canvas canvas) {
        shipsPlayer.render(canvas);
    }

    public void attack(Canvas canvas, int posX, int posY) {

        if (posX > startX + 10 * boxsize || posY > startY + 10 * boxsize || posX < startX || posY < startY) {
            this.renderShips(canvas);
            return;
        }

        posX = posX - startX;
        posY = posY - startY;


    }
}
