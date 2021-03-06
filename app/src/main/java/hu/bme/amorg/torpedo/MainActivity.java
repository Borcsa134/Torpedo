package hu.bme.amorg.torpedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NEW GAME
        Button newgame_button = (Button) findViewById(R.id.btn_newgame);
        newgame_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newGame = new Intent(MainActivity.this,SelectGameActivity.class);
                startActivity(newGame);
            }
        });

        //EXIT
        Button exit_button = (Button) findViewById(R.id.btn_exit);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
