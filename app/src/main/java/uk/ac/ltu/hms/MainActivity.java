package uk.ac.ltu.hms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private Button btnOpenEhr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // bind the button from XML
        btnOpenEhr = findViewById(R.id.btnOpenEhr);

        btnOpenEhr.setOnClickListener(v -> {
            final EditText input = new EditText(this);
            input.setHint("Enter NHS number");

            new AlertDialog.Builder(this)
                    .setTitle("Open Patient EHR")
                    .setView(input)
                    .setPositiveButton("Open", (d, w) -> {
                        String nhs = input.getText().toString().trim();

                        if (nhs.isEmpty()) {
                            Toast.makeText(this, "NHS number required", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Intent i = new Intent(MainActivity.this, EhrSummaryActivity.class);

                        i.putExtra("nhs", nhs);
                        startActivity(i);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
