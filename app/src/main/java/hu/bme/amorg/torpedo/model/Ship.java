package hu.bme.amorg.torpedo.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

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

    private final String FASZOM = "Első buzi";


    private final Bitmap bitmapXblack;
    private final Bitmap bitmapXred;

    private int step;
    private int stepDir;
    private int yAI;
    private int xAI;
    private int xOrigin;
    private int yOrigin;


    private int layoutMatrix[][];
    private int hitMatrix[][];


    public Ship(Context context, int id) {

        this.context = context;
        Bitmap ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);

        ship_1x2 = new BitmapDrawable(ship);
        ship_2x1 = new BitmapDrawable(ship);
        ship_1x3 = new BitmapDrawable(ship);
        ship_3x1 = new BitmapDrawable(ship);
        ship_1x4 = new BitmapDrawable(ship);
        ship_4x1 = new BitmapDrawable(ship);

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
        step = 0;
        stepDir = 0;
        xOrigin = 0;
        yOrigin = 0;
        xAI = 0;
        yAI = 0;


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
            for (BitmapDrawable object : shipsData) {
                if (id == 1)
                    object.draw(canvas);
            }
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
                    int r = random.nextInt(255);
                    int g = random.nextInt(255);
                    int b = random.nextInt(255);
                    object.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.SRC_ATOP);
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
                    int r = random.nextInt(255);
                    int g = random.nextInt(255);
                    int b = random.nextInt(255);
                    object.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.SRC_ATOP);
                    for (int j = hor; j < (hor + i); j++)
                        layoutMatrix[ver][j] = 1; //ahol hajó van, ott a mátrix értéke 1

                }
                if (id == 1)
                    object.draw(canvas);
                shipsData.add(object);
                k++;
            }
        }
    }

    public int[][] getLayoutMatrix() {
        return layoutMatrix;
    }

    public void stepAI() {
        switch (step) {
            case 0:
            case 1:
                xAI = random.nextInt(800);
                yAI = random.nextInt(800);
                step = 1;
                break;

                /*if (xAI > 3 * boxsize)
                    xAI = xAI - random.nextInt(3) * boxsize;
                else if (xAI < 7 * boxsize)
                    xAI = xAI + random.nextInt(3) * boxsize;
                if (yAI > 3 * boxsize)
                    yAI = yAI - random.nextInt(3) * boxsize;
                else if (yAI < 7 * boxsize)
                    yAI = yAI + random.nextInt(3) * boxsize;
                break;*/
            case 2:
                if (stepDir == 0)
                    xAI -= boxsize;
                else if (stepDir == 1)
                    yAI -= boxsize;
                else if (stepDir == 2)
                    xAI += boxsize;
                else if (stepDir == 3)
                    yAI += boxsize;
                else {
                    step = 0;
                    stepDir = 0;
                    xOrigin = 0;
                    yOrigin = 0;
                }

        }

    }

    public class pair {
        private int posX, posY;

        pair(int x, int y) {
            this.posX = x;
            this.posY = y;
        }

        public int getI() {
            return this.posX;
        }

        public int getJ() {
            return this.posY;
        }
    }

    private pair tempData(int x, int y) {

        x += 20;                                                      //eltolás
        y -= 20;                                                      //eltolás

        int j = ((x - x % 80) / 80) - 2;
        int i = ((y - y % 80) / 80);


        return new pair(i, j);
    }

    public void drawX(Canvas canvas, int x, int y) {

        if (x == 0 || y == 0)
            return;

        if (x > 12 * boxsize || y > 10 * boxsize || x < startX) {
            for (BitmapDrawable object : hitData) {
                if (object != null && canvas != null)
                    object.draw(canvas);
            }
            return;
        }


        if (id == 1) {
            stepAI();
            Log.d(FASZOM, "Steppelt az AI!" + step);
            x = xAI + 160;
            y = yAI;
        }

        pair temp=tempData(x,y);

        int i = temp.getI();
        int j = temp.getJ();

        int posX = startX + j * boxsize;
        int posY = startY + i * boxsize;

        if (xOrigin == 0 && yOrigin == 0) {
            xAI = j * boxsize;
            yAI = i * boxsize;
        }
        boolean isEmpty = false;
        BitmapDrawable imageX;

        if (j >= 0 && j < 10 && i < 10 && i >= 0) {
            if (hitMatrix[i][j] == 1) {
                if (step == 2 && stepDir < 4) {
                    imageX = new BitmapDrawable(bitmapXblack);
                    //stepDir++;
                    while(!isEmpty){

                        temp = tempData(xAI-20,yAI+20);
                        i = temp.getI();
                        j = temp.getJ();
                        xAI = xOrigin;
                        yAI = yOrigin;

                        if(stepDir>3) {
                            temp = tempData(random.nextInt(780), random.nextInt(780) + 40);
                            i = temp.getI();
                            j = temp.getJ();
                            stepDir=0;
                        }
                        if(j >= 0 && j < 10 && i < 10 && i >= 0 && hitMatrix[i][j]==1)
                            isEmpty=true;
                        stepDir++;
                        stepAI();
                    }

                    xAI = j*boxsize;
                    yAI = i*boxsize;
                    posX = startX + xAI;
                    posY = startY +yAI;

                    if (id == 1)
                        Log.d(FASZOM, "Első helyen bassza el a program!");
                    /*try {
                        drawX(canvas, xOrigin, yOrigin);
                    } finally {
                        return;
                    }*/

                } else {
                    if (id == 1)
                        Log.d(FASZOM, "MÁSODIK HELYEN BAAAAAAZD");
                    /*try {
                        drawX(canvas, random.nextInt(780), random.nextInt(780) + 40);
                    } finally {
                        return;
                    }*/
                    imageX = new BitmapDrawable(bitmapXblack);
                    while(j < 0 && j > 9 && i > 9 && i < 0 && hitMatrix[i][j]==0) {
                        temp = tempData(random.nextInt(780), random.nextInt(780) + 40);
                        i = temp.getI();
                        j = temp.getJ();
                    }
                    xAI = j*boxsize;
                    yAI = i*boxsize;
                    posX = startX + xAI;
                    posY = startY + yAI;
                }
            } else if (layoutMatrix[i][j] == 1 && hitMatrix[i][j] == 0) {
                if (xOrigin == 0 || yOrigin == 0) {
                    xOrigin = xAI;
                    yOrigin = yAI;
                    stepDir = 0;
                }
                imageX = new BitmapDrawable(bitmapXred);
                step = 2;

                xAI = j*boxsize;
                yAI = i*boxsize;
                posX= startX + xAI;
                posY = startY +yAI;
                //stepAI();

            } else {
                imageX = new BitmapDrawable(bitmapXblack);
                if(step==2) {
                    if (stepDir > 3)
                        stepDir++;
                    else
                        stepDir = 0;
                }
                posX=startX + xAI;
                posY = startY +yAI;
                xAI = xOrigin;
                yAI = yOrigin;
            }
            hitMatrix[i][j] = 1;
        } else {
            if (step == 2) {
                imageX = new BitmapDrawable(bitmapXblack);
                xAI = xOrigin;
                yAI = yOrigin;
                stepDir++;
                stepAI();
                posX=startX+xAI;
                posY=startY+xAI;

                if (xOrigin == 0 && yOrigin == 0) {
                    xAI = 40;
                    yAI = 40;
                }
                if (stepDir > 3)
                    step = 0;
                if (id == 1)
                    Log.d(FASZOM, "KURVÁK VÉR FOLYJON!");
                /*try {
                    drawX(canvas, xOrigin, yOrigin);
                } finally {
                    return;
                }*/

            }
            else {
                while (j < 0 && j > 9 && i > 9 && i < 0 && hitMatrix[i][j] == 0) {
                    temp = tempData(random.nextInt(780), random.nextInt(780) + 40);
                    i = temp.getI();
                    j = temp.getJ();
                }
                xAI = j * boxsize;
                yAI = i * boxsize;
                posX = startX + xAI;
                posY = startY + yAI;
                imageX = new BitmapDrawable(bitmapXblack);
                step = 0;
            /*try {
                drawX(canvas, random.nextInt(780), random.nextInt(780) + 40);
            } finally {
                return;
            }*/
            }
        }


        imageX.setBounds(posX, posY, posX + boxsize, posY + boxsize);


        /*if (posX < startX || posX >= startX + 10 * boxsize || posY >= startY + 10 * boxsize || posY < startY) {
            drawX(canvas, random.nextInt(780), random.nextInt(780) + 20);
            return;
        } else if (y < 0) {
            drawX(canvas, random.nextInt(780), random.nextInt(780) + 20);
            return;
        } else*/
        hitData.add(imageX);

        for (BitmapDrawable object : hitData) {
            if (object != null && canvas != null)
                object.draw(canvas);
        }
    }

}
