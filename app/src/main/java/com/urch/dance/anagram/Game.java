package com.urch.dance.anagram;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import static com.urch.dance.anagram.R.string.found;
import static com.urch.dance.anagram.R.string.letters;

public class Game extends AppCompatActivity {
    ArrayList<String> foundList;
    ArrayAdapter<String> foundAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout ll = (LinearLayout)findViewById(R.id.word_container);
        foundList = new ArrayList<String>();
        foundAdapter = new ArrayAdapter<String>(this, R.layout.list_item, foundList);
        ListView lv = (ListView)findViewById(R.id.listview);
        lv.setAdapter(foundAdapter);
        Button sub = (Button)findViewById(R.id.submit);
        sub.setOnClickListener(new submitClick());
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

    public class submitClick implements View.OnClickListener {

        public void onClick(View view) {
            int i = view.getId();

            Button submit_word = (Button) findViewById(i);
            LinearLayout fc = (LinearLayout)findViewById(R.id.found_container);
            String word = "";
            for (int j = 0; j < fc.getChildCount(); j++) {
                View v = fc.getChildAt(i);
                if (v instanceof Button) {
                    word += ((Button) v).getText().toString();
                }
            }
            //validation
            foundList.add(word);
            foundAdapter.notifyDataSetChanged();
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
