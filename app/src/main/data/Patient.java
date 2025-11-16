package uk.ac.ltu.hms.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "patients",
        indices = @Index(value = {"nhsNumber"}, unique = true) // needed for the unique test
)
public class Patient {
    @PrimaryKey(autoGenerate = true) public long id;
    @NonNull public String fullName = "";
    @NonNull public String nhsNumber = "";
}
