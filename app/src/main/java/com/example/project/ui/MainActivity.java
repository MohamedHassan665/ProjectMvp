package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class MainActivity extends AppCompatActivity {
private Button love,time,sport,live;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize views
        love=(Button) findViewById(R.id.Love);
        time=(Button) findViewById(R.id.Time);
        sport=(Button) findViewById(R.id.Sport);
        live=(Button) findViewById(R.id.Live);


        // make action after click on button
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Love.class);
                startActivity(intent);


            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Time.class);
                startActivity(intent);
            }
        });


        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Sport.class);
                startActivity(intent);
            }
        });


        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Life.class);
                startActivity(intent);
            }
        });


    }
}