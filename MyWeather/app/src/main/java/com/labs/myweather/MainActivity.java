package com.labs.myweather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import static com.labs.myweather.R.color.colorDefoltSity;
import static com.labs.myweather.R.color.colorSity;
import static com.labs.myweather.R.color.colorWite;
import static com.labs.myweather.R.id.bSity1;

public class MainActivity extends AppCompatActivity {
    Button bTest;
    TextView mDateTxt;
    TextView mTextMessage1;
    TextView mTextMessage2;

    int pos_sity = 1;
    Button bSity1;
    Button bSity2;
    Button bSity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bTest = findViewById(R.id.button);
        mDateTxt = (TextView)findViewById(R.id.DateTxt);
        mTextMessage2 = (TextView)findViewById(R.id.message2);
        mTextMessage1 = (TextView)findViewById(R.id.message);

        bSity1 = (Button)findViewById(R.id.bSity1);
        bSity2 = (Button)findViewById(R.id.bSity2);
        bSity3 = (Button)findViewById(R.id.bSity3);

        CurrentTime currentTime = new CurrentTime();
        new Thread(currentTime).start();
    }

    public void onCliclRefresh(View view) {

    }

    @SuppressLint("ResourceAsColor")
    public void onClickbSity(View view) {
        switch (view.getId()) {
            case R.id.bSity1:
                if(pos_sity != 1) {
                    pos_sity = 1;
                    bSity1.setBackgroundColor(getResources().getColor(colorSity));
                    bSity2.setBackgroundColor(getResources().getColor(colorWite));
                    bSity3.setBackgroundColor(getResources().getColor(colorWite));
                }
                else {
                    Intent intent = new Intent(this, SearchSity.class);
                    startActivityForResult(intent, 1);
                }
                break;

            case R.id.bSity2:
                if(pos_sity != 2) {
                    pos_sity = 2;
                    bSity1.setBackgroundColor(getResources().getColor(colorWite));
                    bSity2.setBackgroundColor(getResources().getColor(colorSity));
                    bSity3.setBackgroundColor(getResources().getColor(colorWite));
                }
                else {
                    Intent intent = new Intent(this, SearchSity.class);
                    startActivityForResult(intent, 1);
                }
                break;

            case R.id.bSity3:
                if(pos_sity != 3) {
                    pos_sity = 3;
                    bSity1.setBackgroundColor(getResources().getColor(colorWite));
                    bSity2.setBackgroundColor(getResources().getColor(colorWite));
                    bSity3.setBackgroundColor(getResources().getColor(colorSity));
                }
                else {
                    Intent intent = new Intent(this, SearchSity.class);
                    startActivityForResult(intent, 1);
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    String str = data.getStringExtra("ans");
                    mTextMessage2.setText(str);
                    break;
            }
        }
    }

    class CurrentTime implements Runnable {
        SimpleDateFormat format;
        Calendar calendar;
        boolean stopMode;

        CurrentTime() {
            this.format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.calendar = Calendar.getInstance();
            this.stopMode = false;
        }

        public void StopThread(){
            this.stopMode = true;
        }

        @Override
        public void run() {
            while(true)
            {
                if (stopMode)
                    return;

                calendar = Calendar.getInstance();
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        mDateTxt.setText("Сегодня: " + format.format(calendar.getTime()));
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
