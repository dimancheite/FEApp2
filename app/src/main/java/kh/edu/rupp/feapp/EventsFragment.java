package kh.edu.rupp.feapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.rupp.feapp.adapter.EventAdapter;
import kh.edu.rupp.feapp.model.Event;

public class EventsFragment extends Fragment {

    private RecyclerView rclEvent;
    private EventAdapter eventAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_events, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup recycler view
        rclEvent = view.findViewById(R.id.rcl_event);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rclEvent.setLayoutManager(layoutManager);

        eventAdapter = new EventAdapter();
        rclEvent.setAdapter(eventAdapter);

        // Load data from server
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = "http://10.0.2.2/test/feapp-service/events.php";
        final JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Convert json array to Event array
                Event[] events = new Event[response.length()];
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String imageUrl = jsonObject.getString("imageUrl");
                        Event event = new Event(id, title, description, "", "", imageUrl);
                        events[i] = event;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                eventAdapter.setEvents(events);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                Log.d("rupp", "Loading events error. " + error.getMessage());
            }
        });
        requestQueue.add(request);
    }
}
