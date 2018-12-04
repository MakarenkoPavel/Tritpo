package com.labs.myweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class SearchSity extends AppCompatActivity {
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
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.bback:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.bReturn:
                if (numbersAdapter == null) {
                    Toast toast = Toast.makeText(this, "Элемент не выбран", LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (numbersAdapter.ans == "") {
                    Toast toast = Toast.makeText(this, "Элемент не выбран", LENGTH_SHORT);
                    toast.show();
                    return;
                }
                else {
                    intent.putExtra("ans",numbersAdapter.ans);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

    public void onClickSearch(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        numbersAdapter = new NumbersAdapter(6, this);
        numbersList.setAdapter(numbersAdapter);
    }
}
