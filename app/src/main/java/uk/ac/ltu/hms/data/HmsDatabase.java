package uk.ac.ltu.hms.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { Patient.class }, version = 3, exportSchema = false)
public abstract class HmsDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase db) {

            db.execSQL("ALTER TABLE patients ADD COLUMN allergies TEXT");
            db.execSQL("ALTER TABLE patients ADD COLUMN conditions TEXT");
            db.execSQL("ALTER TABLE patients ADD COLUMN medications TEXT");

            db.execSQL("ALTER TABLE patients ADD COLUMN heartRate INTEGER NOT NULL DEFAULT 0");
            db.execSQL("ALTER TABLE patients ADD COLUMN temperature REAL NOT NULL DEFAULT 0");
            db.execSQL("ALTER TABLE patients ADD COLUMN glucose REAL NOT NULL DEFAULT 0");

            db.execSQL("ALTER TABLE patients ADD COLUMN systolicBP INTEGER NOT NULL DEFAULT 0");
            db.execSQL("ALTER TABLE patients ADD COLUMN diastolicBP INTEGER NOT NULL DEFAULT 0");

            db.execSQL("ALTER TABLE patients ADD COLUMN lastUpdated INTEGER NOT NULL DEFAULT 0");
        }
    };

    private static volatile HmsDatabase INSTANCE;

    public static HmsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HmsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    HmsDatabase.class,
                                    "hms-db"  // IMPORTANT: use your existing DB name!
                            )
                            .addMigrations(MIGRATION_2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
