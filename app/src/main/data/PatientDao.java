package uk.ac.ltu.hms.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Patient p);

    @Query("SELECT * FROM patients WHERE nhsNumber = :nhs LIMIT 1")
    Patient findByNhs(String nhs);
}
