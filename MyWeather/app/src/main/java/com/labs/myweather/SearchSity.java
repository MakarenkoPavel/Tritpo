package com.labs.myweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SearchSity extends AppCompatActivity {
    public Button bTest;
    private RecyclerView numbersList;
    private NumbersAdapter numbersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sity);

        numbersList = findViewById(R.id.container);
    }
    @Override
    protected void onStop() {
        super.onStop();

        numbersList.destroyDrawingCache();
    }

    public void onClick(View view) {

        //Intent intent = new Intent();
        //intent.putExtra("ans","helloWorld");
        //setResult(RESULT_OK, intent);
        //finish();
    }

    public void onClickSearch(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        numbersAdapter = new NumbersAdapter(20, this);
        numbersList.setAdapter(numbersAdapter);
    }
}
