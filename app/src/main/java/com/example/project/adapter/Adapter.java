package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.DataStorage;
import com.example.project.ui.Faviourt;
import com.example.project.model.Film;
import com.example.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Film> info=new ArrayList<>();
    private Context context;
    private Faviourt faviourt;
    private onItemClickedListener mListener;
    public Adapter(ArrayList<Film> info, Context context)
    {
        this.info=info;
        this.context=context;
    }
    public Adapter(Context context)
    {
        this.context=context;
    }

    public interface onItemClickedListener
    {
        void onIteamClicked(int postion);
    }
    public void setOnClickListener(onItemClickedListener listener)
    {
        mListener=listener;
    }


// Create View Holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.form,null,false);
        ViewHolder Holder=new ViewHolder(view,mListener);
        return Holder;
    }
// put Views in ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.publicher.setText(info.get(position).gettitle());
        holder.author.setText(info.get(position).getAuthor());
        Picasso.get().load(info.get(position).getImageUrl()).into(holder.image);// load image from internet
        holder.ratingBar.setRating(info.get(position).getRate());


        // check if item there exist or not
        DataStorage DataStorage=new DataStorage(context.getApplicationContext());
       if(DataStorage.checkIteam(info.get(position).getImageUrl())==false)
        {
            holder.imgFaviour.setImageResource(R.drawable.faviourt);
        }
        else
        {
            holder.imgFaviour.setImageResource(R.drawable.ic_baseline_favorite_24);
        }


    }


    // get size of array list
    @Override
    public int getItemCount() {
        return info.size();
    }


    // make View Holder
    class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView image;
        private TextView publicher;
        private   TextView author;
        private ImageView imgFaviour;
        private RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView ,onItemClickedListener listener) {
            super(itemView);


             //initialize views
            image=itemView.findViewById(R.id.image);
            publicher=itemView.findViewById(R.id.Title);
            author=itemView.findViewById(R.id.author);
            imgFaviour=itemView.findViewById(R.id.imgFaviourt);
            ratingBar=itemView.findViewById(R.id.rateTheMovie);

            // check the color of faviourt
            imgFaviour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataStorage DataStorage=new DataStorage(context.getApplicationContext());
                    int postion=getAdapterPosition();
                    if(DataStorage.checkIteam(info.get(postion).getImageUrl())==false)
                    {
                        imgFaviour.setImageResource(R.drawable.ic_baseline_favorite_24);
                        info.get(postion).setState(true);
                        boolean val=DataStorage.insert(info.get(postion).getImageUrl(),info.get(postion).gettitle(),info.get(postion).getAuthor(),1,info.get(postion).getDescription());
                    }
                    else
                    {
                        imgFaviour.setImageResource(R.drawable.faviourt);
                        DataStorage.remove(info.get(postion).getImageUrl());
                        info.get(postion).setState(false);
                    }
                }
            });


            // send postion of viewHolder
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int postion=getAdapterPosition();
                        if(postion!=RecyclerView.NO_POSITION)
                        {
                            listener.onIteamClicked(postion);
                        }
                    }

                }
            });


        }


    }
}
