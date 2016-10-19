package com.urch.dance.anagram;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        //Bundle extras = intent.getExtras();
        TextView correct = (TextView) findViewById(R.id.correctNo);
        TextView incorrect = (TextView) findViewById(R.id.incorrectNo);
        TextView total = (TextView) findViewById(R.id.totalNo);
        //if(extras != null){
        String i = intent.getStringExtra("correct");//}
        correct.setText(intent.getStringExtra("correct"));;//String.valueOf(extras.getInt("correct")));
        incorrect.setText(intent.getStringExtra("incorrect"));;
        total.setText(intent.getStringExtra("total"));;
    }

    public void title(View view) {
        Intent intent = new Intent(this, Title.class);
        startActivity(intent);
    }
}
