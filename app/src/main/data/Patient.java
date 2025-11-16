package uk.ac.ltu.hms.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients", indices = @Index(value = "nhsNumber", unique = true))
public class Patient {
    @PrimaryKey(autoGenerate = true) public long id;
    @NonNull public String nhsNumber = "";
    public String fullName;
}
