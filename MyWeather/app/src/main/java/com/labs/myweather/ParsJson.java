package com.labs.myweather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ParsJson {
    public int cod;
    public int cnt;
    public Sity sity;
    public List list[];

    ParsJson(JSONObject response) {
        Log.d("TestPars", "parsFirst: " + response.toString());
        try {
            this.cod = response.getInt("cod");
            this.cnt = response.getInt("cnt");
            this.sity = new Sity(response.getJSONObject("city"));
            JSONArray array = response.getJSONArray("list");
            this.list = new List[array.length()];
            for(int i = 0; i < array.length(); i++) {
                list[i] = new List(array.getJSONObject(i));
            }

        } catch (Exception e) {
            Log.d("ErrorLog",e.toString());
            e.printStackTrace();
        }
    }

    public class Sity {
        public int id;
        public String name;
        public Coord coord;
        public String country;

        Sity(JSONObject response) {
            Log.d("TestPars", "parsSity " + response.toString());
            try {
                this.id = response.getInt("id");
                this.name = response.getString("name");
                this.coord = new Coord(response.getJSONObject("coord"));
                this.country = response.getString("country");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class Coord {
        public double lat;
        public double lon;
        Coord(int lat, int lon) {
            this.lat = lat;
            this.lon = lon;
        }
        Coord(JSONObject response) {
            Log.d("TestPars", "parsCoord " + response.toString());
            try {
                this.lat = response.getInt("lat");
                this.lon = response.getInt("lon");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class List {
        public int dt;
        public Main main;
        public Weather weather[];
        public Wind wind;
        public String dt_txt;
        List(JSONObject response) {
            Log.d("TestPars", "parsList " + response.toString());
            try {
                this.dt = response.getInt("dt");
                this.dt_txt = response.getString("dt_txt");
                this.main = new Main(response.getJSONObject("main"));
                JSONArray array = response.getJSONArray("weather");
                this.weather = new Weather[array.length()];
                for(int i = 0; i < array.length(); i++) {
                    weather[i] = new Weather(array.getJSONObject(i));
                }
                this.wind = new Wind(response.getJSONObject("wind"));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class Main {
        public int temp;
        public int pressure;
        public int humidity;
        Main(JSONObject response) {
            Log.d("TestPars", "parsMain " + response.toString());
            try {
                this.temp = response.getInt("temp") - 273;
                this.pressure = response.getInt("pressure");
                this.humidity = response.getInt("humidity");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
        Weather(JSONObject response) {
            Log.d("TestPars", "parsWeather " + response.toString());
            try {
                this.id = response.getInt("id");
                this.main = response.getString("main");
                this.description = response.getString("description");
                this.icon = response.getString("icon");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public class Wind {
        public double speed;
        public double deg;
        Wind(JSONObject response) {
            Log.d("TestPars", "parsWind " + response.toString());
            try {
                this.deg = response.getDouble("deg");
                this.speed = response.getDouble("speed");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
