package hu.bme.amorg.torpedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class SelectGameActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgame);

        Button btn_vsai = (Button) findViewById(R.id.btn_vsai);
        btn_vsai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectGameActivity.this,GameActivity.class);
                startActivity(i);
            }
        });
    }
}
