package com.labs.myweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;
import static com.labs.myweather.R.color.colorSity;
import static com.labs.myweather.R.color.colorWite;

public class MainActivity extends AppCompatActivity {
    Button bTest;
    TextView mDateTxt;

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

        bSity1 = (Button)findViewById(R.id.bSity1);
        bSity2 = (Button)findViewById(R.id.bSity2);
        bSity3 = (Button)findViewById(R.id.bSity3);

        CurrentTime currentTime = new CurrentTime();
        new Thread(currentTime).start();
    }

    public void onCliclRefresh(View view) {
        FindWeather();
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
                    switch (pos_sity) {
                        case 1:
                            bSity1.setText(str);
                            break;
                        case 2:
                            bSity2.setText(str);
                            break;
                        case 3:
                            bSity3.setText(str);
                            break;
                    }
                    break;
            }
        }
    }

    public void FindWeather() {
        final Context context = this;
        Log.d("request","startRequest");
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Minsk&appid=88d38b3803dc8b14d526103d3b9f6474";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    String temp = String.valueOf(main_object.getDouble("temp") - 273);
                    String humidity = String.valueOf(main_object.getInt("humidity"));
                    String pressure = String.valueOf(main_object.getInt("pressure"));

                    JSONObject wind_object = response.getJSONObject("wind");
                    String wind_speed = String.valueOf(wind_object.getInt("speed"));

                    Log.d("request",response.toString());

                    Log.d("request",temp + "граджусов цельсия");
                    Log.d("request",humidity + "влажность");
                    Log.d("request",wind_speed + "скорость ветра");
                    Log.d("request", pressure + "давление");

                } catch (JSONException e) {
                    Log.d("request","error" + e.toString());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, "Отсутствует подключение к интернету", LENGTH_SHORT);
                toast.show();
                Log.d("request", error.toString());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
        Log.d("request","stopRequest");
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
