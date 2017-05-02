package hu.bme.amorg.torpedo.game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import hu.bme.amorg.torpedo.render.Renderer;

/**
 * Created by snake on 2017. 05. 02..
 */

public class GameView extends SurfaceView {


    private Renderer renderer;
    private Canvas canvas;

    private int width;
    private int height;

    public GameView(Context context) {
        super(context);
        init(context);;
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);;
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        init(context);
    }

    private void init(final Context context){
        SurfaceHolder holder = getHolder();
        renderer = new Renderer(context);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                renderer = new Renderer(context);
                renderer.draw(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                /*boolean retry = true;
                renderLoop.setRunning(false);
                while (retry) {
                    try {
                        renderLoop.join();

                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,int width, int height) {
                renderer.init(width,height);
            }
        });

    }
}