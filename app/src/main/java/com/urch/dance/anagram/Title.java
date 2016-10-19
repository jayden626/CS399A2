package com.urch.dance.anagram;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Title extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_title);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}
