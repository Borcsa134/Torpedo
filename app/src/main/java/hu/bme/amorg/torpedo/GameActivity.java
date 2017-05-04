package hu.bme.amorg.torpedo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import hu.bme.amorg.torpedo.game.GameView;

public class GameActivity extends AppCompatActivity {

	private GameView gameView;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

	DisplayMetrics metrics;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		gameView = (GameView) findViewById(R.id.gameView);
		metrics = getResources().getDisplayMetrics();
	}
}
