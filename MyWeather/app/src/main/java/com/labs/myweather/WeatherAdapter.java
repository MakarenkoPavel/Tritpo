package com.labs.myweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAdapter  extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private static int viewHolderCount;
    private int numberItems;
    private ParsJson parsJson;

    private Context parent;

    public String ans = "";

    public WeatherAdapter(Context parent, ParsJson parsJson) {
        numberItems = parsJson.cnt;
        viewHolderCount = 0;
        this.parsJson = parsJson;
        this.parent = parent;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layuotIdForListItem = R.layout.weather_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layuotIdForListItem, viewGroup, false);
        WeatherAdapter.WeatherViewHolder viewHolder = new WeatherAdapter.WeatherViewHolder(view);

        viewHolder.item_day_pos.setText(ParsDayPos.ParsDayPos(parsJson.list[viewHolderCount].dt_txt));
        viewHolder.item_temp.setText(parsJson.list[viewHolderCount].main.temp);

        LoadImage loadImage = null;
        try {
            loadImage = new LoadImage(new URL("http://api.openweathermap.org/img/w/" + parsJson.list[viewHolderCount].weather[0].icon +".png"), viewHolder.item_image_view);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new Thread(loadImage).start();

        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.WeatherViewHolder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView item_day_pos;
        TextView item_temp;
        ImageView item_image_view;

        public WeatherViewHolder(@NonNull final View itemView) {
            super(itemView);
            item_day_pos = itemView.findViewById(R.id.item_day_pos);
            item_temp = itemView.findViewById(R.id.item_temp);
            item_image_view = itemView.findViewById(R.id.item_image_view);
        }

        void bind(int listIndex) {
            item_day_pos.setText(ParsDayPos.ParsDayPos(parsJson.list[listIndex].dt_txt));
            item_temp.setText(parsJson.list[listIndex].main.temp);
        }
    }
    public class LoadImage implements Runnable {
        boolean stopMode;
        URL url;
        ImageView imageView;

        LoadImage(URL url, ImageView imageView) {
            this.stopMode = false;
            this.url = url;
            this.imageView = imageView;
        }

        public void StopThread(){
            this.stopMode = true;
        }

        @Override
        public void run() {
            Bitmap icon;
            try {
                Log.d("loadBitMap", "Start load");
                InputStream in = new java.net.URL(url.toString()).openStream();
                icon = BitmapFactory.decodeStream(in);
                Log.d("loadBitMap", icon.toString());
                this.imageView.setImageBitmap(icon);
            } catch (Exception e) {
                Log.d("loadBitMap", e.toString());
                e.printStackTrace();
            }
            Log.d("loadBitMap", "finish load");
        }
    }
}
