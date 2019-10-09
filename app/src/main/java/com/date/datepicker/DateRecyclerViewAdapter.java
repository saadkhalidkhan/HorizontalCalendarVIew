package com.date.datepicker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateRecyclerViewAdapter extends RecyclerView.Adapter<DateRecyclerViewAdapter.DateViewHolder> {

    private ArrayList<DateModel> dates;
    private int currentDate;
    private View view;
    private Context context;
    private int current_month;
    private int selected_month;

    public DateRecyclerViewAdapter(Context context, ArrayList<DateModel> dates, int currentDate, int selected_month, int current_month) {
        this.dates = dates;
        this.currentDate = currentDate;
        this.context = context;
        this.selected_month = selected_month;
        this.current_month = current_month;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_recycler_date, parent, false);
        return new DateRecyclerViewAdapter.DateViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        holder.dateTextView.setText(dates.get(position).getDate());
        holder.dayTextView.setText(dates.get(position).getDay());
        view.setBackground(null);

        if (selected_month < current_month) {
            holder.dateTextView.setTextColor(Color.GRAY);
            holder.dayTextView.setTextColor(Color.GRAY);
        } else {

            if (selected_month == current_month) {

                if (Integer.parseInt(dates.get(position).getDate()) == currentDate) {
                    view.setBackground(context.getResources().getDrawable(R.drawable.rectangle_shape));
                }

                if (Integer.parseInt(dates.get(position).getDate()) < currentDate) {
                    holder.dateTextView.setTextColor(Color.GRAY);
                    holder.dayTextView.setTextColor(Color.GRAY);
                } else {
                    holder.dateTextView.setTextColor(Color.BLACK);
                    holder.dayTextView.setTextColor(Color.BLACK);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTextView;
        private TextView dayTextView;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.dateTextView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
        }
    }
}
