package uk.ac.ltu.hms.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Patient.class }, version = 1, exportSchema = false)
public abstract class HmsDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();
}
