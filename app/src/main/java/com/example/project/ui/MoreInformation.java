package com.example.project.ui;
// show moreInformation about book
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.project.R;
import com.squareup.picasso.Picasso;

public class MoreInformation extends AppCompatActivity {

    private ImageView image;
    private TextView publicher;
    private TextView author;
    private TextView description;
    private ImageView imgFaviour;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);


        //initialize views
        Intent intent=getIntent();
        image=findViewById(R.id.image);
        description=findViewById(R.id.description);
        ratingBar=findViewById(R.id.rateTheMovie);
        publicher=findViewById(R.id.Title);
        author=findViewById(R.id.author);
        imgFaviour=findViewById(R.id.imgFaviourt);


        //set data in views
        publicher.setText(intent.getStringExtra("title"));
        author.setText(intent.getStringExtra("author"));
        description.setText(intent.getStringExtra("description"));
        ratingBar.setRating(intent.getFloatExtra("rate",0));
        Picasso.get().load(intent.getStringExtra("imgUrl")).into(image);

    }
}