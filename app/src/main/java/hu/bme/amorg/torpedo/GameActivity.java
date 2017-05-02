package hu.bme.amorg.torpedo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hu.bme.amorg.torpedo.game.GameView;

public class GameActivity extends AppCompatActivity {

	private GameView gameView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		gameView = (GameView) findViewById(R.id.gameView);
	}
}
