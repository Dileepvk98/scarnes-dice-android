package com.dvk.scarnesdice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rules extends AppCompatActivity implements View.OnClickListener {

    Button rtg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);


        rtg = (Button) findViewById(R.id.returntogame);
        rtg.setOnClickListener(this);

    }

    public void onClick(View v) {
        Bundle data = getIntent().getExtras();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(data);
        setResult(RESULT_OK, getIntent());
        finish();

    }


}
