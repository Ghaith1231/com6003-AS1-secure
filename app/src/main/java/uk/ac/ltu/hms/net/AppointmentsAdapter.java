package uk.ac.ltu.hms.net;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.VH> {

    private List<Appointment> data;

    public AppointmentsAdapter(List<Appointment> data) {
        this.data = data;
    }

    public void update(List<Appointment> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvLine;
        VH(View v) {
            super(v);
            tvLine = v.findViewById(android.R.id.text1);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Appointment a = data.get(position);
        String line = a.date + "  " + a.time + "  " + a.clinic;
        holder.tvLine.setText(line);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
