package kh.edu.rupp.feapp.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import kh.edu.rupp.feapp.R;
import kh.edu.rupp.feapp.model.Event;

/**
 * FEApp
 * Created by leapkh on 4/23/18.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Event[] events;

    public EventAdapter(){
        events = new Event[0];
    }

    public void setEvents(Event[] events) {
        this.events = events;
        notifyDataSetChanged();
    }

    // Create a view holder
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Load view holder UI
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_event, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    // Bind data set to view holder
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events[position];
        holder.txtEventTitle.setText(event.getTitle());
        // holder.txtEventDate.setText(event.getDate());
        Uri imageUri = Uri.parse(event.getImageUrl());
        holder.imgEvent.setImageURI(imageUri);
    }

    @Override
    public int getItemCount() {
        return events.length;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imgEvent;
        private TextView txtEventTitle;
        private TextView txtEventDate;

        public EventViewHolder(View itemView) {
            super(itemView);

            imgEvent = itemView.findViewById(R.id.img_event);
            txtEventTitle = itemView.findViewById(R.id.txt_title);
            txtEventDate = itemView.findViewById(R.id.txt_date);
        }
    }

}
