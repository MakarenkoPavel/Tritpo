package com.labs.myweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
import static com.labs.myweather.R.color.colorMode;
import static com.labs.myweather.R.color.colorSity;
import static com.labs.myweather.R.color.colorWite;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {
    private static int viewHolderCount;
    private int numberItems;

    private Context parent;

    public int pos;

    public NumbersAdapter(int numberOfItems, Context parent) {
        numberItems = numberOfItems;
        viewHolderCount = 0;

        this.parent = parent;
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layuotIdForListItem = R.layout.number_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layuotIdForListItem, viewGroup, false);

        NumbersViewHolder viewHolder = new NumbersViewHolder(view);
        viewHolder.viewHolderIndex.setText("ViewHolderindex: " + viewHolderCount);

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumbersViewHolder holder, int i) {
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class NumbersViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;
        TextView viewHolderIndex;

        public NumbersViewHolder(@NonNull final View itemView) {
            super(itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);

            itemView.setOnClickListener(new View.OnClickListener() {

                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    int positionIndex = getAdapterPosition();

                    Toast toast = Toast.makeText(parent, "Элемент " + positionIndex + " выбран", LENGTH_SHORT);
                    toast.show();
                }
            });
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}
