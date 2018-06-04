package kh.edu.rupp.feapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import kh.edu.rupp.feapp.adapter.EventAdapter;
import kh.edu.rupp.feapp.model.Event;

/**
 * FEApp
 * Created by leapkh on 4/23/18.
 */
public class EventsActivity extends AppCompatActivity {

    private RecyclerView rclEvent;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);
        rclEvent = findViewById(R.id.rcl_event);

        // Create layout manager object
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rclEvent.setLayoutManager(layoutManager);

        // Create adapter object
        eventAdapter = new EventAdapter();

        // Create temporary data set
        Event[] events = new Event[3];
        //events[0] = new Event(1, "3rd Engineering Day", "...", "CKCC", "123");
        //events[1] = new Event(2, "Event 2", "...", "CKCC", "123");
        //events[2] = new Event(3, "Event 3", "...", "CKCC", "123");
        eventAdapter.setEvents(events);

        rclEvent.setAdapter(eventAdapter);
    }
}















