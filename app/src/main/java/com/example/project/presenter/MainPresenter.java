package com.example.project.presenter;

import android.content.Context;

import androidx.lifecycle.Observer;

import com.example.project.model.Film;
import com.example.project.model.MainModel;

import java.util.ArrayList;

public class MainPresenter {
    Contract contract;
    Context context;
    String nameOfActivty;
    MainModel mainModel=new MainModel(context);
   public MainPresenter(Contract contract, Context context,String nameOfActivty) {
        this.contract = contract;
        this.context=context;
        this.nameOfActivty=nameOfActivty;
    }
//return Data from Api
    public void passDataToSportActivty()
    {
         mainModel.loadData(context,nameOfActivty).observeForever(new Observer<ArrayList<Film>>(){
             @Override
             public void onChanged(ArrayList<Film> films) {
                 contract.loadData(films);
             }
         });

    }
}
