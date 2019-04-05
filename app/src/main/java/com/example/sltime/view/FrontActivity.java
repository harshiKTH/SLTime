package com.example.sltime.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sltime.R;

public class FrontActivity extends AppCompatActivity {
 private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

       startButton = findViewById(R.id.lets_go);

      startButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(FrontActivity.this,MainActivity.class);
               startActivity(intent);
           }
       });
    }
}
