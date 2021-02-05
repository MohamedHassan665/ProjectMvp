package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project.model.Film;
import com.example.project.R;
import com.example.project.adapter.Adapter;
import com.example.project.model.DataStorage;

import java.util.ArrayList;

public class Faviourt extends AppCompatActivity {

    private ImageView imageHome;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private  ArrayList<Film> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faviourt);

        //initialize views
        imageHome=findViewById(R.id.Home);
        recyclerView=findViewById(R.id.recyclerView);

        // set data in faviour page
        DataStorage DataStorage=new DataStorage(getApplicationContext());
        array= new ArrayList<>();
        array= DataStorage.getData();
        adapter=new Adapter(array,this);
        recyclerView.setAdapter(adapter);

        //override from method to send information to MoreInformation Activity
        adapter.setOnClickListener(new Adapter.onItemClickedListener() {
            @Override
            public void onIteamClicked(int postion) {
                String imgUrl=array.get(postion).getImageUrl();
                String title=array.get(postion).gettitle();
                String author=array.get(postion).getAuthor();
                String description=array.get(postion).getDescription();
                Intent intent=new Intent(Faviourt.this, MoreInformation.class);
                intent.putExtra("imgUrl",imgUrl);
                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("description",description);
                intent.putExtra("rate",array.get(postion).getRate());
                startActivity(intent);
            }
        });

        // go to Home Page
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faviourt.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}