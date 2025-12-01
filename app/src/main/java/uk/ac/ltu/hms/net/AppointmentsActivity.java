package uk.ac.ltu.hms.net;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ltu.hms.R;

public class AppointmentsActivity extends AppCompatActivity {

    private RecyclerView rvAppointments;
    private EditText etFilterClinic;
    private Button btnApplyFilter, btnBookAppointment;

    private final AppointmentsRepo repo = new AppointmentsRepo();
    private AppointmentsAdapter adapter;
    private final List<Appointment> allAppointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // IMPORTANT: this must match your XML file in res/layout
        // If your file is called activity_appointments.xml use:
        setContentView(R.layout.activity_appointments);

        rvAppointments = findViewById(R.id.rvAppointments);
        etFilterClinic = findViewById(R.id.etFilterClinic);
        btnApplyFilter = findViewById(R.id.btnApplyFilter);
        btnBookAppointment = findViewById(R.id.btnBookAppointment);

        adapter = new AppointmentsAdapter(new ArrayList<>());
        rvAppointments.setLayoutManager(new LinearLayoutManager(this));
        rvAppointments.setAdapter(adapter);

        loadTodaysAppointments();

        btnApplyFilter.setOnClickListener(v -> applyFilter());

        btnBookAppointment.setOnClickListener(v ->
                Toast.makeText(this, "Booking flow placeholder", Toast.LENGTH_SHORT).show()
        );
    }

    private void loadTodaysAppointments() {
        List<Appointment> result = repo.today();
        allAppointments.clear();
        allAppointments.addAll(result);
        adapter.update(allAppointments);
    }

    private void applyFilter() {
        String clinic = etFilterClinic.getText().toString().trim().toLowerCase();
        if (clinic.isEmpty()) {
            adapter.update(allAppointments);
            return;
        }

        List<Appointment> filtered = new ArrayList<>();
        for (Appointment a : allAppointments) {
            if (a == null) continue;
            String c = (a.clinic == null) ? "" : a.clinic.toLowerCase();
            if (c.contains(clinic)) {
                filtered.add(a);
            }
        }
        adapter.update(filtered);
    }
}
