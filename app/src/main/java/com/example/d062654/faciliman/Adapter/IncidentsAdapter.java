package com.example.d062654.faciliman.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d062654.faciliman.R;
import com.example.d062654.faciliman.Requests.IncidentColl;
import com.example.d062654.faciliman.Requests.IncidentRequest;

import java.util.Collection;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by D062654 on 19.12.2016.
 */

public class IncidentsAdapter extends RecyclerView.Adapter<IncidentsAdapter.IncidentsViewHolder> {

private List<IncidentRequest> incidents;
private int rowLayout;
private Context context;

    public IncidentsAdapter() {
    }

    public static class IncidentsViewHolder extends RecyclerView.ViewHolder{
    LinearLayout incidentLayout;
    TextView facTitle;
    TextView facLocation;
    TextView incidentDescription;
    TextView rating;


    public IncidentsViewHolder(View v) {
        super(v);
        incidentLayout = (LinearLayout) v.findViewById(R.id.incident_layout);
        facTitle = (TextView) v.findViewById(R.id.factitle);
        facLocation = (TextView) v.findViewById(R.id.faclocation);
        incidentDescription = (TextView) v.findViewById(R.id.description);
        rating = (TextView) v.findViewById(R.id.rating);
    }

}

    public IncidentsAdapter(int rowLayout, Context context) {

        //this.incidents = incidents;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public IncidentsAdapter(List<IncidentRequest> incidents, int rowLayout, Context context) {
        this.incidents = incidents;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public IncidentsAdapter.IncidentsViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new IncidentsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(IncidentsViewHolder holder, final int position) {
        holder.facTitle.setText(incidents.get(position).getTitle());
        holder.facLocation.setText(incidents.get(position).getLocation());
        holder.incidentDescription.setText(incidents.get(position).getDescription());
        holder.rating.setText((String.valueOf(incidents.get(position).isActive())));
    }

    @Override
    public int getItemCount() {
        return incidents.size();
    }

    public void onDataChanged(List<IncidentRequest> incidents){
        this.incidents = incidents;
        this.notifyDataSetChanged();
    }

    public List<IncidentRequest> getIncidents() {
        return incidents;
    }
}