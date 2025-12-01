package uk.ac.ltu.hms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import uk.ac.ltu.hms.data.HmsDatabase;
import uk.ac.ltu.hms.data.Patient;


public class EditEhrActivity extends AppCompatActivity {

    private HmsDatabase db;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ehr);

        db = HmsDatabase.getInstance(this);

        String nhs = getIntent().getStringExtra("nhs");
        patient = db.patientDao().findByNhs(nhs);

        if (patient == null) {
            Toast.makeText(this, "Patient not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        EditText etAllergies = findViewById(R.id.etAllergies);
        EditText etConditions = findViewById(R.id.etConditions);
        EditText etMeds = findViewById(R.id.etMeds);
        Button btnSave = findViewById(R.id.btnSaveEhr);

        etAllergies.setText(patient.allergies);
        etConditions.setText(patient.conditions);
        etMeds.setText(patient.medications);

        btnSave.setOnClickListener(v -> {
            patient.allergies = etAllergies.getText().toString().trim();
            patient.conditions = etConditions.getText().toString().trim();
            patient.medications = etMeds.getText().toString().trim();
            patient.lastUpdated = System.currentTimeMillis();

            db.patientDao().updatePatient(patient);

            Toast.makeText(this, "EHR updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
