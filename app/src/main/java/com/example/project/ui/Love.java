package com.example.project.ui;
// love Activity to show books of love
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.R;
import com.example.project.adapter.Adapter;
import com.example.project.model.Film;
import com.example.project.presenter.Contract;
import com.example.project.presenter.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Love extends AppCompatActivity implements View.OnClickListener , Contract {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Film> array;
    private ImageView imageFav,imageHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        //initialize views
        imageHome=findViewById(R.id.Home);
        recyclerView=findViewById(R.id.recyclerView);
        imageFav=findViewById(R.id.fav);
        imageFav.setImageResource(R.drawable.faviourt);
        imageFav.setOnClickListener(this);



        // go to Home Page
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Love.this,MainActivity.class);
                startActivity(intent);
            }
        });


       array= new ArrayList<>();
        MainPresenter mainPresenter=new MainPresenter(this::loadData,this,"love");
        mainPresenter.passDataToSportActivty();// pass Data From Api to this Activity
        adapter=new Adapter(array,this);// send Information to adapter
        recyclerView.setAdapter(adapter);//put iteam in recyclerView


        //override from method to send information to MoreInformation Activity
        adapter.setOnClickListener(new Adapter.onItemClickedListener() {
            @Override
            public void onIteamClicked(int postion) {
                String imgUrl=array.get(postion).getImageUrl();
                String title=array.get(postion).gettitle();
                String author=array.get(postion).getAuthor();
                String description=array.get(postion).getDescription();
                Intent intent=new Intent(Love.this, MoreInformation.class);
                ContentValues contentValues=new ContentValues();
                intent.putExtra("imgUrl",imgUrl);
                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("description",description);
                intent.putExtra("rate",array.get(postion).getRate());
                startActivity(intent);
            }
        });

    }


// go to favorite page
    @Override
    public void onClick(View v)
    {
        Intent intent=new Intent(Love.this, Faviourt.class);
        imageFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        startActivity(intent);
    }


// get data from Api
    @Override
    public void loadData(ArrayList<Film> information)
    {
        array.addAll(information);
        adapter.notifyDataSetChanged();
    }
}