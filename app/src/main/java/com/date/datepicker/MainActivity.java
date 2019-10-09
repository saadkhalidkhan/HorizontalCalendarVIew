package com.date.datepicker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ItemClicked {

    private RecyclerView monthRecyclerView;
    private RecyclerView datesRecyclerView;
    private MonthRecyclerViewAdapter monthRecyclerViewAdapter;
    private DateRecyclerViewAdapter dateRecylerViewAdapter;
    private ArrayList<String> monthsArrayList;
    private ArrayList<DateModel> dates;
    private DateModel dateModel;
    private int currentYear;
    private int currentMonth;
    private int currentMonth_position;
    private int currentDate;
    private String month;
    private String monthSelected;
    private SimpleDateFormat dateFormat;
    private Calendar instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInitializer();
        dates = new ArrayList<>();
        instance = Calendar.getInstance();
        currentYear = instance.get(Calendar.YEAR);
        currentMonth = instance.get(Calendar.MONTH);
        currentMonth_position = instance.get(Calendar.MONTH) + 1;
        dateFormat = new SimpleDateFormat("d");
        addMonths();
        month = convertMonthToEnglish(currentMonth);
        //monthRecyclerView.getLayoutManager().scrollToPosition(currentMonth);
        setMonthRecyclerAdapter();
        updateCalender(currentYear, currentMonth + 1);

    }

    private void setMonthRecyclerAdapter() {
        monthSelected = month;
        monthRecyclerViewAdapter = new MonthRecyclerViewAdapter(monthsArrayList, month, this);
        monthRecyclerView.setAdapter(monthRecyclerViewAdapter);
        monthRecyclerView.smoothScrollToPosition(currentMonth + 1);
    }

    private void setMonthRecyclerAdapter(String monthSelected) {
        monthRecyclerViewAdapter = new MonthRecyclerViewAdapter(monthsArrayList, monthSelected, this);
        monthRecyclerView.setAdapter(monthRecyclerViewAdapter);
        monthRecyclerView.smoothScrollToPosition(currentMonth + 1);
    }

    private void viewInitializer() {
        monthRecyclerView = findViewById(R.id.monthRecyclerView);
        monthRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        datesRecyclerView = findViewById(R.id.datesRecyclerView);
        datesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void addMonths() {
        monthsArrayList = new ArrayList<>();
        monthsArrayList.add("January");
        monthsArrayList.add("February");
        monthsArrayList.add("March");
        monthsArrayList.add("April");
        monthsArrayList.add("May");
        monthsArrayList.add("June");
        monthsArrayList.add("July");
        monthsArrayList.add("August");
        monthsArrayList.add("September");
        monthsArrayList.add("October");
        monthsArrayList.add("November");
        monthsArrayList.add("December");
    }

    private String convertMonthToEnglish(int selectedMonth) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM", Locale.getDefault());
        cal.set(Calendar.MONTH, selectedMonth);
        return month_date.format(cal.getTime());
    }

    @Override
    public void monthClicked(int month) {
        currentMonth = month;
        monthSelected = convertMonthToEnglish(month);
        setMonthRecyclerAdapter(monthSelected);
        updateCalender(currentYear, month + 1);
    }

    public void updateCalender(int year, int month) {
        dates.clear();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("d");
        SimpleDateFormat dayFormat = new SimpleDateFormat("E");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, 1);
        currentDate = instance.get(Calendar.DATE);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < daysInMonth; i++) {
            dateModel = new DateModel();
            dateModel.setDate(dateFormat.format(cal.getTime()));
            dateModel.setDay(dayFormat.format(cal.getTime()));
            dates.add(dateModel);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        populateDates();

    }

    private void populateDates() {
        dateRecylerViewAdapter = new DateRecyclerViewAdapter(this, dates, currentDate, currentMonth + 1, currentMonth_position);
        datesRecyclerView.setAdapter(dateRecylerViewAdapter);
    }
}
