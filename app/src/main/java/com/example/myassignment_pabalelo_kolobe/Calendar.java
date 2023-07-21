package com.example.myassignment_pabalelo_kolobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    private static final String TAG = "Calendar";
    private CalendarView pCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        pCalendarView = findViewById(R.id.calendarView);

        pCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 +"/" + i;
                Log.d(TAG,"onSelectedDayChange: mm/dd/yyyy," + date);



            }
        });

    }
}