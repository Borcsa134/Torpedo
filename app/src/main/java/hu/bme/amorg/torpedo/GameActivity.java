package hu.bme.amorg.torpedo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import hu.bme.amorg.torpedo.game.GameView;

public class GameActivity extends AppCompatActivity {

	private GameView gameViewPlayer;
	private GameView gameViewOpponent;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		gameViewPlayer = (GameView) findViewById(R.id.gameViewPlayer);
		gameViewOpponent = (GameView) findViewById(R.id.gameViewOpponent);
	}
}
