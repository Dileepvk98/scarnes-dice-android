package com.dvk.scarnesdice;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int p1_total = 0, p2_total = 0, turn = 0;
    //turn =0 for player1 , 1 for comp/player2
    int dicevalue, score, win_score = 30;
    Button bsave, breset;
    ImageButton imgbtn;
    TextView p1score, p2score, turn_win_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent i=getIntent();
        String s=i.getStringExtra("ts");
        win_score=Integer.parseInt(s);*/

        imgbtn = (ImageButton) findViewById(R.id.dicepic);
        bsave = (Button) findViewById(R.id.savebtn);
        breset = (Button) findViewById(R.id.resetbtn);
        p1score = (TextView) findViewById(R.id.p1score);
        p2score = (TextView) findViewById(R.id.p2score);
        turn_win_text = (TextView) findViewById(R.id.turntext);
        imgbtn.setOnClickListener(this);
        bsave.setOnClickListener(this);
        breset.setOnClickListener(this);

    }
    public void roll() {
        Random random = new Random();
        dicevalue = random.nextInt(6) + 1;
        if (dicevalue == 1) {
            score = 0;
            savescore();
            update();
        } else {
            score += dicevalue;
            update();
        }
    }
    public void save() {
        savescore();
        update();
        if (p2_total >= win_score || p1_total >= win_score)
            gameover();
    }
    public void reset() {
        score = p1_total = p2_total = turn = 0;
        p1score.setText("P1 Score : " + Integer.toString(p1_total));
        p2score.setText("P2 Score : " + Integer.toString(p2_total));
        imgbtn.setImageResource(R.drawable.dice1);
        turn_win_text.setText("Player 1 get's the first turn");
        imgbtn.setEnabled(true);
        bsave.setEnabled(true);
    }
    public void savescore() {

        if (turn == 0) {
            p1_total += score;
            turn = 1;
            score = 0;
        } else {
            p2_total += score;
            turn = 0;
            score = 0;
        }
    }
    public void update() {
        if (turn == 0) {
            turn_win_text.setText("Player 1's turn");
        } else {
            turn_win_text.setText("Player 2's turn");
        }
        p1score.setText("P1 Score : " + Integer.toString(p1_total));
        p2score.setText("P2 Score : " + Integer.toString(p2_total));
        switch (dicevalue) {
            case 1:
                imgbtn.setImageResource(R.drawable.dice1);  break;
            case 2:
                imgbtn.setImageResource(R.drawable.dice2);  break;
            case 3:
                imgbtn.setImageResource(R.drawable.dice3);  break;
            case 4:
                imgbtn.setImageResource(R.drawable.dice4);  break;
            case 5:
                imgbtn.setImageResource(R.drawable.dice5);  break;
            case 6:
                imgbtn.setImageResource(R.drawable.dice6);  break;
        }
    }
    public void gameover() {

        if (p1_total >= win_score) {
            turn_win_text.setText("PLAYER 1 WINS !");
            turn_win_text.setTextColor(Color.parseColor("#800000"));

        } else if (p2_total >= win_score) {
            turn_win_text.setText("PLAYER 2 WINS !");
            turn_win_text.setTextColor(Color.parseColor("#800000"));

        }
        imgbtn.setEnabled(false);
        bsave.setEnabled(false);
    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dicepic:
                roll();
                break;
            case R.id.savebtn:
                save();
                break;
            case R.id.resetbtn:
                reset();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    //option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rules_screen:
               /* Bundle data = new Bundle();
                Intent intent = new Intent(this, rules.class);
                intent.putExtras(data);
                startActivityForResult(intent, 1);*/
                startActivity(new Intent(YourCurrentActivity.this, YourNewActivity.class));
                return true;
            case R.id.settings_screen:
                Bundle data2 = new Bundle();
                Intent intent2 = new Intent(this, settings.class);
                intent2.putExtras(data2);
                startActivityForResult(intent2, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
