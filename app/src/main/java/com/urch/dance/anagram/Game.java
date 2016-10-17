package com.urch.dance.anagram;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout ll = (LinearLayout)findViewById(R.id.word_container);

        ArrayList<Button> letters = new ArrayList<Button>();
        for(int i=0; i<5; i++){
            Button b = new Button(this);
            b.setText(String.valueOf(i));
            b.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            b.setId(i);
           // b.setMaxWidth(1);
           // b.setMaxHeight(1);
           // b.setMinimumHeight(0);
           // b.setMinimumWidth(0);
            b.setOnClickListener(new letterClick());
            ll.addView(b);
        }
    }

    public class letterClick implements View.OnClickListener {

        public void onClick(View view){
            int i = view.getId();
            Button b = (Button) view.findViewById(i);
            LinearLayout wc = (LinearLayout)findViewById(R.id.word_container);
            LinearLayout fc = (LinearLayout)findViewById(R.id.found_container);
            wc.removeView(b);
            fc.addView(b);
            b.setOnClickListener(new foundClick());

        }

    }

    public class foundClick implements View.OnClickListener {

        public void onClick(View view){
            int i = view.getId();
            Button b = (Button) view.findViewById(i);
            LinearLayout wc = (LinearLayout)findViewById(R.id.word_container);
            LinearLayout fc = (LinearLayout)findViewById(R.id.found_container);
            fc.removeView(b);
            wc.addView(b);
            b.setOnClickListener(new letterClick());

        }

    }
}
