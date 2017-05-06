package hu.bme.amorg.torpedo.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.bme.amorg.torpedo.R;

/**
 * Created by snake on 2017. 05. 02..
 */

public class Ship implements Renderable {

    private Context context;
    private int height;
    private int width;
    private int startX;
    private int startY;
    private int id;

    private int boxsize = 0;
    private float density;
    private Random random;

    private final BitmapDrawable ship_1x2;
    private final BitmapDrawable ship_2x1;
    private final BitmapDrawable ship_1x3;
    private final BitmapDrawable ship_3x1;
    private final BitmapDrawable ship_1x4;
    private final BitmapDrawable ship_4x1;
    private List<BitmapDrawable> ships;
    private List<BitmapDrawable> shipsData;
    private List<BitmapDrawable> hitData;


    private final Bitmap bitmapXblack;
    private final Bitmap bitmapXred;

    private int layoutMatrix[][];
    private int hitMatrix[][];


    public Ship(Context context, int id) {

        this.context = context;
        Bitmap ship1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship_1x2);
        Bitmap ship2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship_2x1);
        Bitmap ship3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship_1x3);
        Bitmap ship4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship_3x1);
        Bitmap ship5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship_1x4);
        Bitmap ship6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship_4x1);

        ship_1x2 = new BitmapDrawable(ship1);
        ship_2x1 = new BitmapDrawable(ship2);
        ship_1x3 = new BitmapDrawable(ship3);
        ship_3x1 = new BitmapDrawable(ship4);
        ship_1x4 = new BitmapDrawable(ship5);
        ship_4x1 = new BitmapDrawable(ship6);

        bitmapXblack = BitmapFactory.decodeResource(context.getResources(), R.drawable.x_black);
        bitmapXred = BitmapFactory.decodeResource(context.getResources(), R.drawable.x_red);


        density = context.getResources().getDisplayMetrics().density;
        if (density >= 3.0) {
            boxsize = 80;
        }

        layoutMatrix = new int[10][10];
        hitMatrix = new int[10][10];

        init();

        this.id = id;


    }

    public void init() {

        random = new Random();


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                layoutMatrix[i][j] = 0;       //ahol mátrix 0, ott nincs hajó
                hitMatrix[i][j] = 0;          //külön mátrix a találatokra
            }
        }

        ships = new ArrayList<>();
        ships.add(ship_1x2);
        ships.add(ship_2x1);
        ships.add(ship_1x3);
        ships.add(ship_3x1);
        ships.add(ship_1x4);
        ships.add(ship_4x1);

        hitData = new ArrayList<>();


    }

    @Override
    public void step() {

    }


    @Override
    public void size(int x, int y) {
        this.height = y;
        this.width = x;
        startX = width / 2 - boxsize * 5;
        startY = 10 * boxsize + boxsize / 2 + boxsize / 4;
    }

    @Override
    public void render(Canvas canvas) {

        if (id == 2) {
            startY = boxsize / 4;
        }

        if (shipsData != null) {
            for (BitmapDrawable object : shipsData)
                object.draw(canvas);
            return;
        }

        int hor;
        int ver;
        boolean haveSpace = true;

        if (ships != null && canvas != null) {
            int i = 1;
            int k = 2;
            shipsData = new ArrayList<>();
            for (BitmapDrawable object : ships) {
                i++;
                if (k % 2 == 0) {

                    hor = random.nextInt(9 - i + 1);
                    ver = random.nextInt(9 - i + 1);

                    for (int j = ver; j < (ver + i); j++) {
                        if (layoutMatrix[j][hor] == 1) {
                            ver = random.nextInt(9 - i + 1);
                            hor = random.nextInt(9 - i + 1);
                            j = ver - 1;
                        }

                    }

                    object.setBounds(startX + hor * boxsize, startY + ver * boxsize,
                            (startX + hor * boxsize) + boxsize, (startY + ver * boxsize) + i * boxsize);
                    for (int j = ver; j < (ver + i); j++)
                        layoutMatrix[j][hor] = 1; //ahol hajó van, ott a mátrix értéke 1
                    i--;

                } else if (k % 2 == 1) {

                    hor = random.nextInt(9 - i + 1);
                    ver = random.nextInt(9 - i + 1);

                    for (int j = hor; j < (hor + i); j++) {
                        if (layoutMatrix[ver][j] == 1) {
                            ver = random.nextInt(9 - i + 1);
                            hor = random.nextInt(9 - i + 1);
                            j = hor - 1;

                        }
                    }

                    object.setBounds(startX + hor * boxsize, startY + ver * boxsize,
                            (startX + hor * boxsize) + i * boxsize, (startY + ver * boxsize) + boxsize);
                    for (int j = hor; j < (hor + i); j++)
                        layoutMatrix[ver][j] = 1; //ahol hajó van, ott a mátrix értéke 1

                }
                object.draw(canvas);
                shipsData.add(object);
                k++;
            }
        }
    }

    public int[][] getLayoutMatrix() {
        return layoutMatrix;
    }

    public void drawX(Canvas canvas, int x, int y) {

        if (x == 0 || y == 0)
            return;

        if (id == 1) {
            x = random.nextInt(800);
            y = random.nextInt(800);
        }

        x += 20;                                                      //eltolás
        y -= 20;                                                      //eltolás

        int j = ((x - x % 80) / 80) - 2;
        int i = ((y - y % 80) / 80);

        int posX = startX + j * boxsize;
        int posY = startY + i * boxsize;


        BitmapDrawable imageX;

        if (j >= 0 && j < 10 && i < 10 && layoutMatrix[i][j] == 1) {
            hitMatrix[i][j] = 1;
            imageX = new BitmapDrawable(bitmapXred);
        } else if (j > 0 && j < 10 && i < 10) {
            hitMatrix[i][j] = 1;
            imageX = new BitmapDrawable(bitmapXblack);
        } else
            imageX = new BitmapDrawable(bitmapXblack);


        imageX.setBounds(posX, posY, posX + boxsize, posY + boxsize);


        if (posX < startX || posX >= startX + 10 * boxsize || posY >= startY + 10 * boxsize) {
            //TODO
            x=0;
        } else if (y < 0) {
            //TODO
            x=0;
        } else
            hitData.add(imageX);

        for (BitmapDrawable object : hitData) {
            if (object != null && canvas != null)
                object.draw(canvas);
        }
    }

}
