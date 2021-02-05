package com.example.project.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.adapter.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainModel {
    private static final String urlSport = "https://run.mocky.io/v3/f393cb45-4081-4e33-a45e-c2966a90f2f8";
    private static final String urlTime = "https://run.mocky.io/v3/e36cc68e-5e78-4f11-950b-fc6570bc5d03";
    private static final String urlLove="https://run.mocky.io/v3/a1f58cce-5dc9-44e7-a5a8-1daa0824250e";
    private static final String urlLife="https://run.mocky.io/v3/b5adf54a-9e8a-47eb-a055-7ba3249aae9c";
   private ArrayList<Film> array = new ArrayList<>();
   private MutableLiveData<ArrayList<Film>> listOfData = new MutableLiveData<>();
   private Context context;
   private RequestQueue requestQueue;
    public MainModel(Context context)
    {
        this.context = context;

    }
//Load Data From Api
    public LiveData<ArrayList<Film>> loadData(Context context,String nameOfActivty) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url=new String();
       if(nameOfActivty=="sport")
        {
            url=urlSport;
        }
        else if(nameOfActivty=="time")
        {
            url=urlTime;
        }
        else if(nameOfActivty=="love")
        {
            url=urlLove;
        }
        else
        {
            url=urlLife;
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    float rate = 2;
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject iteam = jsonArray.getJSONObject(i);

                        JSONObject volume = iteam.getJSONObject("volumeInfo");
                        JSONArray authors = volume.getJSONArray("authors");
                        String name = authors.getString(0);
                        String title = volume.getString("title");

                        JSONObject img = volume.getJSONObject("imageLinks");
                        String uri = img.getString("thumbnail");
                        String description =new String();
                        if(nameOfActivty=="time")
                        {
                            if(i!=1)
                            {
                                description = volume.getString("description");
                            }
                        }
                        else if(nameOfActivty=="sport"){
                            description = volume.getString("description");
                        }
                        else if(nameOfActivty=="love"){
                            if(i!=0&&i!=3)
                            {
                                description  = volume.getString("description");

                            }

                        }
                        else
                        {
                            if(i!=1) {
                                description = volume.getString("description");
                            }
                        }

                        array.add(new Film(uri, title, name, description, false, rate));
                        rate += .5;

                    }
                    listOfData.setValue(array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);


        return listOfData;
    }
}
