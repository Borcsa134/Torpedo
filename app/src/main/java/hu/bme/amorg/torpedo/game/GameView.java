package hu.bme.amorg.torpedo.game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import hu.bme.amorg.torpedo.render.Renderer;

/**
 * Created by Rufusz on 2017. 05. 02..
 */

public class GameView extends SurfaceView {


    private Renderer renderer;
    private Canvas canvas;

    private int width;
    private int height;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        SurfaceHolder holder = getHolder();
        renderer = new Renderer(context, this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                renderer = new Renderer(context, GameView.this);


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                renderer.init(width, height);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = e.getX();
                float y = e.getY();
                long posX = (long) x;
                long posY = (long) y;
                renderer.render((int) posX, (int) posY);
                return true;
        }
        return false;
    }
}
