package uk.ac.ltu.hms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.ltu.hms.data.HmsDatabase;
import uk.ac.ltu.hms.data.Patient;

public class EhrSummaryActivity extends AppCompatActivity {

    private HmsDatabase db;
    private Patient patient;
    private String nhs;

    private TextView tvName, tvNhs, tvAllergies, tvConditions, tvMeds, tvVitals;
    private Button btnEditEhr, btnAddVitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ehr_summary);

        // Database instance
        db = HmsDatabase.getInstance(this);

        // Get NHS passed from MainActivity
        nhs = getIntent().getStringExtra("nhs");

        if (nhs == null || nhs.trim().isEmpty()) {
            Toast.makeText(this, "No NHS number provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvName = findViewById(R.id.tvName);
        tvNhs = findViewById(R.id.tvNhs);
        tvAllergies = findViewById(R.id.tvAllergies);
        tvConditions = findViewById(R.id.tvConditions);
        tvMeds = findViewById(R.id.tvMeds);
        tvVitals = findViewById(R.id.tvVitals);

        btnEditEhr = findViewById(R.id.btnEditEhr);
        btnAddVitals = findViewById(R.id.btnAddVitals);

        patient = db.patientDao().findByNhs(nhs);

        if (patient == null) {
            Toast.makeText(this, "No patient found for NHS: " + nhs, Toast.LENGTH_LONG).show();
            finish();
            return;
        }


        renderPatient(patient);


        btnEditEhr.setOnClickListener(v -> {
            Intent i = new Intent(this, EditEhrActivity.class);
            i.putExtra("nhs", nhs);
            startActivity(i);
        });

        btnAddVitals.setOnClickListener(v -> {
            Intent i = new Intent(this, VitalsActivity.class);
            i.putExtra("nhs", nhs);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload in case Edit/Vitals changed data
        patient = db.patientDao().findByNhs(nhs);
        if (patient != null) renderPatient(patient);
    }

    private void renderPatient(Patient p) {
        tvName.setText(p.fullName);
        tvNhs.setText("NHS: " + p.nhsNumber);
        tvAllergies.setText(p.allergies);
        tvConditions.setText(p.conditions);
        tvMeds.setText(p.medications);

        String vitalsText =
                "HR: " + p.heartRate +
                        "  Temp: " + p.temperature +
                        "  Glucose: " + p.glucose +
                        "  BP: " + p.systolicBP + "/" + p.diastolicBP;

        tvVitals.setText(vitalsText);
    }


}
