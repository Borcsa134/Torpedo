package hu.bme.amorg.torpedo.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import hu.bme.amorg.torpedo.R;

import static hu.bme.amorg.torpedo.R.drawable.base;

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
        Bitmap base_image = BitmapFactory.decodeResource(context.getResources(), base);
        baseOpponent = new BitmapDrawable(base_image);
        basePlayer = new BitmapDrawable(base_image);

        if (density >= 3.0) {
            boxsize = 80;
        }

    }





    @Override
    public void step() {
        //do nothing
    }

    @Override
    public void size(int x, int y) {
        this.width=x;
        this.height=y;
        bitmapDrawable.setBounds(0, 0, width, height);

        if(density==3.0){
            baseOpponent.setBounds(width/2-400,boxsize/4,width/2+400,boxsize/4+800);
            basePlayer.setBounds(width/2-400,boxsize/4+840,width/2+400,boxsize/4+1640);
        }
        else if(density==4.0)
            baseOpponent.setBounds(width/2-500,20,width/2+500,1020);
        else
            baseOpponent.setBounds(width/2-125,20,width/2+125,270);

    }

    @Override
    public void render(Canvas canvas) {
        if(bitmapDrawable!=null && canvas!=null)
        {
            bitmapDrawable.draw(canvas);
            baseOpponent.draw(canvas);
            basePlayer.draw(canvas);
        }
    }
}
