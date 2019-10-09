package com.date.datepicker;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonthRecyclerViewAdapter extends RecyclerView.Adapter<MonthRecyclerViewAdapter.MonthViewHolder> {

    private ArrayList<String> monthsArrayList;
    private ItemClicked itemClickedListener;
    private String currentMonth;
    private int size = 12;

    public MonthRecyclerViewAdapter(ArrayList<String> monthsArrayList, String currentMonth, ItemClicked itemClickedListener) {
        this.monthsArrayList = monthsArrayList;
        this.itemClickedListener = itemClickedListener;
        this.currentMonth = currentMonth;

    }

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycler_month, parent, false);
        return new MonthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, final int position) {

        if (monthsArrayList.get(position).equals(currentMonth)) {
            holder.monthTextView.setTextColor(Color.BLUE);
        }
        else{
            holder.monthTextView.setTextColor(Color.BLACK);
        }
        holder.monthTextView.setText(monthsArrayList.get(position));
        holder.monthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickedListener.monthClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder {

        private TextView monthTextView;

        public MonthViewHolder(@NonNull View itemView) {
            super(itemView);
            monthTextView = itemView.findViewById(R.id.monthTextView);
        }
    }
}
