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


    private int height;
    private int width;
    private int startX;
    private int startY;

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


    public Ship(Context context) {
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

        ships = new ArrayList<>();
        ships.add(ship_1x2);
        ships.add(ship_2x1);
        ships.add(ship_1x3);
        ships.add(ship_3x1);
        ships.add(ship_1x4);
        ships.add(ship_4x1);

        random = new Random();

        density = context.getResources().getDisplayMetrics().density;
        if (density >= 3.0) {
            boxsize = 80;
        }


    }

    @Override
    public void step() {

    }

    @Override
    public void size(int x, int y) {
        this.height = y;
        this.width = x;
        startX = width / 2 - 400;
        startY = 840 + boxsize / 4;
    }

    @Override
    public void render(Canvas canvas) {


        if (ships != null && canvas != null) {
            int i=1;
            int k=2;
            for(BitmapDrawable object: ships) {
                i++;
                int next_num = random.nextInt(9);
                next_num = next_num % 10;
                if (next_num < 11-i) {
                    if(k%2==0) {
                        object.setBounds(startX + next_num * boxsize, startY + next_num * boxsize, (startX + next_num * boxsize) + boxsize, (startY + next_num * boxsize) + i * boxsize);
                        i--;
                    }
                    else if(k%2==1)
                        object.setBounds(startX + next_num * boxsize, startY + next_num * boxsize, (startX + next_num * boxsize) + i*boxsize, (startY + next_num * boxsize) + boxsize);
                    object.draw(canvas);
                    k++;
                }
            }
        }
    }
}
