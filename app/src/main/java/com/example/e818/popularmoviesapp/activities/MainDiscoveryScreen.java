package com.example.e818.popularmoviesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.e818.popularmoviesapp.R;
import com.example.e818.popularmoviesapp.models.MyRecyclerViewAdapter;

public class MainDiscoveryScreen extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    //Todo criar os 2 outros layouts e arranjar este
    //TODO Criar Fake Data para prespectiva

    //Criterios e guidelines
//    http://udacity.github.io/android-nanodegree-guidelines/core.html
//    https://review.udacity.com/#!/rubrics/66/view
//    https://review.udacity.com/#!/rubrics/67/view

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_discovery_screen);

        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
//        adapter = new MyRecyclerViewAdapter(this, data);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
    }
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_discovery_screen);
    }*/
}
