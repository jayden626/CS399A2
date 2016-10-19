package com.urch.dance.anagram;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.urch.dance.anagram.R.string.found;
import static com.urch.dance.anagram.R.string.letters;

public class Game extends AppCompatActivity {
    ArrayList<String> foundList;
    ArrayAdapter<String> foundAdapter;
    ArrayList<Button> clickedButtons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout ll = (LinearLayout)findViewById(R.id.word_container);
        foundList = new ArrayList<String>();
        clickedButtons  = new ArrayList<Button>();
        foundAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foundList);
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

    public void about(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public class letterClick implements View.OnClickListener {

        public void onClick(View view){
            int i = view.getId();
            Button b = (Button) view.findViewById(i);
            clickedButtons.add(b);
            LinearLayout wc = (LinearLayout)findViewById(R.id.word_container);
            LinearLayout fc = (LinearLayout)findViewById(R.id.found_container);
            wc.removeView(b);
            fc.addView(b);
            b.setOnClickListener(new foundClick());

        }

    }

    public class submitClick implements View.OnClickListener {

        public void onClick(View view) {
            if (clickedButtons.size() > 0) {
                int i = view.getId();

                Button submit_word = (Button) findViewById(i);
                LinearLayout fc = (LinearLayout) findViewById(R.id.found_container);
                LinearLayout wc = (LinearLayout) findViewById(R.id.word_container);
                ListView lv = (ListView) findViewById(R.id.listview);
                String word = "";

                for (Button b : clickedButtons) {
                    word += b.getText().toString();
                    fc.removeView(b);
                    wc.addView(b);
                    b.setOnClickListener(new letterClick());
                }
                clickedButtons.clear();
                /*for (int j = 0; j < fc.getChildCount(); j++) {
                    View v = fc.getChildAt(i);
                    if (v.getClass().isInstance(submit_word)) {
                        Log.i("b", "isbutton");
                        word += ((Button) v).getText().toString();
                    }
                    Log.i("b", "isbutton");
                }*/
                //validation
                //Check if word already on list
                //then check if is real word
                Log.i("word", "word: " + word);
                foundList.add(word);
                foundAdapter.notifyDataSetChanged();
                lv.setSelection(foundAdapter.getCount() - 1);
            }
        }
    }

    public class foundClick implements View.OnClickListener {

        public void onClick(View view){
            int i = view.getId();
            Button b = (Button) view.findViewById(i);
            clickedButtons.remove(b);
            LinearLayout wc = (LinearLayout)findViewById(R.id.word_container);
            LinearLayout fc = (LinearLayout)findViewById(R.id.found_container);
            fc.removeView(b);
            wc.addView(b);
            b.setOnClickListener(new letterClick());
        }

    }
}
