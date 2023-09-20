package com.example.volunteers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerLandingPageAdapter extends RecyclerView.Adapter<RecyclerLandingPageAdapter.EventViewHolder> {
    private List<EventPost> eventList;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(EventPost event);

        void ngonameclick(EventPost event);
    }


    public RecyclerLandingPageAdapter() {
        this.context = context;
        this.eventList = new ArrayList<>();
    }

    public void setEventList(List<EventPost> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landingpagedesign2, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventPost event = eventList.get(position);
        holder.bind(event);

    }
// public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//     public EventViewHolder(@NonNull View itemView) {
//         super(itemView);
//     }
//
//     @Override
//     public void onClick(View view) {
//
//     }
// }
    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public  class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView ngonameTextView;
        private final TextView eventnameTextView;
        private final TextView loceventTextView;
//        private final TextView dateofeventTextView;
//        private final TextView volforeventTextView;
//        private final TextView phoneTextView;
//        private  ImageView imgview;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ngonameTextView = itemView.findViewById(R.id.ngonameposted);
            eventnameTextView = itemView.findViewById(R.id.eventnameposted);
            loceventTextView = itemView.findViewById(R.id.ngonamepostedtop);
//            dateofeventTextView = itemView.findViewById(R.id.dateofeventposted);
//            volforeventTextView = itemView.findViewById(R.id.volforeventposted);
//            phoneTextView = itemView.findViewById(R.id.phone);
//            imgview=itemView.findViewById(R.id.im);
//            imgview.setOnClickListener(this);
        }

        public void bind(EventPost event) {
//            ngonameTextView.setText(event.getNgoname());
//            eventnameTextView.setText(event.getEventname());
//            loceventTextView.setText(event.getAddress());
//            dateofeventTextView.setText(event.getDoc());
//            volforeventTextView.setText(event.getvolreq());
//            phoneTextView.setText(event.getPhone());
            String ngoname= event.getNgoname();
            String eventname= event.getEventname();
            String Address= event.getAddress();
            String Doc=event.getDoc();
            String volreq=event.getvolreq();
            String phone=event.getPhone();

            loceventTextView.setText(ngoname+" has posted a new Event Register now!!");
            ngonameTextView.setText(event.getNgoname());
            eventnameTextView.setText(eventname+" is organised on "+Doc+" near "+Address+"\n\nFor Further queries: contact:"+phone+
                    "\nNumber of volunteers req. :"+volreq);

            ngonameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            EventPost event = eventList.get(position);
                            onItemClickListener.ngonameclick(event);

                        }
                    }

                }
            });

        }


        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    EventPost event = eventList.get(position);
                    onItemClickListener.onItemClick(event);

                }
            }
        }


    }
}
