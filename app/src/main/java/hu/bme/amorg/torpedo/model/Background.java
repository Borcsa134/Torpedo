package hu.bme.amorg.torpedo.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

import hu.bme.amorg.torpedo.R;

/**
 * Created by snake on 2017. 05. 02..
 */

public class Background implements Renderable {

    private int width;
    private int height;
    private final BitmapDrawable bitmapDrawable;

    public Background(Context context) {
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapDrawable = new BitmapDrawable(image);
        bitmapDrawable.setTileModeX(Shader.TileMode.MIRROR);
        bitmapDrawable.setTileModeY(Shader.TileMode.MIRROR);
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
    }

    @Override
    public void render(Canvas canvas) {
        if(bitmapDrawable!=null && canvas!=null)
        {
            bitmapDrawable.draw(canvas);
        }
    }
}
