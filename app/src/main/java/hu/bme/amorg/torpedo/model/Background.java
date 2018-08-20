package hu.bme.amorg.torpedo.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import hu.bme.amorg.torpedo.R;


/**
 * Created by snake on 2017. 05. 02..
 */

public class Background implements Renderable {

    private int width;
    private int height;
    private int boxsize;

    private final BitmapDrawable bitmapDrawable;
    private final BitmapDrawable baseOpponent;
    private final BitmapDrawable basePlayer;

    private DisplayMetrics metrics;
    private float density;

    public Background(Context context) {

        Bitmap background_image = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapDrawable = new BitmapDrawable(background_image);
        bitmapDrawable.setTileModeX(Shader.TileMode.MIRROR);
        bitmapDrawable.setTileModeY(Shader.TileMode.MIRROR);

        density = context.getResources().getDisplayMetrics().density;
        Bitmap base_image = BitmapFactory.decodeResource(context.getResources(), R.drawable.base);
        baseOpponent = new BitmapDrawable(base_image);
        basePlayer = new BitmapDrawable(base_image);

        if (density >= 3.0) {
            boxsize = 80;
        }

    }


    @Override
    public void init(int x, int y) {
        this.width = x;
        this.height = y;
        bitmapDrawable.setBounds(0, 0, width, height);
        baseOpponent.setBounds(width / 2 - (5 * boxsize), height / 4 - (5 * boxsize), width / 2 + (5 * boxsize), height / 4 + (5 * boxsize));
        basePlayer.setBounds(width / 2 - (5 * boxsize), 3 * height / 4 - (5 * boxsize), width / 2 + (5 * boxsize), 3 * height / 4 + (5 * boxsize));

    }

    @Override
    public void render(Canvas canvas) {
        if (bitmapDrawable != null && canvas != null) {
            bitmapDrawable.draw(canvas);
            baseOpponent.draw(canvas);
            basePlayer.draw(canvas);
        }
    }
}
