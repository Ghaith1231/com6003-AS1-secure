package uk.ac.ltu.hms;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.ltu.hms.data.HmsDatabase;
import uk.ac.ltu.hms.data.Patient;

public class VitalsActivity extends AppCompatActivity {

    private HmsDatabase db;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        db = HmsDatabase.getInstance(this);

        String nhs = getIntent().getStringExtra("nhs");
        patient = db.patientDao().findByNhs(nhs);

        if (patient == null) {
            Toast.makeText(this, "Patient not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        EditText etHr = findViewById(R.id.etHeartRate);
        EditText etTemp = findViewById(R.id.etTemp);
        EditText etGlu = findViewById(R.id.etGlucose);
        EditText etSys = findViewById(R.id.etSys);
        EditText etDia = findViewById(R.id.etDia);
        Button btnSave = findViewById(R.id.btnSaveVitals);

        btnSave.setOnClickListener(v -> {
            patient.heartRate = parseInt(etHr.getText().toString());
            patient.temperature = parseFloat(etTemp.getText().toString());
            patient.glucose = parseFloat(etGlu.getText().toString());
            patient.systolicBP = parseInt(etSys.getText().toString());
            patient.diastolicBP = parseInt(etDia.getText().toString());
            patient.lastUpdated = System.currentTimeMillis();

            db.patientDao().updatePatient(patient);

            Toast.makeText(this, "Vitals saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return 0; }
    }

    private float parseFloat(String s) {
        try { return Float.parseFloat(s.trim()); } catch (Exception e) { return 0f; }
    }
}
