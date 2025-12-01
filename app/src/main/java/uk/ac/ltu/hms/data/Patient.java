package uk.ac.ltu.hms.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients", indices = @Index(value = "nhsNumber", unique = true))
public class Patient {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public String nhsNumber = "";

    public String fullName;

    public String allergies = "";
    public String conditions = "";
    public String medications = "";

    public int heartRate = 0;
    public float temperature = 0f;
    public float glucose = 0f;
    public int systolicBP = 0;
    public int diastolicBP = 0;

    public long lastUpdated = 0L;
}
