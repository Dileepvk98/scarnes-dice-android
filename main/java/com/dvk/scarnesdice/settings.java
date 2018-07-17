package com.dvk.scarnesdice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
public class settings extends AppCompatActivity implements View.OnClickListener {
    Button rtg2;
    RadioGroup rg;
    RadioButton rb;
    int targetscore,rid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        rg = (RadioGroup) findViewById(R.id.radiogrp);
        rtg2 = (Button) findViewById(R.id.returntogame2);
        rb.setOnClickListener(this);
    }

    public void onClick(View v) {
            rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            String s = rb.getText().toString();
            targetscore = Integer.parseInt(s);

            Bundle data = getIntent().getExtras();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(data);
            intent.putExtra("ts", targetscore);
            setResult(RESULT_OK, getIntent());
            finish();
        }
}
